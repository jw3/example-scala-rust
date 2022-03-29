package com.github.jw3.example

import jnr.ffi.{LibraryLoader, Pointer}

trait FFI {
  def hello_from_rust(): Unit
  def create_a(): Pointer
  def a_get_id(p: Pointer): Int
  def a_get_content(p: Pointer): String
  def a_set_content(p: Pointer, s: String): Unit
  def a_add_callback(p: Pointer, cb: Acallback): Unit
}

object FFI {
  lazy val ffi: FFI = {
    val loader = LibraryLoader.create(classOf[FFI]).failImmediately()
    loader.search("target/debug")
    loader.load("scalarust")
  }
}
