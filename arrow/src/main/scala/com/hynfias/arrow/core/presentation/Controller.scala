package com.hynfias.arrow.core.presentation

import akka.http.scaladsl.Http
import ch.megard.akka.http.cors.scaladsl.CorsDirectives._
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import spray.json.DefaultJsonProtocol._
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import com.hynfias.arrow.core.domain.model.RealObject
import com.hynfias.arrow.core.application.ArrowFactory
import com.hynfias.arrow.core.domain.model.arrow.Arrow
import com.hynfias.arrow.core.presentation.model.{Arrows, RealObjects}
import spray.json.RootJsonFormat

import scala.concurrent.Future

object Controller {


  implicit val realObject: RootJsonFormat[RealObject] = jsonFormat3(RealObject.apply)
  implicit val realObjects: RootJsonFormat[RealObjects] = jsonFormat1(RealObjects.apply)
  implicit val arrow: RootJsonFormat[Arrow] = jsonFormat5(Arrow.apply)
  implicit val arrows: RootJsonFormat[Arrows] = jsonFormat1(Arrows.apply)
  implicit val ec =  scala.concurrent.ExecutionContext.global
  val route: Route = cors() {
    path("add") {
      post {
        entity(as[List[RealObject]]) { roList =>
          println(roList)
          Future {
            ArrowFactory.update(roList)
          }
          complete("ok")
        }
      }
    } ~
    path(""){
      get {
        complete(ArrowFactory.getArrows)
      }
    }
  }
}
