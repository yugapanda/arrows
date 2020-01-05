package com.hynfias.arrow.graph.domain.way

import io.circe.generic.semiauto._
import io.circe.{Decoder, Encoder}

case class RealArrow(
  id: String,
  x: Int,
  y: Int,
  h: Int,
  arrowType: String
)

object RealArrow {
  implicit val encoder: Encoder[RealArrow] = deriveEncoder
  implicit val decoder: Decoder[RealArrow] = deriveDecoder
}