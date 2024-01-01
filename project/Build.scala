import sbt.*
import sbt.Keys.*

object Build {
  // commond dependencies
  val scalaTest = "org.scalatest" %% "scalatest" % "3.2.15" % "test"

  val githubResolver = Resolver.url("GitHub Package Registry", url("https://maven.pkg.github.com/reminia/_"))

  val githubCredential = Credentials(
    "GitHub Package Registry",
    "maven.pkg.github.com",
    System.getenv("GITHUB_REPOSITORY_OWNER"),
    System.getenv("GITHUB_TOKEN")
  )

  // publish setting, default publish to github repository
  val publishSettings = Seq(
    publishTo := Some("Github Package Registry" at "https://maven.pkg.github.com/" + System.getenv("GITHUB_REPOSITORY")),
    credentials += githubCredential,
    Test / packageDoc / publishArtifact := false,
    Test / packageSrc / publishArtifact := false,
    Test / packageBin / publishArtifact := false,
    Compile / packageDoc / publishArtifact := false
  )

}
