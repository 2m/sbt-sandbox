scalaVersion := "2.11.6"

lazy val root =
  Project("root", file("."))
    .settings(
      libraryDependencies += "org.scalatest" %% "scalatest" % "2.2.4"
    )
    .enablePlugins(FunTests)
