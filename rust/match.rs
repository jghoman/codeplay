fn main() {
  let age = 17i;
  match age {
    a @ 0..20 => println!("{} years old", a),
    _ => println!("older than 21")
  }
}
