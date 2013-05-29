package me.pennequin.fabien
package implicits

class RichString(s:String) {

  def slugify = SlugGenerator.generate(s)

  def dropWhileInverse(p: Char => Boolean): String = s.dropRight(suffixLength(p, true))

  private def suffixLength(p: Char => Boolean, expectTrue: Boolean): Int = {
    var i = 0
    while (i < s.length && p(s.apply(s.length - i - 1)) == expectTrue) i += 1
    i
  }

}


object StringImplicits {

  implicit def toRichString(s:String):RichString = new RichString(s)

}
