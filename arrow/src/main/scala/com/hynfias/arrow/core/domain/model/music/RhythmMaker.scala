package com.hynfias.arrow.core.domain.model.music

import com.hynfias.arrow.core.domain.model.arrow.Arrow

object RhythmMaker {

  def make(arrows: List[Arrow]): List[Rhythm] = extractRhythmArrowAndMelodyArrow(arrows) match {
    case (Some(r), melody) => melody.map(x => calcRhythm(r, x))
    case (None, _) => Nil
  }

  def extractRhythmArrowAndMelodyArrow(arrows: List[Arrow]): (Option[Arrow], List[Arrow]) = (
    arrows.find(x => x.arrowType == "RhythmArrow"),
    arrows.filter(x => x.arrowType == "MelodyArrow")
  )

  def calcRhythm(rhythmArrow: Arrow, melodyArrow: Arrow): Rhythm =
    Rhythm(melodyArrow.id, rhythmArrow.distance(melodyArrow).toInt % 10 + 1)

}

case class Rhythm(id: String, rhythm: Int)