package homan;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;

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

  protected boolean validate() {
    if(root == null) {
      return true;
    }

    return validate(root);
  }

  private boolean validate(Node<T> node) {
    if(node.getLeft() != null) {
      if(node.getLeft().element.compareTo(node.element) >= 0) {
        return false;
      }
      if(!validate(node.getLeft())) {
        return false;
      }
    }

    if(node.getRight() != null) {
      if(node.getRight().element.compareTo(node.element) < 0) {
        return false;
      }
      if(!validate(node.getRight())) {
        return false;
      }
    }
    return true;
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

  protected T findMinValue() {
    return findMinValue(root);
  }

  protected T findMinValue(Node<T> node) {
    if(node == null) {
      return null;
    }

    if(node.getLeft() == null) {
      return node.getElement();
    }

    return findMinValue(node.getLeft());
  }

  public boolean remove(T t) {
    if(root == null) {
      return false;
    }

    if(root.element.equals(t)) {
      return removeRoot();
    }

    if(t.compareTo(root.element) < 0) {
      return remove(t, root.getLeft(), root);
    } else {
      return remove(t, root.getRight(), root);
    }
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

    // Root has both left and right children, restructure tree around
    // right side min.
    T rightSideMin = findMinValue(root.getRight());
    assert(rightSideMin != null);
    remove(rightSideMin);
    root.element = rightSideMin;
    return true;
  }

  private boolean remove(T t, Node<T> node, Node<T> parent) {
    if(node == null) {
      return false;
    }

    if(node.element.equals(t)) {
      if(node.getLeft() == null && node.getRight() == null) {
        // leaf, just delete it
        if(parent.getLeft() == node) {
          parent.setLeft(null);
        } else {
          parent.setRight(null);
        }
        return true;
      }

      if(node.getLeft() != null && node.getRight() == null) {
        if(parent.getLeft() == node) {
          parent.setLeft(node.getLeft());
        } else {
          parent.setRight(node.getLeft());
        }
        return true;
      }

      if(node.getLeft() == null && node.getRight() != null) {
        if(parent.getLeft() == node) {
          parent.setLeft(node.getRight());
        } else {
          parent.setRight(node.getRight());
        }
        return true;
      }

      // Both of node's children exist...
      T rightSideMin = findMinValue(node.getRight());
      assert(rightSideMin != null);
      remove(rightSideMin, node.getRight(), node);
      node.element = rightSideMin;
      return true;
    }

    if(t.compareTo(node.element) < 0) {
      return remove(t, node.getLeft(), node);
    } else {
      return remove(t, node.getRight(), node);
    }
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
