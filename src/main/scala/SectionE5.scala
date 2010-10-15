package camelinaction 

import java.net.ConnectException

import org.apache.camel.builder.RouteBuilder

import se.scalablesolutions.akka.actor.Actor._
import se.scalablesolutions.akka.actor.Uuid
import se.scalablesolutions.akka.camel._

/**
 * @author Martin Krasser
 */
object SectionE5 extends Application {
  import CamelServiceManager._
  import SampleActors._

  val producer = actorOf[HttpProducer1].start

  startCamelService

  CamelContextManager.mandatoryContext.addRoutes(new CustomRoute(producer.uuid))
  CamelContextManager.mandatoryTemplate.requestBody("direct:test", "feel good", classOf[String]) match {
    case "<received>feel good</received>" => println("communication ok")
    case "feel bad"                       => println("communication failed")
    case _                                => println("unexpected response")
  }

  stopCamelService
  producer.stop
}

class CustomRoute(uuid: Uuid) extends RouteBuilder {
  def configure = {
    from("direct:test")
    .onException(classOf[ConnectException])
      .handled(true)
      .transform.constant("feel bad")
      .end
    .to("actor:uuid:%s" format uuid)
  }
}
