package com.hynfias.arrow.core.domain.model
import scala.math.{pow, sqrt}

trait Positionable {
  val x: Int
  val y: Int
  val h: Int

  def isNear(positionable: Positionable, threshold: Double): Boolean =
    sqrt(pow(x - positionable.x, 2) + pow(y - positionable.y, 2)) < threshold
}
