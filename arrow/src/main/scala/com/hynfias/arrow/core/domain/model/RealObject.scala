package com.hynfias.arrow.core.domain.model


case class RealObject(
                       override val x: Int,
                       override val y: Int,
                       override val h: Int
                     ) extends Positionable