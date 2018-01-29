// If we list all the natural numbers below 10 that are multiples of 3 or 5, we get 3, 5, 6 and 9. The sum of these multiples is 23.
// Find the sum of all the multiples of 3 or 5 below 1000.

fn p01_sum_5_or_3_multiples(upper_bound: u64) -> u64 {
    let mut sum = 0;
    for i in 0 .. upper_bound {
        if (i % 3 == 0) || (i % 5 == 0) {
            sum += i;
        }
    }
    //println!("sum = {}", sum);
    sum
}

#[test]
fn p01_test_sum_5_or_3_multiples() {
    assert_eq!(sum_5_or_3_multiples(10), 23);
}

#[test]
fn p01_test_expected_answer() {
    assert_eq!(sum_5_or_3_multiples(1000), 233168);
}



fn main() {
    let result = p01_sum_5_or_3_multiples(1000);
    println!("Sum of all the multiple numbers of 3 or 5 below 1000 is {}", result);
}
