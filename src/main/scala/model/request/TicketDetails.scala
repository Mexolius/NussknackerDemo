package model.request

import spray.json.DefaultJsonProtocol._
import spray.json.RootJsonFormat

case class TicketDetails(seat: Int, ticketType: TicketType)

object TicketDetails{
  implicit val fmt: RootJsonFormat[TicketDetails] = jsonFormat2(TicketDetails.apply)
}
