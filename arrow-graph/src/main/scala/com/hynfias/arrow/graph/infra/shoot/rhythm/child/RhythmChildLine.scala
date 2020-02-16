package com.hynfias.arrow.graph.infra.shoot.rhythm.child

import processing.core.PApplet

case class RhythmChildLine(startX: Int, startY: Int, endX: Int, endY: Int)(implicit p: PApplet) {
  var age = 255
  def draw(): Unit = {
    p.fill(0,0, age)
    p.strokeWeight(5)
    p.line(startX, startY, endX, endY)
    p.strokeWeight(1)
  }

  def update(): Unit = age = age - 10

  def isLive: Boolean = age > 0
}
