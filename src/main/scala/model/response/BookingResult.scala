package model.response

import model.ZonedDateTimeFormat
import spray.json.DefaultJsonProtocol._
import spray.json.RootJsonFormat

import java.time.ZonedDateTime

case class BookingResult(paymentAmount: Float, expirationDate: ZonedDateTime)

object BookingResult{
  implicit val fmt: RootJsonFormat[BookingResult] = jsonFormat2(BookingResult.apply)
}
