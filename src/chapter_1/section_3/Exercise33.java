package algsex.chapter1.section3;

import java.util.*;
import edu.princeton.cs.algs4.*;

// 1.3.33 Deque. A double-ended queue or deque (pronounced “deck”) is like
// a stack or a queue but supports adding and removing items at both ends.
// A deque stores a collection of items and supports the following API:

// public class Deque<Item> implements Iterable<Item>
// - Deque() create an empty deque
// - boolean isEmpty() is the deque empty?
// - int size() number of items in the deque
// - void pushLeft(Item item) add an item to the left end
// - void pushRight(Item item) add an item to the right end
// - Item popLeft() remove an item from the left end
// - Item popRight() remove an item from the right end

// Write  a  class Deque that uses a doubly-linked list to implement this API and a class ResizingArrayDeque that uses a resizing array.
public class Exercise33 {
  public static void main(String[] args) {
    Deque<Integer> deque = new Deque<>();

    assert deque.isEmpty();
    assert deque.size() == 0;

    deque.pushLeft(1);
    deque.pushLeft(2);

    assert !deque.isEmpty();
    assert deque.size == 2;

    assert deque.popLeft() == 2;
    assert deque.popLeft() == 1;

    assert deque.isEmpty();
    assert deque.size() == 0;

    deque.pushRight(1);
    deque.pushRight(2);

    assert !deque.isEmpty();
    assert deque.size() == 2;

    assert deque.popRight() == 2;
    assert deque.popRight() == 1;

    assert deque.isEmpty();
    assert deque.size() == 0;

    StdOut.println("Tests passed");
  }

  private static class Deque<T> {
    private Node first;
    private Node last;
    private int size;

    public boolean isEmpty() { return size == 0; }
    public int size() { return size; }

    public void pushLeft(T item) {
      Node oldFirst = first;

      first = new Node();
      first.item = item;
      first.next = oldFirst;

      if (oldFirst == null) last = first;
      else oldFirst.prev = first;

      size++;
    }

    public T popLeft() {
      T item = first.item;
      first = first.next;
      if (first == null) last = null;
      size--;
      return item;
    }

    public void pushRight(T item) {
      Node oldLast = last;

      last = new Node();
      last.item = item;
      last.prev = oldLast;

      if (oldLast == null) first = last;
      else oldLast.next = last;

      size++;
    }

    public T popRight() {
      T item = last.item;
      last = last.prev;
      if (last == null) first = null;
      size--;
      return item;
    }

    private class Node {
      T item;
      Node next;
      Node prev;
    }
  }
}
