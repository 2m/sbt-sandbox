package tags

import org.scalatest._

object SlowTest extends Tag("slow")

class MySpec extends WordSpec {

  "MySpec" must {
    "be slow" taggedAs SlowTest in {}
    "be fast" in {}
  }
}
