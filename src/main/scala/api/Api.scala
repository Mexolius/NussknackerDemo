package api

import akka.actor.typed.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model._
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.ExceptionHandler

import scala.concurrent.{ExecutionContext, Future}

class Api(screeningsEndpoint: ScreeningsEndpoint)(implicit as: ActorSystem[_], ec: ExecutionContext) {

  private def exceptionHandler = ExceptionHandler{
    case e: ApiException =>
      complete(e.status, e.message)
  }
  def run: Future[Http.ServerBinding] = {
    val routes = handleExceptions (exceptionHandler){
      pathPrefix("nussknacker" / "demo") {
        pathEndOrSingleSlash {
          get {
            complete(StatusCodes.OK)
          }
        } ~ screeningsEndpoint.routes
      }
    }



    Http().newServerAt("localhost", 8080).bind(routes)
  }
}
