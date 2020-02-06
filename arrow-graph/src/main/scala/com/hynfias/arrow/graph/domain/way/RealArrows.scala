package com.hynfias.arrow.graph.domain.way

import io.circe.generic.semiauto._
import io.circe.{Decoder, Encoder}

case class RealArrows(arrows: List[RealArrow])

object RealArrows {
  implicit val encoder: Encoder[RealArrows] = deriveEncoder
  implicit val decoder: Decoder[RealArrows] = deriveDecoder
}