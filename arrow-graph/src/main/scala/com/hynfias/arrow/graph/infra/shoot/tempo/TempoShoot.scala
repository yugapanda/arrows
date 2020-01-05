package com.hynfias.arrow.graph.infra.shoot.tempo

import com.hynfias.arrow.graph.infra.effect.Effect
import com.hynfias.arrow.graph.infra.effect.tempo.TempoArrow
import com.hynfias.arrow.graph.infra.shoot.Shoot
import processing.core.PApplet

object TempoShoot extends Shoot {

  @scala.annotation.tailrec
  private def choose(effects: List[Effect],
             tempoArrows: List[TempoArrow] = Nil,
             otherArrows: List[Effect] = Nil): (List[TempoArrow], List[Effect]) = effects match {
    case (x: TempoArrow) :: xs => choose(xs, x :: tempoArrows, otherArrows)
    case (x: Effect) :: xs => choose(xs, tempoArrows, x :: otherArrows)
    case Nil => (tempoArrows, otherArrows)

  }


  override def draw(effects: List[Effect])(implicit p: PApplet): Unit = {

    val (tempoArrows, otherArrows) = choose(effects)

    otherArrows.foreach { o =>
      tempoArrows.foreach { t =>
        p.fill(255)
        p.stroke(255)
        p.line(t.x, t.y, o.x, o.y)
      }
    }
  }


}
