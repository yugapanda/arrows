package com.hynfias.arrow.graph.infra.shoot.rhythm

import com.hynfias.arrow.graph.infra.effect.Effect
import com.hynfias.arrow.graph.infra.effect.rhythm.RhythmArrow
import com.hynfias.arrow.graph.infra.effect.tempo.TempoArrow
import com.hynfias.arrow.graph.infra.shoot.Shoot
import processing.core.PApplet


case class RhythmShoot(targetId: String, sourceId: String) extends Shoot {

  override def draw(effects: List[Effect])(implicit p: PApplet): Unit = ???

}
