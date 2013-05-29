package me.pennequin.fabien
package implicits

class RichString(s:String) {

  def slugify = SlugGenerator.generate(s)

}


object StringImplicits {

  implicit def toRichString(s:String):RichString = new RichString(s)

}
