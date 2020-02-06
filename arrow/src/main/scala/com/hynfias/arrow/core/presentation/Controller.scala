package com.hynfias.arrow.core.presentation

import java.net.{InetSocketAddress, SocketAddress}

import com.hynfias.arrow.core.application.ArrowFactory
import com.hynfias.arrow.core.domain.model.RealObject
import com.hynfias.arrow.core.presentation.model.RealObjects
import de.sciss.osc
import de.sciss.osc.{Message, UDP}
import de.sciss.osc.UDP.Client
import de.sciss.osc.UDP.Receiver.Undirected
import io.circe.parser._
import io.circe.syntax._

object Controller {

  val conf: UDP.ConfigBuilder = UDP.Config()
  conf.localPort = 12001
  val server: Undirected = osc.UDP.Receiver(conf)
  val client: Client = osc.UDP.Client(new InetSocketAddress("127.0.0.1",12002))

  server.connect()
  client.connect()

  def exec(): Unit = {

    server.action = {
      // match against a particular message
      case (osc.Message("/add", s: String), _) =>
        println(s)
        decode[List[RealObject]](s) match {
          case Right(roList) =>
            ArrowFactory.update(roList)
            println(ArrowFactory.getArrows.asJson.toString())
            client ! Message("/to", ArrowFactory.getArrows.asJson.toString())
          case Left(error) =>
            println(println(ArrowFactory.getArrows))
            println(error)
        }

      case (osc.Message("/", s: String), _) =>
        println("other")
      case x =>
        println(x)
    }
  }

  def end(): Unit = {
    server.close()
    client.close()
  }
}
