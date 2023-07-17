package api

import akka.http.scaladsl.model.{StatusCode, StatusCodes}

final case class ApiException(status: StatusCode = StatusCodes.InternalServerError,
                               message: String = "Internal Server Error",
                              private val cause: Throwable = None.orNull) extends Exception(message, cause)
