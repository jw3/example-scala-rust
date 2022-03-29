package com.github.jw3.example

import jnr.ffi.LibraryLoader

trait FFI {
  def hello_from_rust(): Unit
}

object FFI {
  lazy val ffi: FFI = {
    val loader = LibraryLoader.create(classOf[FFI]).failImmediately()
    loader.search("target/debug")
    loader.load("scalarust")
  }
}
