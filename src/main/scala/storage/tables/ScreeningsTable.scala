package storage.tables

import model.storage.ScreeningRecord
import com.github.tminglei.slickpg._

import java.time.ZonedDateTime

trait MyPostgresProfile extends ExPostgresProfile with PgArraySupport {

  override val api = MyAPI

  object MyAPI extends API with ArrayImplicits
}

object MyPostgresProfile extends MyPostgresProfile
import MyPostgresProfile.api._

trait ScreeningsTable {
  class Screenings(tag: Tag) extends Table[ScreeningRecord](tag, "Screenings"){
    def screeningId = column[Int]("Screening_id", O.PrimaryKey, O.AutoInc)

    def from = column[ZonedDateTime]("from")

    def title = column[String]("title")

    def roomId = column[Int]("roomId")
    def seats = column[List[Int]]("seats")

    def * =
      (screeningId, from, title, roomId, seats) <> (ScreeningRecord.tupled, ScreeningRecord.unapply)
  }

  val screenings = TableQuery[Screenings]
}
