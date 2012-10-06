import sbt._
import sbt.Keys._

object ProjectBuild extends Build {

  lazy val root = Project(
    id = "root",
    base = file("."),
    settings = Project.defaultSettings ++ Seq(
      name          := "scala-commons",
      organization  := "me.pennequin.fabien",
      version       := "1.0-SNAPSHOT",

      scalaVersion  := "2.9.1",
      scalacOptions := Seq("-encoding", "utf8"),

      resolvers     ++= Seq(
      ),

      libraryDependencies ++= Seq(
        "joda-time" % "joda-time" % "2.1",
        "org.joda" % "joda-convert" % "1.2",
        "org.specs2" %% "specs2" % "1.12.1" % "test"
      )
    )
  )

}
