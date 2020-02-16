package com.hynfias.arrow.graph.infra

import com.hynfias.arrow.graph.application.ArrowFactory
import com.hynfias.arrow.graph.domain.arrow.Arrow
import com.hynfias.arrow.graph.domain.way.{Drawable, RealArrow}
import com.hynfias.arrow.graph.infra.effect.Effect
import com.hynfias.arrow.graph.infra.effect.kind.{Bang, RhythmEffect}
import processing.core.PApplet


object Quiver {

  var drawable: List[Effect] =  List[Effect]()

  def get: List[Effect] = drawable

  def add(arrows: List[RealArrow]): Unit =
    drawable = ArrowFactory.make(arrows, drawable)

  def hit(id: String): Unit = drawable.foreach(x => x.bang(Bang(id)))
  def hit(): Unit = drawable.foreach(x => x.bang(RhythmEffect))

}
