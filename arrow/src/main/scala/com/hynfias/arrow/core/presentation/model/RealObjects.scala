package com.hynfias.arrow.core.presentation.model

import com.hynfias.arrow.core.domain.model.RealObject
import io.circe._
import io.circe.generic.semiauto._

case class RealObjects(realObjects: List[RealObject])

object RealObjects {
  implicit val encoder: Encoder[RealObjects] = deriveEncoder
  implicit val decoder: Decoder[RealObjects] = deriveDecoder  // <-これを追加
}