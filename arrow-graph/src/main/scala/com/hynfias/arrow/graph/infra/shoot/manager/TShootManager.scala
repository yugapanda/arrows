package com.hynfias.arrow.graph.infra.shoot.manager

import com.hynfias.arrow.graph.domain.arrow.Arrow
import com.hynfias.arrow.graph.infra.effect.Effect
import com.hynfias.arrow.graph.infra.effect.bass.BassArrow
import com.hynfias.arrow.graph.infra.effect.melody.MelodyArrow
import com.hynfias.arrow.graph.infra.effect.rhythm.RhythmArrow
import com.hynfias.arrow.graph.infra.effect.tempo.TempoArrow
import com.hynfias.arrow.graph.infra.shoot.Shoot


trait TShootManager[T <: Shoot] {

  def update(shoots: List[T])
            (implicit tArrows: List[TempoArrow],
             rArrows: List[RhythmArrow],
             mArrows: List[MelodyArrow],
             bArrows: List[BassArrow],
            ): List[T] = {

    val (main, other) = callToIdPair(tArrows, rArrows, mArrows, bArrows)
    val ma2others: List[IdPair] = toIdPair(main, other)

    val (exist, onlyEffects) = separate(ma2others, shoots)
    val stayShoots = shoots.filter(x => exist.exists(p => p.source == x.sourceId && p.target == x.targetId))
    val newShoots = onlyEffects.map(x => make(x.target, x.source))

    stayShoots ++ newShoots
  }

  def make(id1: String, id2: String): T

  def callToIdPair(tArrows: List[TempoArrow],
                   rArrows: List[RhythmArrow],
                   mArrows: List[MelodyArrow],
                   bArrows: List[BassArrow]): (List[Effect], List[Effect])

  protected def toIdPair[A <: Arrow](mArrows: List[A], targets: List[Effect]): List[IdPair] = for {
    m <- mArrows
    t <- targets
  } yield IdPair(m.id, t.id)

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
