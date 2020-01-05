package com.hynfias.arrow.graph.domain.way

trait Movable extends Existable {
  var addX: Float
  var addY: Float
  var left: Int

  def move(): Unit = {
    if(left > 0) {
      x = x + addX
      y = y + addY
      left = left - 1
    }
  }

  def setTarget(left: Int, arrow: RealArrow): Unit = {
    this.left = left
    addX = (arrow.x - x) / left
    addY = (arrow.y - y) / left
  }

}
