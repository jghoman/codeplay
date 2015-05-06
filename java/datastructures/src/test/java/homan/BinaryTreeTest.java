package homan;

import org.junit.Ignore;
import org.junit.Test;
import org.paukov.combinatorics.Factory;
import org.paukov.combinatorics.Generator;
import org.paukov.combinatorics.ICombinatoricsVector;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

import static org.junit.Assert.*;

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
    final int NUM_LETTERS = 9;
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

      assertTrue(bt.validate());
      bt.clear();
      fromTree.clear();
    }
    System.out.println("Counter = " + counter);
  }

  @Test
  public void inOrderIteratorWorks() {
    BinaryTree<String> bt = getBalancedTree();

    Iterator<String> iter = bt.getInOrderIterator();

    while(iter.hasNext()) {
      String i = iter.next();
      System.out.println("howdy : " + i);
    }
    // TODO: Actually do a test here
  }

  @Test
  public void inOrderTraversalWorks() {
    final int NUM_LETTERS = 9;
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

  @Test
  public void findMinValueWorks() {
    final int NUM_LETTERS = 9;
    String[] alphabet = new String[NUM_LETTERS];
    Collection<String> expected = new ArrayList<String>(NUM_LETTERS);

    for(int i = 0; i < NUM_LETTERS; i++) {
      alphabet[i] = String.valueOf((char)(i + 65 + 32));
    }

    Collections.addAll(expected, alphabet);

    ICombinatoricsVector<String> originalVector = Factory.createVector(alphabet);
    Generator<String> gen = Factory.createPermutationGenerator(originalVector);

    BinaryTree<String> bt = new BinaryTree<>();
    ArrayList<String> fromTree = new ArrayList<>(NUM_LETTERS);
    for (ICombinatoricsVector<String> perm : gen) {
      for(String s : perm.getVector()) {
        bt.insert(s);
      }

      assertEquals("a", bt.findMinValue());

      bt.clear();
      fromTree.clear();
    }
  }

  @Test
  public void removalFromEmptyTreeWorks() {
    BinaryTree<String> bt = new BinaryTree<>();

    assertFalse(bt.remove("not in here"));
  }

  @Test
  public void removalFromTreeOfJustToBeRemovedWorks1() {
    // Remove the root, which has no children
    BinaryTree<String> bt = new BinaryTree<>();

    bt.insert("howdy");

    assertTrue(bt.contains("howdy"));
    assertTrue(bt.remove("howdy"));
    assertFalse(bt.contains("howdy"));
  }

  @Test
  public void removalFromTreeOfJustToBeRemovedWorks2() {
    // Remove the root, which has just a left child
    BinaryTree<String> bt = new BinaryTree<>();
    bt.insert("m");
    bt.insert("a");

    assertTrue(bt.contains("m"));
    assertTrue(bt.remove("m"));
    assertFalse(bt.contains("m"));
    assertTrue(bt.contains("a"));
  }

  @Test
  public void removalFromTreeOfJustToBeRemovedWorks3() {
    // Remove the root, which has just a right child
    BinaryTree<String> bt = new BinaryTree<>();
    bt.insert("m");
    bt.insert("z");

    assertTrue(bt.contains("m"));
    assertTrue(bt.remove("m"));
    assertFalse(bt.contains("m"));
    assertTrue(bt.contains("z"));
  }

  @Test
  public void removalFromTreeOfJustToBeRemovedWorks4() {
    // Remove the root, which has just both children
    BinaryTree<String> bt = new BinaryTree<>();
    bt.insert("m");
    bt.insert("z");
    bt.insert("a");

    assertTrue(bt.contains("m"));
    assertTrue(bt.remove("m"));
    assertFalse(bt.contains("m"));
    assertTrue(bt.contains("z"));
    assertTrue(bt.contains("a"));
  }

  @Test
  public void removalFromRightSlantingTreeWorks() {
    BinaryTree<String> bt = new BinaryTree<>();

    bt.insert("m");
    bt.insert("z");

    assertTrue(bt.remove("m"));
    assertTrue(bt.contains("z"));

    assertTrue(bt.remove("z"));
    assertTrue(bt.isEmpty());
  }

  @Test
  public void removalPermutations() {
    final int NUM_LETTERS = 8;
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
      for(String letter : expected) {

        counter++;
        for (String s : perm.getVector()) {
          bt.insert(s);
        }
        assertTrue("Should have removed " + letter, bt.remove(letter));
        assertFalse(bt.contains(letter));
        assertTrue(bt.validate());

        bt.clear();
        fromTree.clear();
      }

    }
    System.out.println("zCounter = " + counter);
  }
}
