object Main extends App {
  println("Existing resource:")
  println(getClass.getResource("/res.dat"))
  println(getClass.getResourceAsStream("res.dat"))

  println("Missing resource:")
  println(getClass.getResource("/missing.dat"))
  println(getClass.getResourceAsStream("missing.dat"))
}
