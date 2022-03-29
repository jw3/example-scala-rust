use std::rc::Rc;
use std::ffi::c_void;
use std::os::raw::c_int;

#[no_mangle]
pub extern "C" fn hello_from_rust() {
    println!("Hello from Rust!");
}

pub struct A {
    id: i32
}

#[no_mangle]
pub extern "C" fn create_a() -> *mut c_void {
    Rc::into_raw(Rc::new(A { id: 1001 })) as *mut c_void
}

#[no_mangle]
pub extern "C" fn get_a_id(a: *mut ::std::os::raw::c_void) -> c_int {
    let aa = unsafe { &*(a as *mut A) };
    aa.id
}

#[cfg(test)]
mod tests {
    #[test]
    fn it_works() {
        let result = 2 + 2;
        assert_eq!(result, 4);
    }
}
