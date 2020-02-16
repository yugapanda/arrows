package com.hynfias.arrow.core.util

object MathUtil {

  def mapping(target: Double, s1: Double, e1: Double, s2: Double, e2: Double): Double =
    target / (s1 + e1) * (s2 + e2)

}
