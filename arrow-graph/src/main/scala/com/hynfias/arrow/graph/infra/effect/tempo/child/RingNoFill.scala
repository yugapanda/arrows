package com.hynfias.arrow.graph.infra.effect.tempo.child

import com.hynfias.arrow.graph.domain.way.Drawable
import processing.core.PApplet

case class RingNoFill(override var x: Float,
                      override var y: Float,
                      override var width: Int,
                      override var height: Int,

                      aging: Int) extends Drawable {

  val hue: Float = 0f
  val saturation: Int = 0
  val brightness: Int = 255
  var alpha: Int = 255

  def draw(implicit p: PApplet): Unit = {
    p.stroke(hue, saturation, brightness, alpha)
    p.noFill()
    p.ellipse(x, y, width, height)
  }

  def update(): Unit = {
    width = width + 5
    height = height + 5
    alpha = alpha - aging
  }

  def isLiving: Boolean = alpha > 0
}
