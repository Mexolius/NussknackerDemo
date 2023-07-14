package model.response

import spray.json.DefaultJsonProtocol._
import spray.json.RootJsonFormat

case class ScreeningDetails(screeningId: Int, screeningRoom: Int, freeSeats: Seq[Int])

object ScreeningDetails {
  implicit val fmt: RootJsonFormat[ScreeningDetails] = jsonFormat3(ScreeningDetails.apply)
}
