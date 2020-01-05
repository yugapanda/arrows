package com.hynfias.arrow.graph.domain.way

import scala.math._

trait Rotatable extends Existable {
  var centerX: Float
  var centerY: Float
  var centerDistance: Float
  var rotateSpeed: Float
  var rad: Float

  def setCenter(e: Existable): Unit = {
    centerX = e.x
    centerY = e.y
  }

  def rotate(): Unit = {
    rad = rad + rotateSpeed
  }

  def getPos: (Double, Double) =
    (centerX + (math.cos(toRadians(rad)) * centerDistance),
    centerY + (math.sin(toRadians(rad)) * centerDistance))



}
