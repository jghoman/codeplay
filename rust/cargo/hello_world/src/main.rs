extern crate color;

use color::{Rgb, ToHsv};

fn main() {
  println!("Converting RGB to HSV!");
  let red = Rgb::new(255u8, 0, 0);
  println!("HSV: {}", red.to_hsv::<f32>());
}
