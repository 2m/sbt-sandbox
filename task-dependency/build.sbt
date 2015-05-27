lazy val failingTask = taskKey[Unit]("Task that fails.")
lazy val staticDepenency = taskKey[Unit]("Task with a static dependency.")
lazy val dynamicDepenency = taskKey[Unit]("Task with a dynamic dependency.")

failingTask := {
  println("This task is going to fail in 3 seconds")
  java.util.concurrent.TimeUnit.SECONDS.sleep(3)
  println("...done.")
  error("opapa")
}

staticDepenency := {
  failingTask.value
  println("After dependency")
}

dynamicDepenency := Def.taskDyn {
  Def.task {
    failingTask.value
    println("After dependency")
  }
}.value
