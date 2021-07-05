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
    assert q.delete(1) == 4; // fails here

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
    private T[] items;
    private int first;
    private int last = -1;
    private int size;

    GeneralizedQueue() {
      items = (T[]) new Object[10];
    }

    public boolean isEmpty() { return size == 0; }

    public void insert(T x) {
      if (size == items.length) resize(items.length * 2);

      last++;
      this.items[last] = x;
      size++;
    }

    public T delete(int k) {
      if (size == 0) throw new RuntimeException("Queue is empty");

      int selectedIndex = first + k - 1;

      T item = items[selectedIndex];
      items[selectedIndex] = null;

      if (selectedIndex == first)
        while (items[first] == null && first != last)
          first++;

      if (first == last && items[first] == null) {
        first = 0;
        last = -1;
      }

      size--;

      return item;
    }

    private void resize(int newSize) {
      T[] temp = (T[]) new Object[newSize];
      for (int i = 0; i < size; i++) temp[i] = items[i];
      items = temp;
    }
  }
}
