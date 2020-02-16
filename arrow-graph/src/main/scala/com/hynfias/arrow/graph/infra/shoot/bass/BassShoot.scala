package com.hynfias.arrow.graph.infra.shoot.bass

import com.hynfias.arrow.graph.infra.effect.Effect
import com.hynfias.arrow.graph.infra.effect.bass.BassArrow
import com.hynfias.arrow.graph.infra.effect.kind.EffectKind
import com.hynfias.arrow.graph.infra.effect.melody.MelodyArrow
import com.hynfias.arrow.graph.infra.shoot.Shoot
import processing.core.{PApplet, PVector}

case class BassShoot(targetId: String, sourceId: String) extends Shoot {

  override def draw(effects: List[Effect])(implicit p: PApplet): Unit = findTargetAndSource(effects).foreach(tas => {
    val (target, source) = tas
    wave(source, target)
  })

  def wave(source: Effect, target: Effect)(implicit p: PApplet): Unit = {
    val diffX = (target.x - source.x) / 100
    val diffY = (target.y - source.y) / 100
    Range(0, 100).map(x => (target.x + (diffX * x), target.y + (diffY * x)))
      .grouped(2)
      .foreach{x =>
        for {
          h <- x.headOption
          l <- x.lastOption
      } yield p.line(h._1, h._2, l._1, l._2)
      }







  }

  override def bang(effectKind: EffectKind, effects: List[Effect], p: PApplet): Unit = ()
}
