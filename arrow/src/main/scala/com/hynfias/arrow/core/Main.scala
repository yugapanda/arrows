package com.hynfias.arrow.core

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer
import com.hynfias.arrow.core.presentation.Controller.route

import scala.concurrent.ExecutionContext.Implicits.global
import scala.io.StdIn

object Main extends App {

  // needed to run the route
  implicit val system = ActorSystem()

  // needed for the future map/flatmap in the end and future in fetchItem and saveOrder
  implicit val executionContext = system.dispatcher

  implicit val materializer = ActorMaterializer()



  val bindingFuture = Http().bindAndHandle(route, "0.0.0.0", 8081)
  println(s"Server online at http://localhost:8081/\nPress RETURN to stop...")
  StdIn.readLine() // let it run until user presses return
  bindingFuture
    .flatMap(_.unbind()) // trigger unbinding from the port
    .onComplete(_ => {
      system.terminate()
    }) // and shutdown when done




}
