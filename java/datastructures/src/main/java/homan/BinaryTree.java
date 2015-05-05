package homan;

import java.util.*;

public class BinaryTree<T extends Comparable> {

  public static class Node<T extends Comparable> {
    T element;
    Node<T> left;
    Node<T> right;

    public Node(T element, Node<T> left, Node<T> right) {
      Utils.notNull(element);
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

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (!(o instanceof Node)) return false;

      Node<?> node = (Node<?>) o;

      if (!element.equals(node.element)) return false;
      if (left != null ? !left.equals(node.left) : node.left != null)
        return false;
      if (right != null ? !right.equals(node.right) : node.right != null)
        return false;

      return true;
    }

    @Override
    public int hashCode() {
      int result = element.hashCode();
      result = 31 * result + (left != null ? left.hashCode() : 0);
      result = 31 * result + (right != null ? right.hashCode() : 0);
      return result;
    }
  }

  private Node<T> root = null;

  public BinaryTree() { /** Nothing to do **/ }

  // Visible for testing
  BinaryTree(Node<T> root) {
    this.root = root;
  }

  public void insert(T t) {
    Utils.notNull(t);
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
    void visit(Node<T> element);
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

  public boolean contains(T t) {
    return contains(t, root);
  }

  private boolean contains(T t, Node<T> node) {
    if(node == null) {
      return false;
    }

    int equality = t.compareTo(node.getElement());
    if(equality < 0) {
      return contains(t, node.getLeft());
    } else if (equality == 0) {
      return true;
    } else {
      return contains(t, node.getRight());
    }
  }

  /**
   * Find and disconnect the minimum node in the tree
   */
  protected Node<T> removeMinNode() {
    // Empty tree
    if(root == null) {
      return null;
    }

    // Just root tree
    if(root.getRight() == null && root.getLeft() == null) {
      Node<T> toReturn = root;
      root = null;
      return toReturn;
    }

    Node<T> cursor = root;
    while(cursor.getRight().getRight() != null) {
      cursor = cursor.getRight();
    }

    Node<T> toReturn = cursor.getRight();
    cursor.setRight(null);
    return toReturn;
  }

  public boolean remove(T t) {
    if(root == null) {
      return false;
    }

    if(root.element.equals(t)) {
      return removeRoot();
    }
    return remove(t, root, null);
  }

  private boolean removeRoot() {
    if(root.getLeft() == null && root.getRight() == null) {
      root = null;
      return true;
    }

    if(root.getLeft() != null && root.getRight() == null) {
      root = root.getLeft();
      return true;
    }

    if(root.getLeft() == null && root.getRight() != null) {
      root = root.getRight();
      return true;
    }

    return true;
  }

  private boolean remove(T t, Node<T> node, Node<T> parent) {
    return true;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof BinaryTree)) return false;

    BinaryTree<?> that = (BinaryTree<?>) o;

    return !(root != null ? !root.equals(that.root) : that.root != null);

  }

  @Override
  public int hashCode() {
    return root != null ? root.hashCode() : 0;
  }
}
