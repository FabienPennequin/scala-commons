package test

import org.specs2.mutable._

import me.pennequin.fabien.SlugGenerator

class SlugGeneratorSpec extends Specification {

  "SlugGenerator.generate method" should {

    "remove extra spaces" in {
      SlugGenerator.generate(" ") must beEqualTo("")
      SlugGenerator.generate("   ") must beEqualTo("")
      SlugGenerator.generate(" fabien ") must beEqualTo("fabien")
    }

    "change case to lower" in {
      SlugGenerator.generate("FaBiEn") must beEqualTo("fabien")
    }

    "convert space to dash" in {
      SlugGenerator.generate("f a b") must beEqualTo("f-a-b")
      SlugGenerator.generate("f  a  b") must beEqualTo("f-a-b")
      SlugGenerator.generate(" f a b ") must beEqualTo("f-a-b")
    }

    "convert accented character" in {
      // Latin-1 Supplement
      SlugGenerator.generate("ÀÁÂÃÄÅÇÈÉÊËÌÍÎÏÑÒÓÔÕÖÙÚÛÜÝß") must beEqualTo("aaaaaaceeeeiiiinooooouuuuyb")
      SlugGenerator.generate("àáâãäåçèéêëìíîïñòóôõöùúûüýÿ") must beEqualTo("aaaaaaceeeeiiiinooooouuuuyy")

      // Latin Extended-A
      SlugGenerator.generate("ĀāĂăĄąĆćĈĉĊċČčĎďĐđĒēĔĕĖėĘęĚěĜĝĞğĠġĢģĤĥ") must beEqualTo("aaaaaaccccccccddddeeeeeeeeeegggggggghh")
      SlugGenerator.generate("ĦħĨĩĪīĬĭĮįİıĲĳĴĵĶķĸĹĺĻļĽľĿŀŁłŃńŅņŇňŉŊŋ") must beEqualTo("hhiiiiiiiiiiijijjjkkkllllllllllnnnnnnnnn")
      SlugGenerator.generate("ŌōŎŏŐőŒœŔŕŖŗŘřŚśŜŝŞşŠšŢţŤťŦŧŨũŪūŬŭŮůŰűŲųŴŵŶŷŸŹźŻżŽžſ") must beEqualTo("oooooooeoerrrrrrssssssssttttttuuuuuuuuuuuuwwyyyzzzzzzs")

      // Others
      SlugGenerator.generate("ÄäÜüÖöß") must beEqualTo("aauuoob")
      // Norwegian characters
      SlugGenerator.generate("ÅÆØæøå") must beEqualTo("aaeoaeoa")
    }

    "convert some special character" in {
      // Plus
      SlugGenerator.generate("+") must beEqualTo("plus")
      // Euro Sign
      SlugGenerator.generate("€") must beEqualTo("e")
      // GBP (Pound) Sign
      SlugGenerator.generate("£") must beEqualTo("l")
    }

    "replace tab and CR" in {
      SlugGenerator.generate("f\na\tb") must beEqualTo("f-a-b")
      SlugGenerator.generate("\nf\n\na\tb\t\ti\ne\tn") must beEqualTo("f-a-b-i-e-n")
    }

    "remove all others special chars" in {
      SlugGenerator.generate("Fab??ien!!!") must beEqualTo("fabien")
      SlugGenerator.generate("Fabi(e)n") must beEqualTo("fabien")
      SlugGenerator.generate("Fab{ien}") must beEqualTo("fabien")
    }

    "remove extra dashes" in {
      SlugGenerator.generate("Scala !") must beEqualTo("scala")
      SlugGenerator.generate("Scala !!!") must beEqualTo("scala")
      SlugGenerator.generate("Scala : 2.10.0") must beEqualTo("scala-2100")
      SlugGenerator.generate("Scala   :   2.10.0") must beEqualTo("scala-2100")
    }
  }

}