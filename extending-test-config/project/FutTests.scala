import sbt.Keys._
import sbt._

object FunTests extends AutoPlugin {

  lazy val FunTest = config("fun") extend(Test)

  override lazy val projectConfigurations = Seq(FunTest)
  override lazy val projectSettings = inConfig(FunTest)(Defaults.testTasks) ++ Seq(
    testOptions in Test := Seq(Tests.Filter(unitFilter)),
    testOptions in FunTest := Seq(Tests.Filter(itFilter))
  )

  def itFilter(name: String): Boolean = name endsWith "FunSpec"
  def unitFilter(name: String): Boolean = (name endsWith "Spec") && !itFilter(name)
}
