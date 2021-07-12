package algsex.chapter1.section3;

import java.util.*;
import edu.princeton.cs.algs4.*;

// 1.3.48 Two stacks with a deque.
// Implement two stacks with a single deque so that each operation takes a constant number of deque operations (see Exercise 1.3.33).
public class Exercise48 {
  public static void main(String[] args) {
    DoubleStack<Integer> ds = new DoubleStack();

    ds.pushLeft(1);
    ds.pushLeft(2);
    ds.pushLeft(3);

    ds.pushRight(4);
    ds.pushRight(5);
    ds.pushRight(6);

    assert ds.popLeft() == 3;
    assert ds.popLeft() == 2;
    assert ds.popLeft() == 1;

    try {
      StdOut.println(ds.popLeft());
      assert false;
    } catch(RuntimeException e) {
      assert e.getMessage() == "Left stack is empty";
    }

    assert ds.popRight() == 6;
    assert ds.popRight() == 5;
    assert ds.popRight() == 4;

    try {
      ds.popRight();
      assert false;
    } catch(RuntimeException e) {
      assert e.getMessage() == "Right stack is empty";
    }

    StdOut.println("Tests passed");
  }

  private static class DoubleStack<T> {
    private Deque<T> deque = new Deque<>();
    private int leftSize;
    private int rightSize;

    private void pushLeft(T item) {
      deque.pushLeft(item);
      leftSize++;
    }

    private T popLeft() {
      if (leftSize == 0) throw new RuntimeException("Left stack is empty");
      leftSize--;
      return deque.popLeft();
    }

    private void pushRight(T item) {
      deque.pushRight(item);
      rightSize++;
    }

    private T popRight() {
      if (rightSize == 0) throw new RuntimeException("Right stack is empty");
      rightSize--;
      return deque.popRight();
    }

    private class Deque<T> {
      private Node first;
      private Node last;

      public void pushLeft(T item) {
        Node oldFirst = first;

        first = new Node();
        first.item = item;
        first.next = oldFirst;

        if (oldFirst == null) last = first;
        else oldFirst.prev = first;
      }

      public T popLeft() {
        T item = first.item;

        first = first.next;

        if (first == null) last = null;
        else first.prev = null;

        return item;
      }

      public void pushRight(T item) {
        Node oldLast = last;

        last = new Node();
        last.item = item;
        last.prev = oldLast;

        if (oldLast == null) first = last;
        else oldLast.next = last;
      }

      public T popRight() {
        T item = last.item;

        last = last.prev;

        if (last == null) first = null;
        else last.next = null;

        return item;
      }

      private class Node {
        T item;
        Node next;
        Node prev;
      }
    }
  }
}
