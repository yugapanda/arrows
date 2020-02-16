package com.hynfias.arrow.graph.infra.shoot.bass

import com.hynfias.arrow.graph.infra.effect.Effect
import com.hynfias.arrow.graph.infra.effect.bass.BassArrow
import com.hynfias.arrow.graph.infra.effect.kind.EffectKind
import com.hynfias.arrow.graph.infra.effect.melody.MelodyArrow
import com.hynfias.arrow.graph.infra.shoot.Shoot
import processing.core.PApplet

case class BassShoot(targetId: String, sourceId: String) extends Shoot {

  override def draw(effects: List[Effect])(implicit p: PApplet): Unit = ???

  override def bang(effectKind: EffectKind, effects: List[Effect], p: PApplet): Unit = ()
}
