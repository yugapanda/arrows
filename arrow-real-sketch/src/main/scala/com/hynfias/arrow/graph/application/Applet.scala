package com.hynfias.arrow.graph.application

import processing.core.PApplet
import processing.core.PConstants._
import scala.util.chaining._

class Applet extends PApplet {

  implicit val p: PApplet = this
  var points: List[Point] = Nil
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


    points = loadJSONArray("frame.json").getIntArray
      .zipWithIndex
      .map(p => {
        val x: Int = p._2 % 1280
        val y: Int = p._2 / 1280
        Point(x, y, p._1)
      }).toList

  }

  override def draw(): Unit = {
    background(0)
    points.foreach(p => {
      val b = PApplet.map(p.brightness, 0, 1000, 0, 255).toInt.pipe(ifZero)
      if (b > 10 && b < 50) {
        fill(0, 255, 255)
        stroke(0, 255, 255)
      } else {
        fill(b)
        stroke(b)
      }

      point(p.x, p.y)
    })


  }

  def ifZero(int: Int): Int = if (int == 0) 255 else int

  //  def oscEvent(mes: OscMessage): Unit = {
  //
  //  }

}