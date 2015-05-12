import sbt.Keys._
import sbt._

object NoDeps extends AutoPlugin {

  lazy val CustomTests = config("cus-tests") extend(Test)

  override lazy val projectConfigurations = Seq(CustomTests)
  override lazy val projectSettings = inConfig(CustomTests)(Defaults.testTasks) ++ Seq(
    test in CustomTests := {
      println("Running custom tests")
      (test in CustomTests).value
    }
  )
}
