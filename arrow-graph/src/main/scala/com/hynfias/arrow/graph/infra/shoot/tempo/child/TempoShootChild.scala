package com.hynfias.arrow.graph.infra.shoot.tempo.child

import processing.core.PApplet

trait TempoShootChild {

  var x1: Float
  var y1: Float
  var x2: Float
  var y2: Float
  var nowX: Float
  var nowY: Float
  var color: Int
  var lifetime = 100
  var count: Int = 0

  def draw(p: PApplet): Unit
  def update(x1: Float, y1: Float, x2: Float, y2: Float): Unit
  def isEnd: Boolean = count >= lifetime
  def isLive: Boolean = !isEnd

}
