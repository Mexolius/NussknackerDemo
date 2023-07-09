package service

import model.response.ScreeningsResponse
import model.response.ScreeningsResponse.ScreeningEntity
import storage.ScreeningsRepository

import java.time.ZonedDateTime
import scala.concurrent.{ExecutionContext, Future}

class ScreeningsService(
                         screeningsRepo: ScreeningsRepository
                       )(implicit executionContext: ExecutionContext) {
  def getScreenings(from: ZonedDateTime, to: ZonedDateTime): Future[ScreeningsResponse] = {
    for{
      screenings <- screeningsRepo.getScreenings(from, to)
    } yield {
      ScreeningsResponse(
        screenings.map(ScreeningEntity(_))
      )
    }
  }
}
