name := "my-ssp"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

resolvers += Resolver.sonatypeRepo("snapshots")

scalaVersion := "2.12.8"

libraryDependencies += guice
libraryDependencies += ws
libraryDependencies += jdbc
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "4.0.3" % Test
libraryDependencies += "com.typesafe.slick" %% "slick" % "3.2.1"
libraryDependencies += "mysql" % "mysql-connector-java" % "8.0.13"
libraryDependencies += "org.joda" % "joda-convert" % "1.8"
libraryDependencies += "net.logstash.logback" % "logstash-logback-encoder" % "4.9"
libraryDependencies += "com.netaporter" %% "scala-uri" % "0.4.16"
libraryDependencies += "net.codingwell" %% "scala-guice" % "4.1.0"

scalacOptions ++= Seq(
  "-feature",
  "-deprecation",
  "-Xfatal-warnings"
)
