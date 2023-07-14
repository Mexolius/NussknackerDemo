package model.request

import spray.json.DefaultJsonProtocol._
import spray.json.RootJsonFormat

case class TicketType(price: Float)

object TicketType{
  def isUnsupported(ticket: TicketType): Boolean = ticket.price match {
    case 25 | 18 | 12.5 => false
    case _ => true
  }

  implicit val fmt: RootJsonFormat[TicketType] = jsonFormat1(TicketType.apply)
}
