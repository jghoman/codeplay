fn main() {
  // Arrays are fixed size
  let a = [1i, 2i, 3i];
  let mut m = [1i, 2i, 3i];

  println!("a has {} elements", a.len())
  for e in a.iter() {
    println!("{}", e);
  }

  // Vectors grow
  let v = vec![1i, 2, 3];
  let mut nums = vec![2i, 3, 4];
  nums.push(99);
  println!("The length of nums is {}.", nums.len());

  let v2 = vec![1i, 2, 3, 4, 5, 6, 7, 8];
  let middle = v2.slice(3,5);

  for e in middle.iter() {
    println!("Sliced from middle: {}", e);
  }
}
