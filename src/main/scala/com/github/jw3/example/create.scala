package com.github.jw3.example

import FFI.ffi

// create rust struct and get a handle
object create extends App {
  val a = ffi.create_a()
  val id = ffi.a_get_id(a)
  println(s"id of a is ${id}")
}
