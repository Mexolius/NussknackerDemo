package storage

import model.storage.ScreeningRecord
import slick.jdbc.PostgresProfile.api._
import storage.tables.ScreeningsTable

import java.time.ZonedDateTime
import scala.concurrent.Future

class ScreeningsRepository(db: Database) extends ScreeningsTable {
  def getScreenings(from: ZonedDateTime, to: ZonedDateTime): Future[Seq[ScreeningRecord]] = {
    val query =
      screenings
        .filter(s => s.from > from && s.from < to)
        .sortBy(s => (s.title, s.from))
        .result

    db.run(query)
  }

  def getScreening(screeningId: Int): Future[Option[ScreeningRecord]] = {
    val query =
      screenings
        .filter(s => s.screeningId === screeningId)
        .take(1)
        .result
        .headOption

    db.run(query)
  }
}
