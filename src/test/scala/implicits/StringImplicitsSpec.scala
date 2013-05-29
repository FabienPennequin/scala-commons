package test

import org.specs2.mutable._

class StringImplicitsSpec extends Specification {

  "StringImplicits" should {
    import me.pennequin.fabien.implicits.StringImplicits._

    "add slugify method" in {
      "Test  Slug ! Generator :: for Scala 2.10".slugify must beEqualTo("test-slug--generator--for-scala-210")
    }
  }

}