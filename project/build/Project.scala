import sbt._

class Project(info: ProjectInfo) extends DefaultProject(info) with AkkaProject {
  val AkkaRepo = "Akka Repository" at "http://akka.io/repository/"

  val akkaCamel = akkaModule("camel")
  val akkaKernel = akkaModule("spring")

  val camelCore = "org.apache.camel" % "camel-core" % "2.8.1" % "compile"
  val camelJetty = "org.apache.camel" % "camel-jetty" % "2.8.1" % "compile"
  val camelSpring = "org.apache.camel" % "camel-spring" % "2.8.1" % "compile"
  val camelStream = "org.apache.camel" % "camel-stream" % "2.8.1" % "compile"
  val log4jOverSlf4j = "org.slf4j" % "log4j-over-slf4j" % "1.6.1" % "runtime"
}
