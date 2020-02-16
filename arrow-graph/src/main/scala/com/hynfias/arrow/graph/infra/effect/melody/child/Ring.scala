package com.hynfias.arrow.graph.infra.effect.melody.child

import com.hynfias.arrow.graph.domain.way.{Colorable, Drawable}
import processing.core.PApplet

case class Ring(override var x: Float,
                override var y: Float,
                override var width: Int,
                override var height: Int,

                aging: Int) extends Drawable {

  val hue: Float = (Math.random() * 255).toFloat
  val saturation: Int = 255
  val brightness: Int = 255
  var alpha: Int = 255

  def draw(implicit p: PApplet): Unit = {
    p.stroke(hue, saturation, brightness, alpha)
    p.fill(hue, saturation, brightness, alpha)
    p.ellipse(x, y, width, height)
  }

  def update(): Unit = {
    width = width + 5
    height = height + 5
    alpha = alpha - aging
  }

  def isLiving: Boolean = alpha > 0
}
