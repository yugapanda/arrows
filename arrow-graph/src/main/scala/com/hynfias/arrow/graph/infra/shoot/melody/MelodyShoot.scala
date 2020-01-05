package com.hynfias.arrow.graph.infra.shoot.melody

import com.hynfias.arrow.graph.infra.effect.Effect
import com.hynfias.arrow.graph.infra.effect.melody.MelodyArrow
import com.hynfias.arrow.graph.infra.effect.rhythm.RhythmArrow
import com.hynfias.arrow.graph.infra.shoot.Shoot
import processing.core.PApplet

object MelodyShoot extends Shoot {

  @scala.annotation.tailrec
  private def choose(effects: List[Effect],
                     melodyArrows: List[MelodyArrow] = Nil,
                     otherArrows: List[Effect] = Nil): (List[MelodyArrow], List[Effect]) = effects match {
    case (x: MelodyArrow) :: xs => choose(xs, x :: melodyArrows, otherArrows)
    case (x: Effect) :: xs => choose(xs, melodyArrows, x :: otherArrows)
    case Nil => (melodyArrows, otherArrows)

  }


  override def draw(effects: List[Effect])(implicit p: PApplet): Unit = {

    val (melodyArrows, otherArrows) = choose(effects)

    otherArrows.foreach { o =>
      melodyArrows.foreach { t =>
        p.fill(255)
        p.stroke(255)
        p.line(t.x, t.y, o.x, o.y)
      }
    }
  }


}
