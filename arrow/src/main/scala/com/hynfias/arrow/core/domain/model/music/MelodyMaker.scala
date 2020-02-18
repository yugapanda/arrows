package com.hynfias.arrow.core.domain.model.music

import com.hynfias.arrow.core.domain.model.arrow.Arrow
import com.hynfias.arrow.core.domain.model.music.MusicParamsMaker.Melody
import com.hynfias.arrow.core.util.MathUtil
import scala.util.chaining._

object MelodyMaker {

  def make(rhythms: List[Rhythm], arrows: List[Arrow]): List[MelodyParams] =
    zip(rhythms, arrows)
      .pipe(toMelodyRhythms)

  /**
   * 同じidを持つリズムとアローをzipし、RhythmArrows型で返す
   *
   * @param rhythms
   * @param arrows
   * @return
   */
  def zip(rhythms: List[Rhythm], arrows: List[Arrow]): List[RhythmArrowPair] = rhythms
    .foldLeft(List[RhythmArrowPair]())((acc, cur) => {
      arrows.find(x => cur.id == x.id) match {
        case Some(arrow) => RhythmArrowPair(cur, arrow) :: acc
        case None => acc
      }
    })

  /**
   * RhythmArrowのリストから、MelodyRhythmを生成する
   *
   * @param rhythmArrows
   * @return
   */
  def toMelodyRhythms(rhythmArrows: List[RhythmArrowPair]): List[MelodyParams] = rhythmArrows
    .map(ra => MelodyParams(ra.arrow.id, ra.rhythm.rhythm, calcMelody(ra.arrow, rhythmArrows)))


  // 対象のArrowとRhythmArrowPairからMelodyを生成する
  def calcMelody(arrow: Arrow, rhythmArrow: List[RhythmArrowPair]): Melody = {
    val others = rhythmArrow.filter(x => x.arrow.id != arrow.id)
    if (others.isEmpty) {
      MathUtil.mapping(arrow.x + arrow.y, 0, 1990, 60, 100).toInt
    } else {
      others
        .map(x => x.arrow.distance(arrow))
        .pipe(MathUtil.average)
        .pipe(x => MathUtil.mapping(x, 0, 1468, 60, 100)).toInt
    }
  }

}


case class RhythmArrowPair(rhythm: Rhythm, arrow: Arrow)