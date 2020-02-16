package com.hynfias.arrow.core.util

import scala.collection.IterableOnce

object MathUtil {

  def mapping(target: Double, s1: Double, e1: Double, s2: Double, e2: Double): Double =
     s2 + (e2 - s2) * ((target - s1) / (e1 - s1))


  def average(list: List[Double]): Double = if(list.isEmpty) {
      0d
    } else {
      list.sum / list.length
    }


}
