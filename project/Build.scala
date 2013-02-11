import sbt._
import sbt.Keys._

object ProjectBuild extends Build {

  lazy val root = Project(
    id = "root",
    base = file("."),
    settings = Project.defaultSettings ++ Seq(
      name          := "scala-commons",
      organization  := "me.pennequin.fabien",
      version       := "1.1-SNAPSHOT",

      scalaVersion  := "2.10.0",
      scalacOptions := Seq("-encoding", "utf8"),

      resolvers     ++= Seq(
      ),

      libraryDependencies ++= Seq(
        "joda-time" % "joda-time" % "2.1",
        "org.joda" % "joda-convert" % "1.2",
        "org.specs2" %% "specs2" % "1.13" % "test"
      )
    )
  )

}
