lazy val failingTask = taskKey[Unit]("Task that fails.")
lazy val mainTask = taskKey[Unit]("Main task that dependencies will be attached to.")

val runAdditionalTasks = settingKey[Boolean]("Whether to run additional tasks.")
runAdditionalTasks := false

failingTask := {
  println("This task is going to fail in 1 second")
  java.util.concurrent.TimeUnit.SECONDS.sleep(1)
  error("opapa")
}

mainTask := {
  println("Running Main Task.")
}

/**
 * This fails at runtime with:
 *
 * > mainTask
 * [trace] Stack trace suppressed: run last *:mainTask for the full output.
 * [error] (*:mainTask) sbt.Init$RuntimeUndefined: References to undefined settings at runtime.
 * [error] Total time: 0 s, completed May 27, 2015 9:56:57 AM
 */
mainTask := Def.taskDyn {
  if (runAdditionalTasks.value) {
    Def.task {
      failingTask.value
      mainTask.value
    }
  }
  else {
    Def.task { mainTask.value }
  }
}.value
