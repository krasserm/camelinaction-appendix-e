import sbt._

class Plugins(info: ProjectInfo) extends PluginDefinition(info) {
  val akkaPlugin = "akka" % "akka-sbt-plugin" % "1.0-SNAPSHOT"
}

