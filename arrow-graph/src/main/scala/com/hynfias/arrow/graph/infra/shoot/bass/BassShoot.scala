package com.hynfias.arrow.graph.infra.shoot.bass

import com.hynfias.arrow.graph.infra.effect.Effect
import com.hynfias.arrow.graph.infra.effect.bass.BassArrow
import com.hynfias.arrow.graph.infra.effect.melody.MelodyArrow
import com.hynfias.arrow.graph.infra.shoot.Shoot
import processing.core.PApplet

object BassShoot extends Shoot {

  @scala.annotation.tailrec
  private def choose(effects: List[Effect],
                     bassArrows: List[BassArrow] = Nil,
                     otherArrows: List[Effect] = Nil): (List[BassArrow], List[Effect]) = effects match {
    case (x: BassArrow) :: xs => choose(xs, x :: bassArrows, otherArrows)
    case (x: Effect) :: xs => choose(xs, bassArrows, x :: otherArrows)
    case Nil => (bassArrows, otherArrows)

  }


  override def draw(effects: List[Effect])(implicit p: PApplet): Unit = {

    val (bassArrows, otherArrows) = choose(effects)

    otherArrows.foreach { o =>
      bassArrows.foreach { t =>
        p.fill(255)
        p.stroke(255)
        p.line(t.x, t.y, o.x, o.y)
      }
    }
  }
}
