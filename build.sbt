import Build.*

val projectVersion = "0.1.0-SNAPSHOT"
val javaVersion = "11"
val scala2version = "2.13.12"

javacOptions ++= Seq("-source", javaVersion, "-target", javaVersion)

Global / onChangedBuildSource := ReloadOnSourceChanges

val commonSettings = Seq(
  version := projectVersion,
  scalaVersion := scala2version,
  maintainer := "sleefd@gmail.com",
  organization := "me.yceel",
  javacOptions := Seq("-source", javaVersion, "-target", javaVersion),
  scalacOptions ++= Seq("-Xsource:3"),
  resolvers += "Github Package" at "https://maven.pkg.github.com/reminia/_"
)

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
  .aggregate(bridge)

lazy val bridge = project
  .in(file("lang-bridge"))
  .enablePlugins(JavaAppPackaging, UniversalPlugin)
  .settings(commonSettings)
  .settings(
    name := "bridge",
    libraryDependencies ++= Seq(
      "me.yceel.json2struct" %% "core" % "0.5.0",
      "com.squareup" % "javapoet" % "1.13.0",
      scalaTest
    )
  )
