package com.hynfias.arrow.core.domain.model.arrow

import com.hynfias.arrow.core.domain.model.{RealObject, RealQuiver}

import scala.util.chaining._
import java.util.UUID.randomUUID

import scala.collection.immutable.Queue

class Quiver {
  var arrows: Queue[List[Arrow]] = Queue[List[Arrow]]()

  def update(realObjects: List[RealObject]): Quiver = if (arrows.nonEmpty) {
    val filteredRealObject = RealQuiver.normalize(realObjects)
    arrows = arrows.dequeue._2.enqueue(convertRealObjectsToArrows(filteredRealObject).distinctBy(_.id))
    this
  } else {
    val filteredRealObject = RealQuiver.normalize(realObjects)
    arrows = arrows :+ filteredRealObject.map(x => Arrow.from(randomUUID().toString, x)(arrows.length))
    this
  }

  private def fixArrowType(arrows: List[Arrow]): List[Arrow] = arrows
    .foldLeft(List[Arrow]())((acc, now) => now.setArrowType(acc.length) :: acc).reverse


  private def convertRealObjectsToArrows(realObjects: List[RealObject]): List[Arrow] = {
    val nearArrows = arrows.lastOption.map(x => {
      x.filter(y => realObjects.exists(_.isNear(y, 100)))
    }) match {
      case Some(x) => x
      case None => Nil
    }

    val filteredRealObjects = realObjects.filter(x => !nearArrows.exists(x.isNear(_, 100)))

    val updatedArrows = convertRealObjectToNearArrows(realObjects, nearArrows)
    val newArrows = filteredRealObjects.map(x => Arrow.from(randomUUID().toString, x)(_))
      .zipWithIndex
      .map(f => f._1(f._2 + updatedArrows.length)) // updateArrowsの数と、newArrowsの数を足したものでtypeを判断

    fixArrowType(updatedArrows ++ newArrows)

  }

  private def convertRealObjectToNearArrows(realObjects: List[RealObject], nearArrows: List[Arrow]): List[Arrow] =
    realObjects
      .iterator
      .map(ro => (ro, nearArrows.filter(na => na.isNear(ro, 100))))
      .map(roAs => {
        val (ro, as) = roAs
        as.headOption.map(x => Arrow(x.id, ro.x, ro.y, ro.h, x.arrowType))
      }).collect {
      case Some(x) => x
    }.toList


}
