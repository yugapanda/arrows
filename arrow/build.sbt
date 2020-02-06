name := "arrow-core"

version := "0.1"

scalaVersion := "2.13.1"

libraryDependencies ++= Seq(
  "com.typesafe" % "config" % "1.3.2",
  "io.circe" %% "circe-core" % "0.12.3",
  "io.circe" %% "circe-parser" % "0.12.3",
  "io.circe" %% "circe-generic" % "0.12.3",
  "de.sciss" %% "scalaosc" % "1.2.1"
)
