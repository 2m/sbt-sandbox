import sbt._
import sbt.Keys._

import scala.concurrent.{Await, Promise}

object MainBuild extends Build {

  import DependencyHelpers._
  import ScalaVersionDependentProject._

  lazy val project1 = Project(
    id = "project1",
    base = file("project1")
  )

  lazy val project2 = Project(
    id = "project2",
    base = file("project2")
  )

  val promise = Promise[String]

  lazy val root = Project(
    id = "root",
    base = file("."),
    settings = Defaults.defaultSettings ++ Seq(
      scalaVersion := "2.10.4",
      crossScalaVersions := Seq("2.10.4", "2.11.2")
    ),
    aggregate = Seq(project1, project2)
  )

  val agg = aggregates(project1, pre211Project(project2))
  println(scala.util.Properties.versionNumberString)
  println(agg)

}


