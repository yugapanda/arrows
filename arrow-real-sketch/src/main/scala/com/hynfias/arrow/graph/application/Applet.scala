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
      if (p.brightness > 50 && p.brightness < 150) {
        fill(0, 255, 255)
        stroke(0, 255, 255)
      } else {
        fill(b)
        stroke(b)
      }

      point(p.x, p.y)
    })

    stroke(120,255,255)
    noFill()
    ell(768400)
    ell(1000)
    ell(768300)
    ell(768500)
    ell(1100)


  }

  def ifZero(int: Int): Int = if (int == 0) 255 else int

  def ell(pos: Int): Unit = ellipse(pos%1280, pos/1280, 100, 100)

  //  def oscEvent(mes: OscMessage): Unit = {
  //
  //  }



}