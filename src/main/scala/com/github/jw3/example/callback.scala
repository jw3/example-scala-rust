package com.github.jw3.example

import com.github.jw3.example.FFI.ffi
import com.github.jw3.example.model.A

// Output:
// 1) content: default
// 2) content: updated from scala
// 3) content: updated again from scala
object callback extends App {
  val a = A.make(ffi.create_a())

  // default content
  println(s"1) content: ${a.content}")

  // update and print the content
  a.content("updated from scala")
  println(s"2) content: ${a.content}")

  // beware of garbage collection
  a.callback((v: A) => println(s"3) content: ${v.content}"))

  // the callback prints this one
  a.content("updated again from scala")
}
