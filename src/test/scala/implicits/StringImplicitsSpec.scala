package test

import org.specs2.mutable._

class StringImplicitsSpec extends Specification {

  "RichString.slugify method" should {
    import me.pennequin.fabien.implicits.RichString

    "remove extra spaces" in {
      new RichString(" ").slugify must beEqualTo("")
      new RichString("   ").slugify must beEqualTo("")
      new RichString(" fabien ").slugify must beEqualTo("fabien")
    }

    "change case to lower" in {
      new RichString("FaBiEn").slugify must beEqualTo("fabien")
    }

    "convert space to dash" in {
      new RichString("f a b").slugify must beEqualTo("f-a-b")
      new RichString("f  a  b").slugify must beEqualTo("f-a-b")
      new RichString(" f a b ").slugify must beEqualTo("f-a-b")
    }

    "convert accented character" in {
      // Latin-1 Supplement
      new RichString("ÀÁÂÃÄÅÇÈÉÊËÌÍÎÏÑÒÓÔÕÖÙÚÛÜÝß").slugify must beEqualTo("aaaaaaceeeeiiiinooooouuuuyb")
      new RichString("àáâãäåçèéêëìíîïñòóôõöùúûüýÿ").slugify must beEqualTo("aaaaaaceeeeiiiinooooouuuuyy")

      // Latin Extended-A
      new RichString("ĀāĂăĄąĆćĈĉĊċČčĎďĐđĒēĔĕĖėĘęĚěĜĝĞğĠġĢģĤĥ").slugify must beEqualTo("aaaaaaccccccccddddeeeeeeeeeegggggggghh")
      new RichString("ĦħĨĩĪīĬĭĮįİıĲĳĴĵĶķĸĹĺĻļĽľĿŀŁłŃńŅņŇňŉŊŋ").slugify must beEqualTo("hhiiiiiiiiiiijijjjkkkllllllllllnnnnnnnnn")
      new RichString("ŌōŎŏŐőŒœŔŕŖŗŘřŚśŜŝŞşŠšŢţŤťŦŧŨũŪūŬŭŮůŰűŲųŴŵŶŷŸŹźŻżŽžſ").slugify must beEqualTo("oooooooeoerrrrrrssssssssttttttuuuuuuuuuuuuwwyyyzzzzzzs")

      // Others
      new RichString("ÄäÜüÖöß").slugify must beEqualTo("aauuoob")
      // Norwegian characters
      new RichString("ÅÆØæøå").slugify must beEqualTo("aaeoaeoa")
    }

    "convert some special character" in {
      // Plus
      new RichString("+").slugify must beEqualTo("plus")
      // Euro Sign
      new RichString("€").slugify must beEqualTo("e")
      // GBP (Pound) Sign
      new RichString("£").slugify must beEqualTo("l")
    }

    "replace tab and CR" in {
      new RichString("f\na\tb").slugify must beEqualTo("f-a-b")
      new RichString("\nf\n\na\tb\t\ti\ne\tn").slugify must beEqualTo("f-a-b-i-e-n")
    }

    "remove all others special chars" in {
      new RichString("Fab??ien!!!").slugify must beEqualTo("fabien")
      new RichString("Fabi(e)n").slugify must beEqualTo("fabien")
      new RichString("Fab{ien}").slugify must beEqualTo("fabien")
    }
  }

  "StringImplicits" should {
    import me.pennequin.fabien.implicits.StringImplicits._

    "add slugify method" in {
      "test".slugify must beEqualTo("test")
    }
  }

}