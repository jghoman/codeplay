extern crate serde_json;

use serde_json::{Value, Error};

fn main() {
    let example_bytes = include_bytes!("example.json");
    let as_string = String::from_utf8_lossy(example_bytes);
    println!("Hello!");

    println!("{}", as_string);
    
    let v: Value = serde_json::from_str(&as_string).unwrap();

    println!("The name is {} {}", v["first"], v["last"]);
    
    println!("Goodbye!");
}
