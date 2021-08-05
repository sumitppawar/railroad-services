
import sbt._

object Dependencies {


  val tests = Seq(
    "org.scalatest" %% "scalatest" % "3.2.9" % Test,
    "org.scalamock" %% "scalamock" % "5.1.0" % Test
  )

  val log = Seq(
    "org.slf4j" % "slf4j-api" % "1.7.32",
    "ch.qos.logback" % "logback-core" % "1.2.5"
  )

}
