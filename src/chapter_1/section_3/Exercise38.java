package algsex.chapter1.section3;

import java.util.*;
import edu.princeton.cs.algs4.*;

// 1.3.38 Delete kth element. Implement a class that supports the following API:
//
// public class GeneralizedQueue<Item>
// - GeneralizedQueue() create an empty queue
// - boolean isEmpty() is the queue empty?
// - void insert(Item x) add an item
// - Item delete(int k) delete and return the kth least recently inserted item
//
// First, develop an implementation that uses an array implementation, and then develop
// one that uses a linked-list implementation.
//
// Note: the algorithms and data structures
// that we introduce in Chapter 3 make it possible to develop an implementation that
// can guarantee that both insert() and delete() take time prortional to the logarithm
// of the number of items in the queue — see Exercise 3.5.27.
public class Exercise38 {
  public static void main(String[] args) {
    GeneralizedQueue<Integer> q = new GeneralizedQueue<>();

    q.insert(1);
    q.insert(2);
    q.insert(3);
    q.insert(4);

    assert !q.isEmpty();

    assert q.delete(1) == 1;
    assert q.delete(1) == 2;
    assert q.delete(1) == 3;
    assert q.delete(1) == 4;

    try {
      q.delete(1);
      assert false : "An exception should have been raised";
    } catch (RuntimeException e) {
      assert e.getMessage().equals("Queue is empty");
    }

    q.insert(5);
    q.insert(6);
    q.insert(7);
    q.insert(8);

    assert !q.isEmpty();

    assert q.delete(1) == 5;
    assert q.delete(1) == 6;
    assert q.delete(1) == 7;
    assert q.delete(1) == 8;

    assert q.isEmpty();

    q.insert(9);
    q.insert(10);
    q.insert(11);
    q.insert(12);

    assert q.delete(4) == 12;
    assert q.delete(3) == 11;
    assert q.delete(2) == 10;
    assert q.delete(1) == 9;

    StdOut.println("Tests passed");
  }

  private static class GeneralizedQueue<T> {
    private Node first;
    private Node last;
    private int size;

    public boolean isEmpty() { return size == 0; }

    public void insert(T x) {
      Node oldLast = last;

      last = new Node();
      last.item = x;

      if (oldLast == null) first = last;
      else oldLast.next = last;

      size++;
    }

    public T delete(int k) {
      if (isEmpty()) throw new RuntimeException("Queue is empty");
      assert k >= 1 : "Argument k must be equal to or greater than 1";

      Node node = first;
      Node prevNode = first;

      for (int i = 0; i <= k - 2; i++) {
        prevNode = node;
        node = node.next;
      }

      T item = node.item;

      if (k == 1) first = node.next;
      else prevNode.next = prevNode.next.next;

      size--;

      if (size == 0) {
        first = null;
        last = null;
      }

      return item;
    }

    public String toString() {
      Node node = first;
      StringBuilder builder = new StringBuilder();

      while (node != null) {
        builder.append(node.item.toString());
        builder.append(' ');
        node = node.next;
      }

      return builder.toString();
    }

    private class Node {
      T item;
      Node next;
    }
  }
}
