package me.pennequin.fabien

import java.util.regex.Pattern
import java.text.Normalizer

object SlugGenerator {

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

  def generate(input:String):String = {
    var slug = input

    // Whitespace
    slug = slug.trim.toLowerCase
    slug = WHITESPACE.matcher(slug).replaceAll("-").replaceAll("--", "-")

    // Special chars
    slug = Normalizer.normalize(slug, Normalizer.Form.NFD)
    specialChars.foreach {
      case (key, value) => slug = slug.replaceAll(key, value)
    }

    // All other chars...
    slug = NONLATIN.matcher(slug).replaceAll("")

    slug
  }

}