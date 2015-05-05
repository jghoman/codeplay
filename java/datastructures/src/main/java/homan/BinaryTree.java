package homan.datastructures;

import java.util.*;

public class BinaryTree<T extends Comparable> {
  public static class Node<T extends Comparable> {
    T element;
    Node<T> left;
    Node<T> right;

    public Node(T element, Node<T> left, Node<T> right) {
      this.element = element;
      this.left = left;
      this.right = right;
    }

    public Node(T element) {
      this(element, null, null);
    }

    public T getElement() { return this.element; }

    public Node<T> getLeft() { return this.left; }

    public Node<T> getRight() { return this.right; }

    public void setLeft(Node<T> left) { this.left = left; }

    public void setRight(Node<T> right) { this.right = right; }

    @Override
    public String toString() { return element.toString(); }

    public String toVerboseString() {
      String e = toString();
      String l = getLeft() == null ? "<null>" : getLeft().toString();
      String r = getRight() == null ? "<null>" : getRight().toString();

      return new StringBuilder("[element = ")
        .append(e)
        .append(", left = ")
        .append(l)
        .append(", right = ")
        .append(r)
        .append("]")
        .toString();
    }
  }

  private Node<T> root = null;

  public BinaryTree() { /** Nothing to do **/ }

  private void notNull(Object o) throws IllegalArgumentException {
    if(o == null) {
      throw new IllegalArgumentException("Value cannot be null");
    }
  }
  
  public void insert(T t) {
    notNull(t);
    if(root == null) {
      root = new Node<T>(t);
    } else {
      insert(t, root);
    }
  }

  protected void insert(T t, Node<T> node) {
    int equality = t.compareTo(node.getElement());
    if(equality < 0) {
      if(node.getLeft() == null) {
        node.setLeft(new Node(t));
      } else {
        insert(t, node.getLeft());
      }
    } else if (equality == 0) {
      System.out.println("Encountered duplicate!");
    } else {
      if(node.getRight() == null) {
        node.setRight(new Node(t));
      } else {
        insert(t, node.getRight());
      }
    }
  }

  public interface TreeVisitor<T extends Comparable> {
    public void visit(Node<T> element);
  }
  
  public class PrintVisitor implements TreeVisitor<T> {
    public void visit(Node<T> node) {
      System.out.println(node.toVerboseString());
    }
  }

  public void printInOrder() {
    visitInOrder(root, new PrintVisitor());
  }

  private void visitInOrder(Node<T> node, TreeVisitor<T> visitor) {
    if(node.getLeft() != null) {
      visitInOrder(node.getLeft(), visitor);
    }

    visitor.visit(node);

    if(node.getRight() != null) {
      visitInOrder(node.getRight(), visitor);
    }
  }

  public boolean isEmpty() {
    return root == null;
  }

  public Iterator<T> getInOrderIterator() {
    if(root == null) {
      return Collections.emptyIterator();
    } else {
      return getInOrderIterator(root);
    }
  }

  public Iterator<T> getInOrderIterator(Node<T> node) {
    final LinkedList<T> queue = new LinkedList<>();
    
    TreeVisitor<T> queueVisitor = new TreeVisitor<T>() {
        public void visit(Node<T> node) {
          queue.add(node.getElement());
        }
      };

    visitInOrder(root, queueVisitor);

    return queue.iterator();
  }

  public void clear() {
    this.root = null;
  }
}
