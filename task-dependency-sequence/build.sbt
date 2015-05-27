lazy val failingTask = taskKey[Unit]("Task that fails.")
lazy val mainTask = taskKey[Unit]("Main task that dependencies will be attached to.")

val runAdditionalTasks = settingKey[Boolean]("Whether to run additional tasks.")
runAdditionalTasks := false

val additionalTasks = settingKey[Seq[TaskKey[_]]]("Additional tasks.")
additionalTasks := Seq(failingTask)

failingTask := {
  println("This task is going to fail in 1 second")
  java.util.concurrent.TimeUnit.SECONDS.sleep(1)
  error("opapa")
}

/**
 * This fails at compile time with:
 *
 * [info] Loading global plugins from /home/martynas/.sbt/0.13/plugins
 * [info] Loading project definition from /home/martynas/projects/samples/sbt-sandbox/task-dependency-sequence/project
 * /home/martynas/projects/samples/sbt-sandbox/task-dependency-sequence/build.sbt:16: error: Illegal dynamic reference: task
 *       task.value
 *       ^
 * [error] Type error in expression
 */
mainTask := {
  if (runAdditionalTasks.value) {
    for (task <- additionalTasks.value)
      task.value
  }

  println("Running Main Task.")
}
