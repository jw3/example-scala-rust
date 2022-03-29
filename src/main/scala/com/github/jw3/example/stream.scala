package com.github.jw3.example

import akka.actor.ActorSystem
import akka.stream.{Materializer, OverflowStrategy}
import akka.stream.scaladsl.{Sink, Source}
import com.github.jw3.example.FFI.ffi

object stream extends App {
  val system = ActorSystem()
  implicit val materializer: Materializer = Materializer(system)

  val (input, stream) = Source.queue[Long](10, OverflowStrategy.dropHead).preMaterialize()
  val b = ffi.create_b(v => input.offer(v))

  stream.map(v => s"value: $v").runWith(Sink.foreach(println))
}
