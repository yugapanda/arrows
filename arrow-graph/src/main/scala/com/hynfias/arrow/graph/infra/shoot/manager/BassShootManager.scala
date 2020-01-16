package com.hynfias.arrow.graph.infra.shoot.manager

import com.hynfias.arrow.graph.infra.effect.Effect
import com.hynfias.arrow.graph.infra.effect.bass.BassArrow
import com.hynfias.arrow.graph.infra.effect.melody.MelodyArrow
import com.hynfias.arrow.graph.infra.effect.rhythm.RhythmArrow
import com.hynfias.arrow.graph.infra.effect.tempo.TempoArrow
import com.hynfias.arrow.graph.infra.shoot.bass.BassShoot

object BassShootManager extends TShootManager[BassShoot] {

  override def make(id1: String, id2: String): BassShoot = BassShoot(id1, id2)

  override def callToIdPair(tArrows: List[TempoArrow],
                            rArrows: List[RhythmArrow],
                            mArrows: List[MelodyArrow],
                            bArrows: List[BassArrow]): (List[Effect], List[Effect]) = {
    (bArrows, tArrows ++ rArrows ++ mArrows)
  }

}
