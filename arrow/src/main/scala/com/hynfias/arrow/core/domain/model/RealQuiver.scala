package com.hynfias.arrow.core.domain.model

object RealQuiver {
  def normalize(realObjects: List[RealObject]): List[RealObject] =
    realObjects.foldLeft(List[RealObject]())((acc, curr) => {
    if (acc.exists(_.isNear(curr, 100))) {
      acc
    } else {
      curr :: acc
    }
  })
}
