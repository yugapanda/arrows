package com.hynfias.arrow.graph.infra.shoot.tempo.child

import processing.core.PApplet

case class ArrowTypeTempoChild(override var x1: Float,
                               override var y1: Float,
                               override var x2: Float,
                               override var y2: Float,
                               override var color: Int) extends TempoShootChild {

  var nowX: Float = x1
  var nowY: Float = y1

  override def draw(p: PApplet): Unit = {
    p.fill(color)
    p.stroke(color)
    p.ellipse(nowX, nowY, 50, 50)
  }

  override def update(x1: Float, y1: Float, x2: Float, y2: Float): Unit = {
    nowX = PApplet.map(count, 0, lifetime, x1, x2)
    nowY = PApplet.map(count, 0, lifetime, y1, y2)
    count = count + 1
  }
}
