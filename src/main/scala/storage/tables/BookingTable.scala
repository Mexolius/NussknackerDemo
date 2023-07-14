package storage.tables

import model.storage.BookingRecord
import slick.jdbc.PostgresProfile.api._
trait BookingTable {
  class Bookings(tag: Tag) extends Table[BookingRecord]( tag, "bookings"){

    def screeningId = column[Int]("screening_id", O.PrimaryKey)
    def seat = column[Int]("seat", O.PrimaryKey)
    def ticketPrice = column[Float]("ticket_price")
    def bookingName = column[String]("booking_name")

    def * = (screeningId, seat, ticketPrice, bookingName) <> (BookingRecord.tupled, BookingRecord.unapply)
  }

  val bookings: TableQuery[Bookings] = TableQuery[Bookings]
}
