package com.hynfias.arrow.graph.infra.effect

import com.hynfias.arrow.graph.domain.arrow.Arrow
import com.hynfias.arrow.graph.infra.effect.kind.EffectKind
import processing.core.PApplet

trait Effect extends Arrow {

  def bang(content: EffectKind): Unit
  def draw(implicit p: PApplet): Unit

}
