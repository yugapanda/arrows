package com.hynfias.arrow.graph.application

import com.hynfias.arrow.graph.domain.way.{RealArrow, RealArrows}
import com.hynfias.arrow.graph.infra.Quiver
import com.hynfias.arrow.graph.infra.effect.Effect
import io.circe.parser.decode
import processing.core.PApplet
import processing.core.PConstants._
import oscP5.{OscMessage, _}

class Applet extends PApplet {

  implicit val p: PApplet = this

  val osc = new OscP5(this, 12002)

  override def settings(): Unit = {
    fullScreen(P2D)
  }


  override def setup(): Unit = {

    colorMode(HSB)
    rectMode(CENTER)
    surface.setTitle("arrow graph")
    smooth()
    frameRate(30)
    background(0)
    blendMode(ADD)
    frameRate(60)

  }

  override def draw(): Unit = {
    background(0)

    try {
      stroke(255)
      fill(255)
      val arrows: List[Effect] = Quiver.get

      arrows.foreach(x => {
        x.move()
        x.draw
      })

      ShootService.update(arrows)
      ShootService.draw(arrows)

      // println(Quiver.get.toList)
    } catch {
      case e: Exception => println(e)
      case _ => ()
    }
  }



    def oscEvent(mes: OscMessage): Unit = {

      //println(mes.get(0).stringValue().replace("\n", "").replace(" ", ""))

      mes.addrPattern() match {
        case "/to" => decode[RealArrows](mes.get(0).stringValue()) match {
          case Right(x) =>
            println(x)
            Quiver.add(x.arrows)
          case Left(x) => println(x)
        }
        case "/graph" =>
          Quiver.hit(mes.get(0).stringValue())
          ShootService.bang(mes.get(0).stringValue(), Quiver.get, this)
        case "/tempo" => Quiver.hit()
        case _ => ()
      }
    }

}