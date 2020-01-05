package com.hynfias.arrow.graph.application

import com.hynfias.arrow.graph.domain.arrow.Arrow
import com.hynfias.arrow.graph.domain.way.RealArrow
import com.hynfias.arrow.graph.infra.effect.Effect
import com.hynfias.arrow.graph.infra.effect.bass.BassArrow
import com.hynfias.arrow.graph.infra.effect.melody.MelodyArrow
import com.hynfias.arrow.graph.infra.effect.rhythm.RhythmArrow
import com.hynfias.arrow.graph.infra.effect.tempo.TempoArrow
import processing.core.PApplet

object ArrowFactory {

  def make(arrows: List[RealArrow], oldList: List[Arrow]): List[Effect] = zipByIdAndType(arrows, oldList)
    .foldLeft(List[Effect]())((acc, now) => now match {
      case (x, Some(y: Effect)) =>
        y.setTarget(10, x)
        y :: acc
      case (x, None) if x.arrowType == "TempoArrow" =>
        TempoArrow(x.id, x.x, x.y, 0, 0, 0, x.h, x.h, "TempoArrow") :: acc
      case (x, None) if x.arrowType == "RhythmArrow" =>
        RhythmArrow(x.id, x.x, x.y, 0, 0, 0, x.h, x.h, "RhythmArrow") :: acc
      case (x, None) if x.arrowType == "BassArrow" =>
        BassArrow(x.id, x.x, x.y, 0, 0, 0, x.h, x.h, "BassArrow") :: acc
      case (x, None) if x.arrowType == "MelodyArrow" =>
        MelodyArrow(x.id, x.x, x.y, 0, 0, 0, x.h, x.h, "MelodyArrow") :: acc
    })


  protected def zipByIdAndType[A <: Arrow](arrows: List[RealArrow], oldList: List[A]): List[(RealArrow, Option[A])] =
    arrows.iterator.map(x => (x, oldList.find(y => y.id == x.id && y.arrowType == x.arrowType))).toList


}
