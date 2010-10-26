import sbt._

class Project(info: ProjectInfo) extends DefaultProject(info) with AkkaProject {
  val akkaHome = Path.fromFile(System.getenv("AKKA_HOME"))
  val embeddedRepo = MavenRepository("eer",  (akkaHome / "embedded-repo").asURL.toString)
  val camelStaging = MavenRepository("camel-staging", "https://repository.apache.org/content/repositories/orgapachecamel-004/")

  override val sjsonModuleConfig       = ModuleConfiguration("sjson.json", embeddedRepo)

  val akkaCamel = akkaModule("camel")
  val akkaKernel = akkaModule("spring")

  val camelJetty = "org.apache.camel" % "camel-jetty" % "2.5.0" % "compile"
  val camelSpring = "org.apache.camel" % "camel-spring" % "2.5.0" % "compile"
  val log4jOverSlf4j = "org.slf4j" % "log4j-over-slf4j" % "1.6.1" % "runtime"

  // Temporary workaround to ignore logback.xml in akka-core_2.8.0-0.10.jar
  System.setProperty("logback.configurationFile", "src/main/resources/logback.xml")
}
