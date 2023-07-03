package api

import akka.http.scaladsl.Http

import scala.concurrent.Future

class Api(screeningsEndpoint: ScreeningsEndpoint) {
  def run: Future[Http.ServerBinding] = ???
}
