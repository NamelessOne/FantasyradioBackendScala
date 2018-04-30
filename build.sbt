name := """FantasyradioBackend"""
organization := "com.example"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.12.4"

libraryDependencies += guice
libraryDependencies += jdbc
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "3.1.2" % Test
libraryDependencies += "org.postgresql" % "postgresql" % "9.4-1206-jdbc42"
libraryDependencies += "org.playframework.anorm" %% "anorm" % "2.6.1"
libraryDependencies += "joda-time" % "joda-time" % "2.9.9"
libraryDependencies += "com.google.code.gson" % "gson" % "2.8.1"
libraryDependencies += "com.fatboyindustrial.gson-jodatime-serialisers" % "gson-jodatime-serialisers" % "1.0.0"