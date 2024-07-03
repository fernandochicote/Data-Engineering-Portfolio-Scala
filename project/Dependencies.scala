import sbt._
import Keys._
import Versions._

object Dependencies {

  val sparkDependencies = Seq(
    "org.apache.spark" %% "spark-core" % Versions.spark % Provided,
    "org.apache.spark" %% "spark-sql" % Versions.spark % Provided,

  )

  val testDependencies = Seq(
    "org.scalatest" %% "scalatest" % Versions.scalaTest % Test
  )

}
