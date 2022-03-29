ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.8"

lazy val root = (project in file("."))
  .settings(
    name := "example-scala-rust",
    libraryDependencies ++= Seq(
      "com.github.jnr" % "jnr-ffi" % "2.2.11",
      "com.typesafe.akka" %% "akka-actor" % "2.6.19"
    )
  )
