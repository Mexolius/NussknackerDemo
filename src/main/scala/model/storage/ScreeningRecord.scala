package model.storage

import java.time.ZonedDateTime

case class ScreeningRecord(screeningId: Int, from: ZonedDateTime, title: String, roomId: Int, seats: List[Int])
