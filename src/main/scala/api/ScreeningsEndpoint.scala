package api

import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server._
import model.request.{BookingDetails, TimeInterval}
import service.ScreeningsService

class ScreeningsEndpoint(service: ScreeningsService) {
  def routes: Route = {
    pathPrefix("screenings") {
      pathEndOrSingleSlash {
        post {
          entity(as[TimeInterval]) { interval =>
            complete(service.getScreenings(interval.from, interval.to))
          }
        }
      } ~ path("details" / IntNumber){ screeningId =>
        pathEndOrSingleSlash {
          get {
            complete(service.getScreeningDetails(screeningId))
          }
        }
      } ~ path("book" / IntNumber) { screeningId =>
        put {
          pathEndOrSingleSlash {
            entity(as[BookingDetails]) { details =>
              complete(service.bookScreening(screeningId, details))
            }
          }
        }
      }
    }
  }
}
