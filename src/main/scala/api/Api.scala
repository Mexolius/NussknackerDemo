package api

import akka.actor.typed.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model._
import akka.http.scaladsl.server.Directives._

import scala.concurrent.{ExecutionContext, Future}

class Api(screeningsEndpoint: ScreeningsEndpoint)(implicit as: ActorSystem[_], ec: ExecutionContext) {
  def run: Future[Http.ServerBinding] = {

    val routes = pathPrefix("nussknacker" / "demo") {
      pathEndOrSingleSlash {
        get {
          complete(StatusCodes.OK)
        }
      } ~ screeningsEndpoint.routes
    }

    Http().newServerAt("localhost", 8080).bind(routes)
  }
}
