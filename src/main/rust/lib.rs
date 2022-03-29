use std::rc::Rc;
use std::ffi::{c_void, CString, CStr};
use std::os::raw::{c_int, c_char};

#[no_mangle]
pub extern "C" fn hello_from_rust() {
    println!("Hello from Rust!");
}

pub struct A {
    id: i32,
    content: String,
    cb: Option<Callback>
}

#[no_mangle]
pub extern "C" fn create_a() -> *mut c_void {
    Rc::into_raw(Rc::new(A { id: 1001, content: "default".to_string(), cb: None })) as *mut c_void
}

#[no_mangle]
pub extern "C" fn a_get_id(a: *mut c_void) -> c_int {
    let aa = unsafe { &*(a as *mut A) };
    aa.id
}

#[no_mangle]
pub extern "C" fn a_get_content(a: *mut c_void) -> *const c_char {
    let aa = unsafe { &*(a as *mut A) };
    let s = CString::new(aa.content.clone()).unwrap();
    s.into_raw()
}

#[no_mangle]
pub extern "C" fn a_set_content(a: *mut c_void, txt: *const c_char) {
    let content = unsafe { CStr::from_ptr(txt).to_str().unwrap().to_owned() };
    let aa = unsafe { &mut *(a as *mut A) };
    aa.content = content;
    if let Some(cb) = aa.cb {
        cb(aa)
    }
}

type Callback = extern fn(*mut A);

#[no_mangle]
pub extern "C" fn a_add_callback(a: *mut c_void, cb: Callback) {
    let aa = unsafe { &mut *(a as *mut A) };
    aa.cb = Some(cb);
}

#[cfg(test)]
mod tests {
    #[test]
    fn it_works() {
        let result = 2 + 2;
        assert_eq!(result, 4);
    }
}
