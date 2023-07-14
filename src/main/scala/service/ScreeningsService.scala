package service

import akka.http.scaladsl.model.StatusCodes
import api.ApiException
import model.request.{BookingDetails, TicketType}
import model.response.ScreeningsResponse.ScreeningEntity
import model.response.{BookingResult, ScreeningDetails, ScreeningsResponse}
import model.storage.BookingRecord
import storage.{BookingRepository, ScreeningsRepository}

import java.time.ZonedDateTime
import scala.concurrent.{ExecutionContext, Future}

class ScreeningsService(
                         screeningsRepo: ScreeningsRepository,
                         bookingRepository: BookingRepository
                       )(implicit ec: ExecutionContext) {
  def getScreenings(from: ZonedDateTime, to: ZonedDateTime): Future[ScreeningsResponse] = {
    for{
      screenings <- screeningsRepo.getScreenings(from, to)
    } yield {
      ScreeningsResponse(
        screenings.map(ScreeningEntity(_))
      )
    }
  }

  def getScreeningDetails(screeningId: Int): Future[ScreeningDetails] = {
    for {
      screening <- screeningsRepo.getScreening(screeningId).map(_.getOrElse {
        throw ApiException(StatusCodes.NotFound, "Screening does not exist")
      })
      priorBookings <- bookingRepository.getBookings(screeningId)
    } yield {
      ScreeningDetails(
        screeningId = screeningId,
        screeningRoom = screening.roomId,
        freeSeats = screening.seats.diff(priorBookings.map(_.seat))
      )
    }
  }

  def bookScreening(screeningId: Int, bookingDetails: BookingDetails): Future[BookingResult] = {
    val tickets = bookingDetails.tickets
    if(tickets.exists(td => TicketType.isUnsupported(td.ticketType))){
      throw ApiException(StatusCodes.BadRequest, "Ticket type does not exist")
    } else {
      for {
        screening <- screeningsRepo.getScreening(screeningId).map(_.getOrElse{
          throw ApiException(StatusCodes.NotFound, "Screening does not exist")
        })
        _ = {
          if(ZonedDateTime.now.plusMinutes(15).isAfter(screening.from)){
            throw ApiException(StatusCodes.BadRequest, "The booking period for this screening is already over")
          }
        }
        priorBookings <- bookingRepository.getBookings(screeningId)
        seatsNumbers = tickets.map(_.seat)
        _ = {
          if(priorBookings.map(_.seat).exists(seatsNumbers.contains)){
            throw ApiException(StatusCodes.BadRequest, "One or more seats already taken")
          }
        }
        bookingRecords = bookingDetails.tickets.map{ ticket =>
          BookingRecord(
            screeningId = screeningId,
            seat = ticket.seat,
            ticketPrice = ticket.ticketType.price,
            bookingName = bookingDetails.reservationName
          )
        }
        _ <- bookingRepository.bookSeats(bookingRecords)
      } yield {
        BookingResult(
          paymentAmount = tickets.map(_.ticketType.price).sum,
          expirationDate = screening.from.plusHours(1)
        )
      }
    }
  }
}
