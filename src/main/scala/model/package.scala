import spray.json.{JsString, JsValue, RootJsonFormat}

import java.time.ZonedDateTime
import java.time.format.{DateTimeFormatter, DateTimeParseException}

package object model {
  implicit object ZonedDateTimeFormat extends RootJsonFormat[ZonedDateTime] {
    override def read(json: JsValue): ZonedDateTime = {
      println(json)
      json match {
        case str: JsString =>
          ZonedDateTime.parse(str.toString(), DateTimeFormatter.ofPattern("\"yyyy-MM-dd HH:mm:ss Z\""))
      }
    }

    override def write(ts: ZonedDateTime): JsValue = {
      JsString(ts.toString)
    }
  }
}
