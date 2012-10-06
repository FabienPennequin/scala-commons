package me.pennequin.fabien
package implicits

import org.joda.time.DateTime

class RichDateTime(d:DateTime) {

  import org.joda.time.format.DateTimeFormat

  def toString(format:String):String = {
    DateTimeFormat.forPattern(format).print(d)
  }

}


object DateTimeImplicits {

  implicit def toRichDateTime(d:DateTime):RichDateTime = new RichDateTime(d)

}
