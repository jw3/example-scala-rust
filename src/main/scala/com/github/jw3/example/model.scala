package com.github.jw3.example

import com.github.jw3.example.FFI.ffi
import jnr.ffi.Pointer

object model {
  trait A {
    def id: Int
    def content: String
    def content(txt: String): Unit
    def callback(cb: Acallback): Unit
  }
  object A {
    def make(p: Pointer): A = new A {
      def id: Int = ffi.a_get_id(p)
      def content: String = ffi.a_get_content(p)
      def content(txt: String): Unit = ffi.a_set_content(p, txt)
      def callback(cb: Acallback): Unit = ffi.a_add_callback(p, cb)
    }
  }
}
