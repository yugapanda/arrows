package com.hynfias.arrow.graph.infra.shoot.tempo.child

import processing.core.PApplet

trait TempoShotChild {

  var x1: Float = 0
  var y1: Float = 0
  var x2: Float = 0
  var y2: Float = 0

  def draw(p: PApplet): Unit = {

  }
  def update(x1: Float, y1: Float, x2: Float, y2: Float): Unit
  def isEnd: Boolean
  def isLive: Boolean = !isEnd

}
