package homan.datastructures;

import java.util.*;
import org.junit.*;
import static org.junit.Assert.*;
import org.paukov.combinatorics.*;

public class BinaryTreeTest {

  @Test
  public void treeInitiallyIsEmpty() {
    BinaryTree<String> bt = new BinaryTree<>();

    assertTrue(bt.isEmpty());
  }

  @Test
  public void afterFirstInsertNoLongerEmpty() {
    BinaryTree<String> bt = new BinaryTree<>();
    bt.insert("Hello");
    assertFalse(bt.isEmpty());
  }

  public BinaryTree<String> getBalancedTree() {
    BinaryTree<String> bt = new BinaryTree<>();
    bt.insert("m");
    bt.insert("a");
    bt.insert("z");
    bt.insert("f");
    bt.insert("b");

    return bt;
  }

  @Test
  public void containsWorks() {
    BinaryTree<String> bt = getBalancedTree();

    assertTrue(bt.contains("z"));
    assertTrue(bt.contains("m"));
    assertFalse(bt.contains("c"));

  }

  @Test
  public void insertWorks() {
    BinaryTree<String> bt = getBalancedTree();
    bt.printInOrder();
  }

  @Test
  public void inOrderIteratorWorks() {
    BinaryTree<String> bt = getBalancedTree();

    Iterator<String> iter = bt.getInOrderIterator();

    while(iter.hasNext()) {
      String i = iter.next();
      System.out.println("howdy : " + i);
    }
  }

  @Test
  public void inOrderTraversalWorks() {
    final int NUM_LETTERS = 10;
    String[] alphabet = new String[NUM_LETTERS];
    Collection<String> expected = new ArrayList<String>(NUM_LETTERS);

    for(int i = 0; i < NUM_LETTERS; i++) {
      alphabet[i] = String.valueOf((char)(i + 65 + 32));
    }

    Collections.addAll(expected, alphabet);
    
    ICombinatoricsVector<String> originalVector = Factory.createVector(alphabet);
    Generator<String> gen = Factory.createPermutationGenerator(originalVector);
    int counter = 0;

    BinaryTree<String> bt = new BinaryTree<>();
    ArrayList<String> fromTree = new ArrayList<>(NUM_LETTERS);
    for (ICombinatoricsVector<String> perm : gen) {
      counter++;
      for(String s : perm.getVector()) {
        bt.insert(s);
      }
      Iterator<String> iter = bt.getInOrderIterator();

      while(iter.hasNext()) {
        fromTree.add(iter.next());
      }

      assertEquals(expected, fromTree);
      bt.clear();
      fromTree.clear();
    }
    System.out.println("Counter = " + counter);
  }

}
