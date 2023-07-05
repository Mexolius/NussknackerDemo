package model.storage

import java.time.ZonedDateTime

case class ScreeningRecord(from: ZonedDateTime, to: ZonedDateTime, title: String)
