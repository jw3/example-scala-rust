package com.github.jw3.example

import jnr.ffi.{LibraryLoader, Pointer}

trait FFI {
  def hello_from_rust(): Unit
  def create_a(): Pointer
  def get_a_id(p: Pointer): Int
}

object FFI {
  lazy val ffi: FFI = {
    val loader = LibraryLoader.create(classOf[FFI]).failImmediately()
    loader.search("target/debug")
    loader.load("scalarust")
  }
}
