package com.hynfias.arrow.graph.application

import com.hynfias.arrow.graph.infra.effect.Effect
import com.hynfias.arrow.graph.infra.effect.bass.BassArrow
import com.hynfias.arrow.graph.infra.effect.melody.MelodyArrow
import com.hynfias.arrow.graph.infra.effect.rhythm.RhythmArrow
import com.hynfias.arrow.graph.infra.effect.tempo.TempoArrow
import com.hynfias.arrow.graph.infra.shoot.bass.BassShoot
import com.hynfias.arrow.graph.infra.shoot.manager.{BassShootManager, MelodyShootManager, RhythmShootManager, TempoShootManager}
import com.hynfias.arrow.graph.infra.shoot.melody.MelodyShoot
import com.hynfias.arrow.graph.infra.shoot.rhythm.RhythmShoot
import com.hynfias.arrow.graph.infra.shoot.tempo.TempoShoot
import processing.core.PApplet

object ShootService {

  private var mShoots: List[MelodyShoot] = Nil
  private var tShoots: List[TempoShoot] = Nil
  private var rShoots: List[RhythmShoot] = Nil
  private var bShoots: List[BassShoot] = Nil

  def draw(arrows: List[Effect])(implicit p: PApplet): Unit = {
    mShoots.foreach(_.draw(arrows))
    tShoots.foreach(_.draw(arrows))
    rShoots.foreach(_.draw(arrows))
    bShoots.foreach(_.draw(arrows))
  }

  def update(arrows: List[Effect]): Unit = {
    implicit val (tArrow, rArrow, mArrow, bArrow) = routing(arrows)
    mShoots = MelodyShootManager.update(mShoots)
    tShoots = TempoShootManager.update(tShoots)
    rShoots = RhythmShootManager.update(rShoots)
    bShoots = BassShootManager.update(bShoots)
  }

  @scala.annotation.tailrec
  private def routing(effects: List[Effect],
                      accTs:List[TempoArrow] = Nil,
                      accRs: List[RhythmArrow] = Nil,
                      accMs: List[MelodyArrow] = Nil,
                      accBs: List[BassArrow] = Nil
                     ):(List[TempoArrow], List[RhythmArrow], List[MelodyArrow], List[BassArrow]) = effects match {

    case (x: TempoArrow)::xs => routing(xs, x::accTs, accRs, accMs, accBs)
    case (x: RhythmArrow)::xs => routing(xs, accTs, x::accRs, accMs, accBs)
    case (x: MelodyArrow)::xs => routing(xs, accTs, accRs, x::accMs, accBs)
    case (x: BassArrow)::xs => routing(xs, accTs, accRs, accMs, x::accBs)
    case Nil => (accTs, accRs, accMs, accBs)

  }

}
