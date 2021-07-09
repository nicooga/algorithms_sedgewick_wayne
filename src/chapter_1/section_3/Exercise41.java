package algsex.chapter1.section3;

import java.util.*;
import edu.princeton.cs.algs4.*;

// 1.3.41 Copy a queue. Create a new constructor so that
//
//     Queue<Item> r = new Queue<Item>(q);
//
// makes r a reference to a new and independent copy of the queue q. You should be able
// to enqueue and dequeue from either q or r without influencing the other.
// Hint: Delete all of the elements from q and add these elements to both q and r.
public class Exercise41 {
  public static void main(String[] args) {
    Queue<Integer> q1 = new Queue<>();

    q1.enqueue(1);
    q1.enqueue(2);
    q1.enqueue(3);

    Queue<Integer> q2 = new Queue<>(q1);

    assert q2.dequeue() == 1;
    assert q2.dequeue() == 2;
    assert q2.dequeue() == 3;

    assert q1.dequeue() == 1;
    assert q1.dequeue() == 2;
    assert q1.dequeue() == 3;

    StdOut.println("Tests passed");
  }

  private static class Queue<T> {
    private Node first;
    private Node last;

    Queue() {}

    Queue(Queue<T> original) {
      Node node = original.first;

      while (node != null) {
        enqueue(node.item);
        node = node.next;
      }
    }

    public void enqueue(T item) {
      Node oldLast = last;

      last = new Node();
      last.item = item;

      if (oldLast == null) first = last;
      else oldLast.next = last;
    }

    public T dequeue() {
      T item = first.item;

      first = first.next;
      if (first == null) last = null;

      return item;
    }

    private class Node {
      T item;
      Node next;
    }
  }
}
