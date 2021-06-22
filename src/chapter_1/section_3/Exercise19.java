package algsex.chapter1.section3;

import java.util.*;
import edu.princeton.cs.algs4.*;

// 1.3.19 Give a code fragment that removes the last node in a linked list whose first node is first.
public class Exercise19 {
  public static void main(String[] args) {
    LinkedList<Integer> list = new LinkedList<>();

    list.push(1);
    list.push(2);
    list.push(3);

    assert list.pop() == 1;
    assert list.pop() == 2;
    assert list.pop() == 3;

    list.push(4);
    list.push(5);
    list.push(6);

    assert list.pop() == 4;
    assert list.pop() == 5;
    assert list.pop() == 6;

    StdOut.println("Tests passed");
  }

  private static class LinkedList<T> {
    private Node first;
    private Node last;
    private int N;

    public void push(T item)  {
      Node oldFirst = first;

      first = new Node();
      first.item = item;
      first.next = oldFirst;

      N++;

      if (N == 1) {
        last = first;
      } else {
        oldFirst.prev = first;
      }
    }

    public T pop() {
      T item = last.item;

      last = last.prev;

      if (last != null) {
        last.next = null;
      }

      N--;

      if (N == 0) {
        first = null;
      }

      return item;
    }

    private class Node {
      T item;
      Node next;
      Node prev;
    }
  }
}