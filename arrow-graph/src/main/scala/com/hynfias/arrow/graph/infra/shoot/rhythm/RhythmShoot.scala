package com.hynfias.arrow.graph.infra.shoot.rhythm

import com.hynfias.arrow.graph.infra.effect.Effect
import com.hynfias.arrow.graph.infra.effect.kind.{Bang, EffectKind, RhythmEffect}
import com.hynfias.arrow.graph.infra.effect.rhythm.RhythmArrow
import com.hynfias.arrow.graph.infra.effect.tempo.TempoArrow
import com.hynfias.arrow.graph.infra.shoot.Shoot
import com.hynfias.arrow.graph.infra.shoot.rhythm.child.RhythmChildLine
import processing.core.PApplet


case class RhythmShoot(targetId: String, sourceId: String) extends Shoot {


  var children: List[RhythmChildLine] = List[RhythmChildLine]()

  override def draw(effects: List[Effect])(implicit p: PApplet): Unit = {
    children.foreach(x => {
      x.draw()
      x.update()
    })

    children = children.filter(x => x.isLive)
  }

  def add(id: String, effects: List[Effect], p: PApplet): Unit = findTargetAndSource(effects).foreach(tas => {
    val (target, source) = tas
    if(id == target.id) {
      children = RhythmChildLine(target.x.toInt, target.y.toInt, source.x.toInt, source.y.toInt)(p) :: children
    }
  })

  override def bang(effectKind: EffectKind, effects: List[Effect], p: PApplet): Unit = effectKind match {
    case Bang(id) => add(id, effects, p)
    case RhythmEffect => ()
    case _ => ()
  }
}
