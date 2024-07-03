import Dependencies.*

ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := Versions.scala

lazy val root = (project in file("."))
  .settings(
    name := "Data-Engineering-Portfolio-Scala",
    libraryDependencies ++= sparkDependencies++ testDependencies
  )
