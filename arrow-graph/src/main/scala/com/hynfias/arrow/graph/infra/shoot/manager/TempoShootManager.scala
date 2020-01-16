package com.hynfias.arrow.graph.infra.shoot.manager

import com.hynfias.arrow.graph.infra.effect.Effect
import com.hynfias.arrow.graph.infra.effect.bass.BassArrow
import com.hynfias.arrow.graph.infra.effect.melody.MelodyArrow
import com.hynfias.arrow.graph.infra.effect.rhythm.RhythmArrow
import com.hynfias.arrow.graph.infra.effect.tempo.TempoArrow
import com.hynfias.arrow.graph.infra.shoot.tempo.TempoShoot

object TempoShootManager extends TShootManager[TempoShoot] {
  def make(id1: String, id2: String): TempoShoot = TempoShoot(id1, id2)

  override def callToIdPair(tArrows: List[TempoArrow],
                            rArrows: List[RhythmArrow],
                            mArrows: List[MelodyArrow],
                            bArrows: List[BassArrow]): (List[Effect], List[Effect]) = {
    (tArrows, bArrows ++ rArrows ++ mArrows)
  }
}
