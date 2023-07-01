import akka.actor.typed.ActorSystem
import akka.actor.typed.scaladsl.Behaviors
import api.{Api, ScreeningsEndpoint}

import scala.concurrent.ExecutionContextExecutor
import scala.io.StdIn

object Main {
  def main(args: Array[String]): Unit = {
    implicit val system: ActorSystem[Nothing] = ActorSystem(Behaviors.empty, "my-system")
    // needed for the future flatMap/onComplete in the end
    implicit val executionContext: ExecutionContextExecutor = system.executionContext

    // Endpoints

    val screeningsEndpoint = new ScreeningsEndpoint()
    val apiFuture = new Api(screeningsEndpoint).run

    println(s"Server now online. Please navigate to http://localhost:8080/hello\nPress RETURN to stop...")
    StdIn.readLine() // let it run until user presses return

    apiFuture
      .flatMap(_.unbind()) // trigger unbinding from the port
      .onComplete(_ => system.terminate()) // and shutdown when done
  }
}
