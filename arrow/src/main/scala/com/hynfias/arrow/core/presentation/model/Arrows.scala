package com.hynfias.arrow.core.presentation.model

import com.hynfias.arrow.core.domain.model.arrow.Arrow
import io.circe.generic.semiauto.{deriveDecoder, deriveEncoder}
import io.circe.{Decoder, Encoder}

case class Arrows(arrows: List[Arrow])

object Arrows {
  implicit val encoder: Encoder[Arrows] = deriveEncoder
  implicit val decoder: Decoder[Arrows] = deriveDecoder  // <-これを追加
}