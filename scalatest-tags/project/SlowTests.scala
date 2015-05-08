import sbt.Keys._
import sbt._

object SlowTests extends AutoPlugin {

  override def trigger = allRequirements

  lazy val SlowTest = config("slow") extend Test

  override lazy val projectConfigurations = Seq(SlowTest)
  override lazy val projectSettings = inConfig(SlowTest)(Defaults.testTasks) ++ Seq(
    testOptions in SlowTest += Tests.Argument("-n", "slow")
  )
}
