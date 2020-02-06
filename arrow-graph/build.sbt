import Dependencies._

lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "com.hynfias",
      scalaVersion := "2.13.1"
    )),
    name := "arrow-graph",
    libraryDependencies += "de.xypron.statistics" % "statistics" % "1.0.9",
    libraryDependencies += "com.typesafe" % "config" % "1.3.4",
    mainClass in assembly := Some("com.hynfias.arrow.graph.Main"),
    unmanagedResourceDirectories in Compile += baseDirectory.value / "lib",
    libraryDependencies +=  "org.scalaj" %% "scalaj-http" % "2.4.2",
    libraryDependencies += "io.circe" %% "circe-core" % "0.12.3",
    libraryDependencies += "io.circe" %% "circe-parser" % "0.12.3",
    libraryDependencies += "io.circe" %% "circe-generic" % "0.12.3",
    libraryDependencies += "de.sciss" %% "scalaosc" % "1.2.1"

  )
