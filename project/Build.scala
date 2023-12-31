import sbt._
import sbt.Keys._

object Build {
  // commond dependencies
  val scalaTest = "org.scalatest" %% "scalatest" % "3.2.15" % "test"

  // publish setting, default publish to github repository
  val publishSettings = Seq(
    publishTo := Some("Github Package Registry" at "https://maven.pkg.github.com/" + System.getenv("GITHUB_REPOSITORY")),
    credentials += Credentials(
      "GitHub Package Registry",
      "maven.pkg.github.com",
      System.getenv("GITHUB_REPOSITORY_OWNER"),
      System.getenv("GITHUB_TOKEN")
    ),
    Test / packageDoc / publishArtifact := false,
    Test / packageSrc / publishArtifact := false,
    Test / packageBin / publishArtifact := false,
    Compile / packageDoc / publishArtifact := false
  )

}
