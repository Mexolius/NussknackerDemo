ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.11"

lazy val root = (project in file("."))
  .settings(
    name := "NussknackerDemo"
  )

val AkkaVersion = "2.8.0"
val AkkaHttpVersion = "10.5.2"
val SlickVersion = "3.4.1"
val hikariCPVersion = "3.4.1"
val PostgresVersion = "42.5.4"
val configVersion = "1.4.2"

// Lightbend Config
libraryDependencies += "com.typesafe" % "config" % configVersion

// Akka
libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor-typed" % AkkaVersion,
  "com.typesafe.akka" %% "akka-stream" % AkkaVersion,
  "com.typesafe.akka" %% "akka-http" % AkkaHttpVersion,
  "com.typesafe.akka" %% "akka-http-spray-json" % AkkaHttpVersion,
  "ch.qos.logback" % "logback-classic" % "1.4.7"
)

// Database
libraryDependencies ++= Seq(
  "com.typesafe.slick" %% "slick" % SlickVersion,
  "com.typesafe.slick" %% "slick-hikaricp" % hikariCPVersion,
  "org.postgresql" % "postgresql" % PostgresVersion,
  "com.github.tminglei" %% "slick-pg" % "0.21.1"
)

