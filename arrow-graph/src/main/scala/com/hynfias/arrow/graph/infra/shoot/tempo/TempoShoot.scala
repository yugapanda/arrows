package com.hynfias.arrow.graph.infra.shoot.tempo

import com.hynfias.arrow.graph.infra.effect.Effect
import com.hynfias.arrow.graph.infra.shoot.Shoot
import com.hynfias.arrow.graph.infra.shoot.tempo.child.{ArrowTypeTempoChild, TempoShootChild}
import processing.core.PApplet

case class TempoShoot(targetId: String, sourceId: String) extends Shoot {

  var children: List[TempoShootChild] = Nil

  override def draw(effects: List[Effect])(implicit p: PApplet): Unit = findTargetAndSource(effects).foreach(tas => {
    val (target, source) = tas
    if(p.frameCount % 30 == 0) {
      children = ArrowTypeTempoChild(
        source.x,
        source.y,
        target.x,
        target.y,
        p.color(100, 255, 255))::children
    }

    children.foreach {c =>
      c.draw(p)
      c.update(source.x, source.y, target.x, target.y)
    }
    children = children.filter(_.isLive)
  })

}
