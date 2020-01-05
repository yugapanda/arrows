name := "arrow-core"

version := "0.1"

scalaVersion := "2.13.1"

libraryDependencies ++= Seq(
  "com.typesafe" % "config" % "1.3.2",
  "com.typesafe.akka" %% "akka-http" % "10.1.10",
  "com.typesafe.akka" %% "akka-stream" % "2.6.0",
  "com.typesafe.akka" %% "akka-http-spray-json" % "10.1.10",
  "ch.megard" %% "akka-http-cors" % "0.4.2"
)
