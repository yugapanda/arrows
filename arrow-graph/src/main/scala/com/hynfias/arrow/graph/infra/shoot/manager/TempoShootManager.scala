package com.hynfias.arrow.graph.infra.shoot.manager

import com.hynfias.arrow.graph.infra.effect.bass.BassArrow
import com.hynfias.arrow.graph.infra.effect.melody.MelodyArrow
import com.hynfias.arrow.graph.infra.effect.rhythm.RhythmArrow
import com.hynfias.arrow.graph.infra.effect.tempo.TempoArrow
import com.hynfias.arrow.graph.infra.shoot.tempo.TempoShoot

object TempoShootManager extends TShootManager {

  def update(shoots: List[TempoShoot])(
    implicit tArrows: List[TempoArrow],
    rArrows: List[RhythmArrow],
    mArrows: List[MelodyArrow],
    bArrows: List[BassArrow],
  ): List[TempoShoot] = {

    val ma2others: List[IdPair] = toIdPair(mArrows, tArrows ++ rArrows ++ bArrows)

    val (exist, onlyEffects) = separate(ma2others, shoots)
    val stayShoots = shoots.filter(x => exist.exists(p => p.source == x.sourceId && p.target == x.targetId))
    val newShoots = onlyEffects.map(x => TempoShoot(x.target, x.source))

    stayShoots ++ newShoots
  }

}
