import spray.json.{JsString, JsValue, RootJsonFormat}

import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

package object model {
  implicit object ZonedDateTimeFormat extends RootJsonFormat[ZonedDateTime] {
    override def read(json: JsValue): ZonedDateTime = {
      json match {
        case str: JsString => ZonedDateTime.parse(str.toString(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss a z"))
      }
    }

    override def write(ts: ZonedDateTime): JsValue = {
      JsString(ts.toString)
    }
  }
}
