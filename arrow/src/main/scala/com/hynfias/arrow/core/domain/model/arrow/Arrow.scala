package com.hynfias.arrow.core.domain.model.arrow

import com.hynfias.arrow.core.domain.model.{Positionable, RealObject}

case class Arrow(
                  id: String,
                  override val x: Int,
                  override val y: Int,
                  override val h: Int,
                  var arrowType: String,
                ) extends Positionable {

  def setArrowType(length: Int): Arrow = {
    arrowType = Arrow.choose(length)
    this
  }

}

object Arrow {
  def from(id: String, positionable: Positionable)(length: Int): Arrow =
    Arrow(id, positionable.x, positionable.y, positionable.h, choose(length))


  def choose(length: Int): String = length match {
    case 0 => "TempoArrow"
    case 1 => "RhythmArrow"
    case 2 => "BassArrow"
    case _ => "MelodyArrow"
  }


  def from(id: String, x: Int, y: Int, h: Int)(arrowType: String): Arrow = new Arrow(id, x, y, h, arrowType)
}