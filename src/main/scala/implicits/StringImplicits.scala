package me.pennequin.fabien
package implicits

class RichString(s:String) {

  import java.util.regex.Pattern
  import java.text.Normalizer

  private val WHITESPACE = Pattern.compile("[\\s]")
  private val NONLATIN = Pattern.compile("[^\\w-]")
  private val specialChars = Map(
    "\\+" -> "plus",
    // Latin-1 Supplement
    "ß" -> "b",
    // Latin Extended-A
    "đ" -> "d",
    "ħ" -> "h",
    "ı" -> "i",
    "ĳ" -> "ij",
    "ĸ" -> "k",
    "ŀ" -> "l",
    "ł" -> "l",
    "ŉ" -> "n",
    "ŋ" -> "n",
    "œ" -> "oe",
    "ŧ" -> "t",
    "ſ" -> "s",
    "€" -> "e",
    "£" -> "l",
    "æ" -> "ae",
    "ø" -> "o"
  ) 

  def slugify = {
    val cleared = s.trim.toLowerCase
    val nowhitespace = WHITESPACE.matcher(cleared).replaceAll("-").replaceAll("--", "-")

    var noaccents = Normalizer.normalize(nowhitespace, Normalizer.Form.NFD)
    specialChars.foreach {
      case (key, value) => noaccents = noaccents.replaceAll(key, value)
    }

    val slug = NONLATIN.matcher(noaccents).replaceAll("")

    slug
  }

}


object StringImplicits {

  implicit def toRichString(s:String):RichString = new RichString(s)

}
