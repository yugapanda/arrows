package com.hynfias.arrow.graph.infra.shoot

import com.hynfias.arrow.graph.infra.effect.Effect
import com.hynfias.arrow.graph.infra.effect.tempo.TempoArrow
import processing.core.PApplet

trait Shoot {
  val targetId: String
  val sourceId: String
  def draw(effects: List[Effect])(implicit p: PApplet): Unit

}


