import Build._

version := "0.1.0-SNAPSHOT"
scalaVersion := "2.13.12"
organization := "me.yceel"
maintainer := "sleefd@gmail.com"
val javaVersion = "11"

javacOptions ++= Seq("-source", javaVersion, "-target", javaVersion)

Global / onChangedBuildSource := ReloadOnSourceChanges

lazy val root = project
  .in(file("."))
  .enablePlugins(JavaAppPackaging, UniversalPlugin)
  .settings(
    name := "cdr2api",
    libraryDependencies ++= Seq(
      scalaTest
    )
  )
  .settings(
    Universal / mappings ++= Seq(file("README.md") -> "README.md"),
  )
  .settings(publishSettings)
