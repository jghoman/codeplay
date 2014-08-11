struct Point {
  x: f64,
  y: f64
}

fn matchit(p: Point) -> () {
  match p {
    Point { x: 0.0, y: yy } => println!("{}", yy),
    Point { x: xx, y: yy } => println!("{} {}", xx, yy)
  }
}

fn main() {

  let p1 = Point { x: 1.0, y: 1.0};
  matchit(p1);
  matchit(Point { x: 0.0, y: 42.2 })
}
