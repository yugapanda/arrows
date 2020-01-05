package com.hynfias.arrow.graph
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

import com.hynfias.arrow.graph.infra.GetArrow
import processing.core.PApplet

object Main extends App {

  val ex = Executors.newSingleThreadExecutor()
  ex.execute(new GetArrow)

  PApplet.main("com.hynfias.arrow.graph.application.Applet")

}
