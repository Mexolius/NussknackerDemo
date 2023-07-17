package model.response

import model.ZonedDateTimeFormat
import model.response.ScreeningsResponse.ScreeningEntity
import model.storage.ScreeningRecord
import spray.json.DefaultJsonProtocol._
import spray.json.JsonFormat

import java.time.ZonedDateTime

case class ScreeningsResponse(
                             screenings: Seq[ScreeningEntity]
                             )

object ScreeningsResponse{
  case class ScreeningEntity(screeningId: Int, from: ZonedDateTime, title: String, roomId: Int)

  object ScreeningEntity{
    def apply(record: ScreeningRecord): ScreeningEntity = {
      ScreeningEntity(
        record.screeningId,
        record.from,
        record.title,record.roomId
      )
    }

    implicit val fmt: JsonFormat[ScreeningEntity] = jsonFormat4(ScreeningEntity.apply)
  }

  implicit val fmt: JsonFormat[ScreeningsResponse] = jsonFormat1(ScreeningsResponse.apply)
}
