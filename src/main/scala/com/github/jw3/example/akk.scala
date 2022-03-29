package com.github.jw3.example

import akka.actor.{Actor, ActorSystem, Props}
import com.github.jw3.example.FFI.ffi
import com.github.jw3.example.model.A

object akk extends App {
  val system = ActorSystem()
  val a = A.make(ffi.create_a())

  val echo = system.actorOf(Props[Echo])
  a.callback((v: A) => echo ! Msg(v.content))

  for (i <- 1 to 10)
    a.content(s"update $i")

  system.terminate()
}

case class Msg(txt: String)
class Echo extends Actor {
  def receive: Receive = {
    case Msg(txt) => println(s"callback: ${txt}")
  }
}
