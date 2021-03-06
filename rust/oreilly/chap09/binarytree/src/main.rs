
use std::fmt::Display;

enum BinaryTree<T> {
    Empty,
    NonEmpty(Box<TreeNode<T>>)
}

struct TreeNode<T> {
    element: T,
    left: BinaryTree<T>,
    right: BinaryTree<T>
}

impl<T: Ord + Display> BinaryTree<T> {

    fn add(&mut self, value:T) {
        match *self {
            BinaryTree::Empty =>
                *self = BinaryTree::NonEmpty(Box::new(TreeNode { element: value, left: BinaryTree::Empty, right: BinaryTree::Empty})),
            BinaryTree::NonEmpty(ref mut node) =>
                if value <= node.element {
                    node.left.add(value)
                } else {
                    node.right.add(value)
                }
        }
    }

    fn print_in_order(& self) {

        if let BinaryTree::NonEmpty(ref node) = *self {
          node.left.print_in_order();
          println!("{}", node.element);
          node.right.print_in_order();  
        }
    }

    fn new() -> BinaryTree<T> {
        BinaryTree::Empty
    }
}


fn main() {
    println!("Hello, world!");
    let mut tree = BinaryTree::<i32>::new();

    tree.add(5);
    tree.add(3);
    tree.add(1);
    tree.print_in_order();
}
