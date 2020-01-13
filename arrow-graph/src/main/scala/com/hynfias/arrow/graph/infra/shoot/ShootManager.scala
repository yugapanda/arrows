package com.hynfias.arrow.graph.infra.shoot

import com.hynfias.arrow.graph.infra.effect.Effect
import com.hynfias.arrow.graph.infra.effect.bass.BassArrow
import com.hynfias.arrow.graph.infra.effect.melody.MelodyArrow
import com.hynfias.arrow.graph.infra.effect.rhythm.RhythmArrow
import com.hynfias.arrow.graph.infra.effect.tempo.TempoArrow
import com.hynfias.arrow.graph.infra.shoot.bass.BassShoot
import com.hynfias.arrow.graph.infra.shoot.melody.MelodyShoot
import com.hynfias.arrow.graph.infra.shoot.rhythm.RhythmShoot
import com.hynfias.arrow.graph.infra.shoot.tempo.TempoShoot

object ShootManager {

  var tempoShoots: List[TempoShoot] = Nil
  var rhythmShoots: List[RhythmShoot] = Nil
  var melodyShoots: List[MelodyShoot] = Nil
  var bassShoots: List[BassShoot] = Nil

  def update(effects: List[Effect]): Unit = {

    // それぞれの型のEffectに対してtargetとsourceがあるかを確認しなければnewする。
    // また、shootに対応するEffectがなかった場合、そのshootをdeleteする。

  }


}
