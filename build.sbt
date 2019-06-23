name := "my-ssp"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

resolvers += Resolver.sonatypeRepo("snapshots")

scalaVersion := "2.12.8"

libraryDependencies += guice
libraryDependencies += jdbc
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "4.0.3" % Test
libraryDependencies += "com.typesafe.slick" %% "slick" % "3.2.1"
libraryDependencies += "mysql" % "mysql-connector-java" % "5.1.34"
libraryDependencies += "net.codingwell" %% "scala-guice" % "4.1.0"

scalacOptions ++= Seq(
  "-feature",
  "-deprecation",
  "-Xfatal-warnings"
)
