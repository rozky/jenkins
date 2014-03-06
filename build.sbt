name := "jenkins"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  "net.databinder.dispatch" %% "dispatch-core" % "0.11.0",
  "org.scalatest" %% "scalatest" % "2.0" % "test"
)     

play.Project.playScalaSettings
