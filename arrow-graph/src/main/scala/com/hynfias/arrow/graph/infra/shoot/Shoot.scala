package com.hynfias.arrow.graph.infra.shoot

import com.hynfias.arrow.graph.infra.effect.Effect
import com.hynfias.arrow.graph.infra.effect.kind.{Bang, EffectKind}
import processing.core.PApplet

trait Shoot {
  val targetId: String
  val sourceId: String

  def draw(effects: List[Effect])(implicit p: PApplet): Unit

  def findTargetAndSource(effects: List[Effect]): Option[(Effect, Effect)] = for {
      target <- effects.find(e => e.id == targetId)
      source <- effects.find(e => e.id == sourceId)
    } yield (target, source)

  def bang(effectKind: EffectKind, effects: List[Effect], p: PApplet): Unit
}


