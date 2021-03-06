package com.hynfias.arrow.core.domain.model.music

import com.hynfias.arrow.core.domain.model.arrow.Arrow

object MusicParamsMaker {

  type Tempo = Int
  type Bass = Int
  type Rhythm = Int
  type Melody = Int

  def make(arrows: List[Arrow]): MusicParams = {
    val tempo = TempoMaker.make(arrows)
    val bass = BassMaker.make(arrows)
    val rhythms = RhythmMaker.make(arrows)
    val melodies = MelodyMaker.make(rhythms, arrows)

    MusicParams(tempo, bass, melodies)

  }




}
