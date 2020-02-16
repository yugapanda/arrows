package com.hynfias.arrow.core.domain.model.music

import com.hynfias.arrow.core.domain.model.arrow.Arrow

object MelodyMaker {

  def make(rhythms: List[Rhythm], arrows: List[Arrow]): List[MelodyRhythm] = {

  }

}


case class MelodyRhythm(id: String, rhythm: Int, melody: Int)