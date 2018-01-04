
pub struct Queue<T> {
    older: Vec<T>,
    younger: Vec<T>

}

impl<T> Queue<T> {
    pub fn new() -> Queue<T> {
        Queue { older: Vec::new(), younger: Vec::new()}
    }

    pub fn push(&mut self, t: T) {
        self.younger.push(t);
    }

    pub fn is_empty(&self) -> bool {
        self.older.is_empty() && self.younger.is_empty()
    }
}

#[test]
fn test_queue() {
    let mut q = Queue::<i32>::new();

    assert_eq!(q.is_empty(), true);

    q.push(22);

    assert_eq!(q.is_empty(), false);
}

fn main() {
    println!("Hello, world!");
}
