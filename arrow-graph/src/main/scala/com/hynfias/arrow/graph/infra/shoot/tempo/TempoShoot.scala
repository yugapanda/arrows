package com.hynfias.arrow.graph.infra.shoot.tempo

import com.hynfias.arrow.graph.infra.effect.Effect
import com.hynfias.arrow.graph.infra.effect.kind.{Bang, EffectKind, RhythmEffect}
import com.hynfias.arrow.graph.infra.shoot.Shoot
import com.hynfias.arrow.graph.infra.shoot.tempo.child.{ArrowTypeTempoChild, TempoShootChild}
import processing.core.PApplet

case class TempoShoot(targetId: String, sourceId: String) extends Shoot {

  var children: List[TempoShootChild] = Nil

  override def draw(effects: List[Effect])(implicit p: PApplet): Unit = findTargetAndSource(effects).foreach(tas => {
    val (target, source) = tas
    children.foreach { c =>
      c.draw(p)
      c.update(source.x, source.y, target.x, target.y)
    }
    children = children.filter(_.isLive)
  })

  def add(id: String, effects: List[Effect], p: PApplet): Unit = findTargetAndSource(effects).foreach(tas => {
    val (target, source) = tas
    if (id == target.id) {
      children = ArrowTypeTempoChild(
        source.x,
        source.y,
        target.x,
        target.y,
        p.color(0, 0, 255)) :: children
    }
  })


  override def bang(effectKind: EffectKind, effects: List[Effect], p: PApplet): Unit = effectKind match {
    case Bang(id) => add(id, effects, p)
    case RhythmEffect =>
    case _ => ()
  }
}
