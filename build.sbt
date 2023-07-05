ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.11"

lazy val root = (project in file("."))
  .settings(
    name := "NussknackerDemo"
  )

val AkkaVersion = "2.8.0"
val AkkaHttpVersion = "10.5.2"
val SlickVersion = "3.4.1"
val MYSqlVersion = "8.0.33"
val configVersion = "1.4.2"

// Lightbend Config
libraryDependencies += "com.typesafe" % "config" % configVersion

// Akka
libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor-typed" % AkkaVersion,
  "com.typesafe.akka" %% "akka-stream" % AkkaVersion,
  "com.typesafe.akka" %% "akka-http" % AkkaHttpVersion,
  "com.typesafe.akka" %% "akka-http-spray-json" % AkkaHttpVersion
)

// Database
libraryDependencies ++= Seq(
  "com.typesafe.slick" %% "slick" % SlickVersion,
  "mysql" % "mysql-connector-java" % MYSqlVersion
)
