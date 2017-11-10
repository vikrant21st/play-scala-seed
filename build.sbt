name := """play-scala-seed"""
organization := "com.example"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.12.3"

libraryDependencies += guice

PlayKeys.devSettings := Seq("play.server.http.port" -> "9010")

libraryDependencies += "com.typesafe.play" %% "play-slick" %  "3.0.2"
libraryDependencies += "com.typesafe.play" %% "play-slick-evolutions" % "3.0.2"
libraryDependencies += "com.h2database" % "h2" % "1.4.194"

libraryDependencies ++= Seq(
    //ehcache,
    jdbc,
    //anorm,
    //My dependencies
	"org.postgresql" % "postgresql" % "9.3-1100-jdbc41")
	