package com.hynfias.arrow.core.domain.model.music

case class MusicParams(
                   tempo: Int,
                   bass: Int,
                   melodies: List[MelodyParams]
                 )

case class MelodyParams(rhythm: String, melody: String)