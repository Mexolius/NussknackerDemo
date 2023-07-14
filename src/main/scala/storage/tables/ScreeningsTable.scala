package storage.tables

import model.storage.ScreeningRecord

import java.time.ZonedDateTime
import com.github.tminglei.slickpg._

import java.time.format.DateTimeFormatter

trait CustomPostgresProfile extends ExPostgresProfile with PgArraySupport with PgDate2Support {
  private val localDateTimeFormatter = DateTimeFormatter.ofPattern("\"yyyy-MM-dd HH:mm:ss Z\"")

  override val api = MyAPI
  object MyAPI extends API with ArrayImplicits with DateTimeImplicits
}

object CustomPostgresProfile extends CustomPostgresProfile
import CustomPostgresProfile.api._


trait ScreeningsTable {
  class Screenings(tag: Tag) extends Table[ScreeningRecord](tag, "screenings") {
    def screeningId = column[Int]("screening_id", O.PrimaryKey, O.AutoInc)

    def from: Rep[ZonedDateTime] = column[ZonedDateTime]("ts_from")

    def title: Rep[String] = column[String]("title")

    def roomId: Rep[Int] = column[Int]("room_id")

    def seats: Rep[List[Int]] = column[List[Int]]("seats")

    def * =
      (screeningId, from, title, roomId, seats) <> (ScreeningRecord.tupled, ScreeningRecord.unapply)
  }

  val screenings: TableQuery[Screenings] = TableQuery[Screenings]
}
