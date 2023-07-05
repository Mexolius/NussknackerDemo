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
  case class ScreeningEntity(from: ZonedDateTime, to: ZonedDateTime, title: String)

  object ScreeningEntity{
    def apply(record: ScreeningRecord): ScreeningEntity = {
      ScreeningEntity(
        record.from,
        record.to,
        record.title
      )
    }

    implicit val fmt: JsonFormat[ScreeningEntity] = jsonFormat3(ScreeningEntity.apply)
  }

  implicit val fmt: JsonFormat[ScreeningsResponse] = jsonFormat1(ScreeningsResponse.apply)
}
