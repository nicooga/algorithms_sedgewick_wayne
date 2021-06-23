package algsex.chapter1.section3;

import java.util.*;
import edu.princeton.cs.algs4.*;

// 1.3.32 Steque. A stack-ended queue or steque is a data type that supports push, pop, and enqueue.
// Articulate an API for this ADT. Develop a linked-list-based implementation.
public class Exercise32 {
  public static void main(String[] args) {
    Steque<Integer> steque = new Steque<>();

    steque.push(1);
    steque.push(2);
    steque.enqueue(3);

    assert steque.pop() == 2;
    assert steque.pop() == 1;
    assert steque.pop() == 3;

    StdOut.println("Tests passed");
  }

  private static class Steque<T> {
    Node first;
    Node last;

    public void push(T item) {
      Node oldFirst = first;

      first = new Node();
      first.item = item;
      first.next = oldFirst;

      if (last == null) last = first;
    }

    public T pop() {
      T item = first.item;
      first = first.next;
      return item;
    }

    public void enqueue(T item) {
      Node oldLast = last;

      last = new Node();
      last.item = item;

      if (oldLast == null) first = last;
      else oldLast.next = last;
    }

    private class Node {
      T item;
      Node next;
    }
  }
}
