package com.hynfias.arrow.graph.application

import com.hynfias.arrow.graph.infra.Quiver
import com.hynfias.arrow.graph.infra.shoot.bass.BassShoot
import com.hynfias.arrow.graph.infra.shoot.melody.MelodyShoot
import com.hynfias.arrow.graph.infra.shoot.rhythm.RhythmShoot
import com.hynfias.arrow.graph.infra.shoot.tempo.TempoShoot
import processing.core.PApplet
import processing.core.PConstants._

class Applet extends PApplet {


  val shoots = List(TempoShoot, RhythmShoot, BassShoot, MelodyShoot)

//  val osc = new OscP5(this, 14445)

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

    implicit val p: PApplet = this


    try {
      stroke(255)
      fill(255)
      val arrows = Quiver.get

      arrows.foreach(x => {
        x.move()
        x.draw
      })

      shoots.foreach(_.draw(arrows))

     // println(Quiver.get.toList)
    } catch {
      case e: Exception => println(e)
      case _ => ()
    }
  }

//  def oscEvent(mes: OscMessage): Unit = {
//
//  }

}