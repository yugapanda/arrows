package com.hynfias.arrow.graph.infra.effect.tempo.child

import com.hynfias.arrow.graph.domain.way.{Rotatable, Sizable}
import processing.core.PApplet

case class Particles(override var centerX: Float,
                     override var centerY: Float,
                     override var centerDistance: Float,
                     override var rotateSpeed: Float,
                     override var rad: Float,
                     override var width: Int,
                     override var height: Int,
                     override var x: Float,
                     override var y: Float) extends Rotatable with Sizable {

  def draw(implicit p: PApplet): Unit = {
    val (xPos,yPos) = getPos
    p.ellipse(xPos.toFloat, yPos.toFloat, width, height)
  }

}
