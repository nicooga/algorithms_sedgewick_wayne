package algsex.chapter1.section3;

import java.util.*;
import edu.princeton.cs.algs4.*;

// 1.3.29 Write a Queue implementation that uses a circular linked list,
// which is the same as a linked list except that no links are null and
// the value of last.next is first when-ever the list is not empty.
// Keep only one Node instance variable (last).
public class Exercise29 {
  public static void main(String[] args) {
    Queue<Integer> queue = new Queue<>();

    queue.enqueue(1);
    queue.enqueue(2);
    queue.enqueue(3);

    assert queue.dequeue() == 1;
    assert queue.dequeue() == 2;

    queue.enqueue(4);
    queue.enqueue(5);
    queue.enqueue(6);

    assert queue.dequeue() == 3;
    assert queue.dequeue() == 4;
    assert queue.dequeue() == 5;
    assert queue.dequeue() == 6;

    StdOut.println("All tests passed");
  }

  private static class Queue<T> {
    Node last;

    public void enqueue(T item) {
      Node node = new Node();
      node.item = item;

      if (last == null) {
        node.next = node;
        last = node;
      } else {
        node.next = last.next;
        last.next = node;
        last = node;
      }
    }

    public T dequeue() {
      T item = last.next.item;

      if (last.next == last) last = null;
      else last.next = last.next.next;

      return item;
    }

    private class Node  {
      T item;
      Node next;
    }
  }
}
