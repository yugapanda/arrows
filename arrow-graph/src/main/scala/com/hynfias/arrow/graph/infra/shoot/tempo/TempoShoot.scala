package com.hynfias.arrow.graph.infra.shoot.tempo

import com.hynfias.arrow.graph.infra.effect.Effect
import com.hynfias.arrow.graph.infra.shoot.Shoot
import processing.core.PApplet

case class TempoShoot(targetId: String, sourceId: String) extends Shoot {
  override def draw(effects: List[Effect])(implicit p: PApplet): Unit = ???
}
