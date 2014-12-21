
struct Circle {
  x: f64,
  y: f64,
  radius: f64,
}

impl Circle {
  fn new(x: f64, y: f64, radius: f64) -> Circle {
    Circle {
      x: x,
      y: y,
      radius: radius,
    }
  }

//  fn area(&self) -> f64 {
//    std::f64::consts::PI * (self.radius * self.radius)
//  }
}

trait HasArea {
  fn area(&self) -> f64;
}

impl HasArea for Circle {
  fn area(&self) -> f64 {
    std::f64::consts::PI * (self.radius * self.radius)
  }
}

fn main() {
  let c = Circle { x: 0.0, y: 0.0, radius: 2.0 };
  println!("{}", c.area());

  let c2 = Circle::new(4.3, 2.7, 9.6);
  println!("c2 = {}", c2.area());
}
