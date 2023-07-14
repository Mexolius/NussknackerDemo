package storage

import akka.Done
import model.storage.BookingRecord
import slick.jdbc.PostgresProfile.api._
import storage.tables.BookingTable

import scala.concurrent.{ExecutionContext, Future}

class BookingRepository(db: Database)(implicit ec: ExecutionContext) extends BookingTable {
  def getBookings(screeningId: Int): Future[Seq[BookingRecord]] = {
    val query =
      bookings
        .filter(r => r.screeningId === screeningId)
        .result

    db.run(query)
  }

  def bookSeats(bookingList: Seq[BookingRecord]): Future[Done] = {
    db.run((bookings ++= bookingList).map(_=>Done))
  }
}
