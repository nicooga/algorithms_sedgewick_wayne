package algsex.chapter1.section3;

import java.util.*;
import edu.princeton.cs.algs4.*;

// 1.3.24 Write a method removeAfter() that takes a linked-list Node as argument
// and removes the node following the given one (and does nothing if the argument
// or the next field in the argument node is null).
public class Exercise24 {
  public static void main(String[] args) {
    LinkedList<Integer> list = new LinkedList<>();

    list.push(1);
    list.push(2);
    list.push(3);
    list.push(4);

    list.removeAfter(list.first.next);

    assert list.first.item == 4;
    assert list.first.next.item == 3;
    assert list.first.next.next == null;

    StdOut.println("Tests passed");
  }

  private static class LinkedList<T> {
    public Node first;

    public void push(T item) {
      Node oldFirst = first;

      first = new Node();
      first.item = item;
      first.next = oldFirst;
    }

    public void removeAfter(Node node) {
      node.next = null;
    }

    private class Node {
      T item;
      Node next;
    }
  }
}