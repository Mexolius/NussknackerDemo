import akka.actor.typed.ActorSystem
import akka.actor.typed.scaladsl.Behaviors
import api.{Api, ScreeningsEndpoint}
import com.typesafe.config.{Config, ConfigFactory}
import service.ScreeningsService
import storage.{BookingRepository, ScreeningsRepository}
import slick.jdbc.PostgresProfile.api._

import scala.concurrent.ExecutionContextExecutor
import scala.io.StdIn

object Main {
  def main(args: Array[String]): Unit = {
    implicit val system: ActorSystem[_] = ActorSystem(Behaviors.empty, "my-system")
    implicit val executionContext: ExecutionContextExecutor = system.executionContext

    val config: Config = ConfigFactory.load()

    // Database 
    val db = Database.forConfig("postgres", config)

    // Repo
    val screeningsRepository = new ScreeningsRepository(db)
    val bookingRepository = new BookingRepository(db)

    // Service
    val screeningsService = new ScreeningsService(screeningsRepository, bookingRepository)

    // Endpoints

    val screeningsEndpoint = new ScreeningsEndpoint(screeningsService)
    val apiFuture = new Api(screeningsEndpoint).run

    println(s"Server now online. Please navigate to http://localhost:8080/hello\nPress RETURN to stop...")
    StdIn.readLine() // let it run until user presses return

    apiFuture
      .flatMap(_.unbind()) // trigger unbinding from the port
      .onComplete(_ => system.terminate()) // and shutdown when done
  }
}
