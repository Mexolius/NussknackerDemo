package storage.tables

import model.storage.ScreeningRecord

import java.time.ZonedDateTime

import com.github.tminglei.slickpg._

trait CustomPostgresProfile extends ExPostgresProfile with PgArraySupport {

  override val api = MyAPI
  object MyAPI extends API with ArrayImplicits
}

object CustomPostgresProfile extends CustomPostgresProfile
import CustomPostgresProfile.api._


trait ScreeningsTable {
  class Screenings(tag: Tag) extends Table[ScreeningRecord](tag, "Screenings") {
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
