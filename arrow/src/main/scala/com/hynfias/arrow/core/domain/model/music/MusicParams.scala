package com.hynfias.arrow.core.domain.model.music

case class MusicParams(
                   tempo: Int,
                   bass: Int,
                   melodies: List[MelodyParams]
                 ) {
  override def toString: String = {
    val melodyString = melodies.map(_.toString).mkString(" ")
    s"$tempo $bass $melodyString"
  }

  def toStringArray: List[String] = {
    List(tempo.toString, bass.toString) ++ melodies.flatMap(_.toStringArray)
  }
}

case class MelodyParams(id: String, rhythm: Int, melody: Int) {
  override def toString: String = s"$id $rhythm $melody"
  def toStringArray: List[String] = List(id.toString, rhythm.toString, melody.toString)

}