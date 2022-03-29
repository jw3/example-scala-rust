package com.github.jw3.example

import com.github.jw3.example.model.A
import jnr.ffi.annotations.Delegate
import jnr.ffi.{LibraryLoader, Pointer}

trait FFI {
  def hello_from_rust(): Unit
  def create_a(): Pointer
  def a_get_id(p: Pointer): Int
  def a_get_content(p: Pointer): String
  def a_set_content(p: Pointer, s: String): Unit
  def a_add_callback(p: Pointer, cb: Acallback): Unit

  def create_b(cb: Bcallback): Pointer
}

object FFI {
  lazy val ffi: FFI = {
    val loader = LibraryLoader.create(classOf[FFI]).failImmediately()
    loader.search("target/debug")
    loader.load("scalarust")
  }
}

trait Acallback {
  @Delegate def invoke(p: Pointer): Unit =
    handle(A.make(p))

  def handle(v: A): Unit
}

trait Bcallback {
  @Delegate def invoke(v: Long): Unit =
    handle(v)

  def handle(v: Long): Unit
}
