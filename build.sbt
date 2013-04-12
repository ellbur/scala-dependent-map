
organization := "com.github.ellbur"

name := "dependent-map"

version := "1.0-SNAPSHOT"

scalaVersion := "2.10.1"

scalaSource in Compile <<= baseDirectory(_ / "src")

libraryDependencies ++= Seq(
    "com.github.ellbur" %% "dependent-types" % "1.0-SNAPSHOT",
    "org.scalaz" %% "scalaz-core" % "6.0.4"
)

