package storage

import model.storage.ScreeningRecord
import slick.jdbc.MySQLProfile.api._

import java.time.ZonedDateTime
import scala.concurrent.Future

class ScreeningsRepository(db: Database) {
  def getScreenings(from: ZonedDateTime, to: ZonedDateTime): Future[Seq[ScreeningRecord]] = {
    ???
  }
}
