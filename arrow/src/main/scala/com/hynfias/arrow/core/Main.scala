package com.hynfias.arrow.core

import com.hynfias.arrow.core.presentation.Controller


object Main extends App {

  Controller.exec()
  scala.io.StdIn.readLine()
  Controller.end()

}
