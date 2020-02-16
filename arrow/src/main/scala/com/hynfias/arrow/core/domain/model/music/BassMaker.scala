package com.hynfias.arrow.core.domain.model.music

import com.hynfias.arrow.core.domain.model.arrow.Arrow
import com.hynfias.arrow.core.domain.model.music.MusicParamsMaker.{Bass, Tempo}
import com.hynfias.arrow.core.util.MathUtil

object BassMaker {

  def make(arrows: List[Arrow]): Bass = extractBassArrow(arrows) match {
    case (Some(x), other) => calcBass(x, other)
    case (None, _) => 0
  }

  def extractBassArrow(arrows: List[Arrow]): (Option[Arrow], List[Arrow]) = (
    arrows.find(x => x.arrowType == "BassArrow"),
    arrows.filter(x => x.arrowType != "BassArrow")
  )

  def calcBass(tempo: Arrow, others: List[Arrow]): Tempo = others match {
    case Nil => 0
    case x =>
      val sum = x
        .foldLeft(0d)((acc, cur) => acc + tempo.distance(cur))
        .toInt
      val ave = sum / others.length
      MathUtil.mapping(ave, 0, 1920, 0, 96).toInt % 12
  }

}
