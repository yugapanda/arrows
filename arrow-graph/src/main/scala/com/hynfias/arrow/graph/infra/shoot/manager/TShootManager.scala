package com.hynfias.arrow.graph.infra.shoot.manager

import com.hynfias.arrow.graph.domain.arrow.Arrow
import com.hynfias.arrow.graph.infra.effect.Effect
import com.hynfias.arrow.graph.infra.shoot.Shoot

trait TShootManager {

  protected def toIdPair[A <: Arrow](mArrows: List[A], targets: List[Effect]): List[IdPair] =
    mArrows.flatMap(m => targets.map(t => IdPair(m.id, t.id)))

  /**
   * MelodyArrowとOtherArrowのidのペアの集合と、Shootのtargetとsourceのペアの集合を引数に取り、
   * どちらにもあるもの、Arrowの集合にしかないもの、Shootの集合にしかないものを返す
   *
   * @param effectsIdPairs
   * @param shootsIdPairs
   * @return
   */
  protected def separate[A <: Shoot](effectsIdPairs: List[IdPair],
                       shootsIdPairs: List[A]): (List[IdPair], List[IdPair]) = {
    val selfShootIdPair = shootsIdPairs.map(x => IdPair(x.sourceId, x.targetId))
    val exists = effectsIdPairs
      .filter { idp =>
        selfShootIdPair.exists { shoot =>
          idp.target == shoot.target && idp.source == shoot.source
        }
      }

    val onlyEffects = effectsIdPairs.diff(selfShootIdPair)

    (exists, onlyEffects)
  }
}
