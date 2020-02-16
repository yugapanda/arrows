package com.hynfias.arrow.graph.infra.effect.melody

import com.hynfias.arrow.graph.domain.arrow.Arrow
import com.hynfias.arrow.graph.infra.effect.Effect
import com.hynfias.arrow.graph.infra.effect.kind.{Bang, EffectKind}
import com.hynfias.arrow.graph.infra.effect.melody.child.Ring
import processing.core.PApplet

case class MelodyArrow(
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
  var children: List[Ring] = Nil

  def draw(implicit p: PApplet): Unit = {
    //    p.fill(color)
    //    p.noStroke()
    //    p.rect(x, y, width, height)

    children.foreach(x => {
      x.draw(p)
      x.update()
    })
    children = children.filter(x => x.isLiving)
  }

  override def bang(content: EffectKind): Unit = content match {
    case Bang(id) if id == this.id => children = Ring(x.toInt, y.toInt, 0, 0, 5) :: children
    case _ =>
  }


}
