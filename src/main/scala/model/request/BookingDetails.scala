package model.request

import spray.json.DefaultJsonProtocol._
import spray.json.RootJsonFormat

case class BookingDetails(reservationName: String, tickets: Seq[TicketDetails])

object BookingDetails{
  implicit val fmt: RootJsonFormat[BookingDetails] = jsonFormat2(BookingDetails.apply)
}
