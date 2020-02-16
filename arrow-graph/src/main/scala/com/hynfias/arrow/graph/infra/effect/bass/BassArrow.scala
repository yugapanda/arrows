package com.hynfias.arrow.graph.infra.effect.bass

import com.hynfias.arrow.graph.domain.arrow.Arrow
import com.hynfias.arrow.graph.infra.effect.Effect
import com.hynfias.arrow.graph.infra.effect.kind.{Bang, EffectKind}
import processing.core.PApplet

case class BassArrow(
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

  def draw(implicit p: PApplet): Unit = {

  }

  override def bang(content: EffectKind): Unit = content match {
    case Bang(x) => if (x == id) {
    }
    case _ => ()
  }


}
