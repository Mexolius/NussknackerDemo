package model.request

import model.ZonedDateTimeFormat
import spray.json.DefaultJsonProtocol.jsonFormat2
import spray.json.JsonFormat

import java.time.ZonedDateTime

case class TimeInterval(from: ZonedDateTime, to: ZonedDateTime)

object TimeInterval{
  implicit val fmt: JsonFormat[TimeInterval] = jsonFormat2(TimeInterval.apply)
}
