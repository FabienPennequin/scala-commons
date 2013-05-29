package me.pennequin.fabien

import java.util.regex.Pattern
import java.text.Normalizer

import me.pennequin.fabien.implicits.StringImplicits._

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
    "ø" -> "o",

    // Extras
    "\\." -> "-",
    "_" -> "-"
  ) 

  def generate(input:String):String = {
    var slug = input

    // Whitespace
    slug = slug.trim.toLowerCase
    slug = WHITESPACE.matcher(slug).replaceAll("-")

    // Special chars
    slug = Normalizer.normalize(slug, Normalizer.Form.NFD)
    specialChars.foreach {
      case (key, value) => slug = slug.replaceAll(key, value)
    }

    // All other chars...
    slug = NONLATIN.matcher(slug).replaceAll("")

    // Remove extra dashes
    val isDash:(Char => Boolean) = _ == '-'
    slug.replaceAll("(-){2,}", "-").dropWhile(isDash).dropWhileInverse(isDash)
  }

  def generateUnique(name:String, similarSlugs:(String => List[String])):String = {
    val baseSlug = generate(name)

    var slug = baseSlug
    var existingSlugs = similarSlugs(baseSlug)

    var num = 0
    while (existingSlugs.exists(_ == slug)) {
      num += 1
      slug = baseSlug + "-" + num
    }

    slug
  }

}