package com.hynfias.arrow.graph.infra.effect

import com.hynfias.arrow.graph.infra.effect.kind.Bang

import scala.collection.mutable

class EffectNotificator {
  var sendTarget: List[Effect] = Nil
  def +=(effect: Effect): Unit = sendTarget = effect::sendTarget
  def bang(content: String): Unit = sendTarget.foreach(_.bang(Bang(content)))
}
