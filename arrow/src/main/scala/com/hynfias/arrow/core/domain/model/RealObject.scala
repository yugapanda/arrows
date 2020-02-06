package com.hynfias.arrow.core.domain.model

import com.hynfias.arrow.core.presentation.model.RealObjects
import io.circe.{Decoder, Encoder}
import io.circe.generic.semiauto.{deriveDecoder, deriveEncoder}


case class RealObject(
                       override val x: Int,
                       override val y: Int,
                       override val h: Int
                     ) extends Positionable


object RealObject {
  implicit val encoder: Encoder[RealObject] = deriveEncoder
  implicit val decoder: Decoder[RealObject] = deriveDecoder  // <-これを追加
}