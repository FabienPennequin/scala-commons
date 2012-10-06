package test

import org.specs2.mutable._

import org.joda.time.DateTime
import me.pennequin.fabien.implicits.RichDateTime

class DateTimeImplicitsSpec extends Specification {

  "RichDateTime.toString(String) method" should {
    import me.pennequin.fabien.implicits.RichDateTime

    "return formatted datetime as string" in {
      new RichDateTime(new DateTime(2012,5,27, 0,0,0)).toString("yyyy") must beEqualTo("2012")
      new RichDateTime(new DateTime(2012,5,27, 0,0,0)).toString("yyyy-MM-dd") must beEqualTo("2012-05-27")
    }
  }

  "DateTimeImplicits" should {
    import me.pennequin.fabien.implicits.DateTimeImplicits._

    "add toString(String) method" in {
      new DateTime(2012,5,27,0,0,0).toString("yyyy-MM-dd") must beEqualTo("2012-05-27")
    }
  }

}