package api

import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import model.request.TimeInterval
import service.ScreeningsService

class ScreeningsEndpoint(
                          service: ScreeningsService
                        ) {
  def routes: Route = {
    path("screenings") {
      pathEndOrSingleSlash {
        post {
          entity(as[TimeInterval]) { interval =>
            complete(service.getScreenings(interval.from, interval.to))
          }
        }
      }
    }
  }
}
