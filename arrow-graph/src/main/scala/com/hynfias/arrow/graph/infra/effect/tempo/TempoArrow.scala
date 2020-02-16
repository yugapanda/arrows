package com.hynfias.arrow.graph.infra.effect.tempo

import com.hynfias.arrow.graph.domain.arrow.Arrow
import com.hynfias.arrow.graph.infra.effect.Effect
import com.hynfias.arrow.graph.infra.effect.kind.{Bang, EffectKind, RhythmEffect}
import com.hynfias.arrow.graph.infra.effect.melody.child.Ring
import com.hynfias.arrow.graph.infra.effect.tempo.child.{Particles, RingNoFill}
import processing.core.PApplet

case class TempoArrow (
                      override val id: String,
                      override var x: Float,
                      override var y: Float,
                      override var addX: Float,
                      override var addY: Float,
                      override var left: Int,
                      override var width: Int,
                      override var height: Int,
                      override val arrowType: String
                    ) extends Effect with Arrow {



  var color: Int = Int.MaxValue
  var children: List[RingNoFill] = Nil

  var particles: List[Particles] = (0 until 10).map(part => {
    Particles(x,y, 150, 1 + part, 0, 10, 10, 0, 0)
  }).toList

  def draw(implicit p: PApplet): Unit = {
//    p.fill(color)
//    p.noStroke()
//    p.ellipse(x, y, width, height)
    particles.foreach(x => {
      x.rotate()
      x.setCenter(this)
      x.draw
    })

    children.foreach(x => {
      x.draw(p)
      x.update()
    })
    children = children.filter(x => x.isLiving)
  }

  override def bang(content: EffectKind): Unit = content match {
    case RhythmEffect => children = RingNoFill(x.toInt, y.toInt, 0, 0, 5) :: children
    case _ =>
  }

}
