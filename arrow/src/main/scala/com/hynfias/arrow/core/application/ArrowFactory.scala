package com.hynfias.arrow.core.application

import com.hynfias.arrow.core.domain.model.RealObject
import com.hynfias.arrow.core.domain.model.arrow.{Arrow, Quiver}

object ArrowFactory {

  var quiver: Quiver = new Quiver

  def update(realObjects: List[RealObject]): Unit = {
    quiver = quiver.update(realObjects)
    println(quiver.arrows.toList)
  }

  def getArrows: List[Arrow] = {
    if(quiver.arrows.nonEmpty) {
      quiver.arrows.dequeue._1
    } else {
      Nil
    }
  }

}
