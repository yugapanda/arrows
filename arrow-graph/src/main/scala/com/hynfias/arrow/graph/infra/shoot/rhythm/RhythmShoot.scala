package com.hynfias.arrow.graph.infra.shoot.rhythm

import com.hynfias.arrow.graph.infra.effect.Effect
import com.hynfias.arrow.graph.infra.effect.rhythm.RhythmArrow
import com.hynfias.arrow.graph.infra.effect.tempo.TempoArrow
import com.hynfias.arrow.graph.infra.shoot.Shoot
import processing.core.PApplet

object RhythmShoot extends Shoot {

  @scala.annotation.tailrec
  private def choose(effects: List[Effect],
                     rhythmArrows: List[RhythmArrow] = Nil,
                     otherArrows: List[Effect] = Nil): (List[RhythmArrow], List[Effect]) = effects match {
    case (x: RhythmArrow) :: xs => choose(xs, x :: rhythmArrows, otherArrows)
    case (x: Effect) :: xs => choose(xs, rhythmArrows, x :: otherArrows)
    case Nil => (rhythmArrows, otherArrows)

  }


  override def draw(effects: List[Effect])(implicit p: PApplet): Unit = {

    val (rhythmArrows, otherArrows) = choose(effects)

    otherArrows.foreach { o =>
      rhythmArrows.foreach { t =>
        p.fill(255)
        p.stroke(255)
        p.line(t.x, t.y, o.x, o.y)
      }
    }
  }


}
