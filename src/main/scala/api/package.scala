import akka.http.scaladsl.marshalling.{Marshaller, ToEntityMarshaller}
import akka.http.scaladsl.model.MediaTypes.`application/json`
import akka.http.scaladsl.model.{ContentTypeRange, HttpEntity}
import akka.http.scaladsl.unmarshalling.{FromEntityUnmarshaller, Unmarshaller}
import spray.json.{JsonFormat, _}

import scala.concurrent.Future
import scala.util.Try

package object api {
  private def jsonContentTypes: List[ContentTypeRange] =
    List(`application/json`)

  implicit final def unmarshaller[A: JsonFormat]: FromEntityUnmarshaller[A] = {
    Unmarshaller.stringUnmarshaller
      .forContentTypes(jsonContentTypes: _*)
      .flatMap { ctx => mat => json =>
        Try(json.parseJson.convertTo[A])
          .fold(Future.failed, Future.successful)
      }
  }

  implicit final def marshaller[A: JsonFormat]: ToEntityMarshaller[A] = {
    Marshaller.withFixedContentType(`application/json`) { a =>
      HttpEntity(`application/json`, a.toJson.toString)
    }
  }
}
