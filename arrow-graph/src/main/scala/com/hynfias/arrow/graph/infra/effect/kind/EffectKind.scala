package com.hynfias.arrow.graph.infra.effect.kind

sealed trait EffectKind
object RhythmEffect extends EffectKind
case class Bang(id: String) extends EffectKind
