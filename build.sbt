name := """rcubed"""
organization := "gsscogs.uk"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.13.1"

libraryDependencies += guice
libraryDependencies += "com.typesafe.play" %% "play-slick" % "5.0.0"
libraryDependencies += "com.typesafe.play" %% "play-slick-evolutions" % "5.0.0"
libraryDependencies += "com.h2database" % "h2" % "1.4.199"
libraryDependencies += specs2 % Test
libraryDependencies += ws

// Adds additional packages into Twirl
//TwirlKeys.templateImports += "gsscogs.uk.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "gsscogs.uk.binders._"
