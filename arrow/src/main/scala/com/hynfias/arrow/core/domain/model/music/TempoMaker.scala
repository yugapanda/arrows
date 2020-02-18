package com.hynfias.arrow.core.domain.model.music

import com.hynfias.arrow.core.domain.model.arrow.Arrow
import MusicParamsMaker.Tempo
import com.hynfias.arrow.core.util.MathUtil

object TempoMaker {

  def make(arrows: List[Arrow]): Tempo = extractTempoArrow(arrows) match {
      case (Some(t), other) => calcTempo(t, other)
      case (None, _) => 40
    }


  def extractTempoArrow(arrows: List[Arrow]): (Option[Arrow], List[Arrow]) = (
    arrows.find(x => x.arrowType == "TempoArrow"),
    arrows.filter(x => x.arrowType != "TempoArrow")
  )

  def calcTempo(tempo: Arrow, others: List[Arrow]): Tempo = others match {
    case Nil => 40
    case x =>
      val sum = x
        .foldLeft(0d)((acc, cur) => acc + tempo.distance(cur))
        .toInt
      val ave = sum / others.length
      MathUtil.mapping(ave, 0, 1270, 0, 200).toInt
  }

}
