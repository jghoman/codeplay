
fn demonstrate_moves() {
    // We have to use to_string to get a value on the heap.
    // If we didn't, and just used a string literal, we would be able to
    // double reference it... which is confusing.
    let mut a = "hello".to_string();
    let b = a;

    // Can't do this.  a doesn't own the bytes for "hello" anymore
    // println!("Can't reference a anymore: {}", a);

    // Can do this
    println!("Can reference it via b: {}", b);

    a = b;
    println!("Now it's back to a: {}", a);

}

fn demonstrate_references_and_borrowing() {
    let mut a:Vec<i32> = vec![42];

    // Can't borrow and mutate, even though a is mutable
    // fn mutate_the_arg(v:  Vec<i32>) {
    //     println!("Look what I got! {}", v.pop().unwrap())
    // }
    
    fn mutate_the_arg(v:  &mut Vec<i32>) {
        println!("Look what I got! {}", v.pop().unwrap())
    }

    // Even though a is mutable, can't just pass it:
    //mutate_the_arg(a);

    // Instead, have to make a reference and be mutable
    mutate_the_arg(&mut a);

}

fn demonstrate_mut_variables() {
    let mut a = 22;
    println!("mut a = {}", a);

    a = 23;
    println!("now mut a = {}", a);

    let b = 24;
    println!("b = {}", b);

    // b = 25;
    println!("now b = {}", b);
}

fn main() {
    println!("** demonstrate_mut_variables **");
    demonstrate_mut_variables();

    println!("** demonstrate_moves() **");
    demonstrate_moves();

    println!("** demonstrate_references_and_borrowing **");
    demonstrate_references_and_borrowing();
}
