package com.hynfias.arrow.graph.utils

import de.xypron.statistics.MersenneTwister
object Random {
  val MT = new MersenneTwister()
  def randomDouble(): Double = MT.nextDouble()
  def randomInt(): Int = MT.random()
}
