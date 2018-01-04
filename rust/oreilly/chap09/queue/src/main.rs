
pub struct Queue<T> {
    older: Vec<T>,
    younger: Vec<T>

}

impl<T> Queue<T> {
    pub fn new() -> Self {
        Queue { older: Vec::new(), younger: Vec::new()}
    }

    pub fn push(&mut self, t: T) {
        self.younger.push(t);
    }

    pub fn pop(&mut self) -> Option<T> {
        if !self.older.is_empty() {
            return Some(self.older.remove(0));
        }

        if !self.younger.is_empty() {
            return Some(self.younger.remove(0));
        }

        None
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

#[test]
fn test_taking_from_queue() {
    let mut q = Queue::<i32>::new();

    q.push(1);
    q.push(2);
    q.push(3);

    assert_eq!(q.is_empty(), false);

    assert_eq!(q.pop(), Some(1));
    assert_eq!(q.pop(), Some(2));
    assert_eq!(q.pop(), Some(3));
    assert_eq!(q.pop(), None);

    assert_eq!(q.is_empty(), true);
}

fn main() {
    println!("Hello, world!");
}
