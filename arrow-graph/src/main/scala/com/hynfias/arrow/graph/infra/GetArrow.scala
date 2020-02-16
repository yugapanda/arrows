package com.hynfias.arrow.graph.infra

import com.hynfias.arrow.graph.domain.way.RealArrow
import io.circe.parser._
import scalaj.http._

//class GetArrow extends Runnable {
////  override def run(): Unit = {
////    while (true) {
////      val url = "http://localhost:8081/"
////      try {
////        decode[List[RealArrow]](Http(url).method("GET").execute().body) match {
////          case Right(x) =>
////            Quiver.add(x)
////          case Left(x) => println(x)
////        }
////      } catch {
////        case e: Exception => println(e)
////        case _ => ()
////      }
////    }
////  }
//}
