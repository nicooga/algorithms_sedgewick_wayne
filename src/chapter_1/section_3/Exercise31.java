package algsex.chapter1.section3;

import java.util.*;
import edu.princeton.cs.algs4.*;

// 1.3.31 Implement a nested class DoubleNode for building doubly-linked lists,
// where each node contains a reference to the item preceding it and the item
// following it in the list (null if there is no such item).
// Then implement static methods for the following tasks: insert at the beginning,
// insert at the end, remove from the beginning, remove from the end, insert before a given node,
// insert after a given node, and remove a given node.
public class Exercise31 {
  public static void main(String[] args) {
    DoublyLinkedList list = new DoublyLinkedList();

    list.insertLeft(1);
    list.insertLeft(2);

    assert list.first.item == 2;
    assert list.first.next.item == 1;
    assert list.first.next.next == null;
    assert list.last.item == 1;
    assert list.last.prev.item == 2;
    assert list.last.prev.prev == null;

    list = new DoublyLinkedList();

    list.insertRight(1);
    list.insertRight(2);

    assert list.first.item == 1;
    assert list.first.next.item == 2;
    assert list.first.next.next == null;
    assert list.last.item == 2;
    assert list.last.prev.item == 1;
    assert list.last.prev.prev == null;

    list = new DoublyLinkedList();

    list.insertLeft(1);
    list.insertLeft(2);

    assert list.popLeft() == 2;
    assert list.first.item == 1;
    assert list.first.prev == null;
    assert list.popLeft() == 1;
    assert list.first == null;
    assert list.last == null;

    list = new DoublyLinkedList();

    list.insertLeft(1);
    list.insertLeft(2);

    assert list.popRight() == 1;
    assert list.last.item == 2;
    assert list.last.next == null;
    assert list.popRight() == 2;
    assert list.last == null;
    assert list.first == null;


    list = new DoublyLinkedList();
    list.insertLeft(1);
    DoubleNode node = list.insertLeft(2);
    list.insertBefore(node, 3);

    assert list.first.item == 3;
    assert list.first.next.item == 2;
    assert list.first.next.next.item == 1;
    assert list.first.next.next.next == null;
    assert list.last.item == 1;
    assert list.last.prev.item == 2;
    assert list.last.prev.prev.item == 3;
    assert list.last.prev.prev.prev == null;

    list = new DoublyLinkedList();
    list.insertRight(1);
    node = list.insertRight(2);
    list.insertAfter(node, 3);

    assert list.first.item == 1;
    assert list.first.next.item == 2;
    assert list.first.next.next.item == 3;
    assert list.first.next.next.next == null;
    assert list.last.item == 3;
    assert list.last.prev.item == 2;
    assert list.last.prev.prev.item == 1;
    assert list.last.prev.prev.prev == null;

    list = new DoublyLinkedList();
    list.insertRight(1);
    node = list.insertRight(2);
    list.insertRight(3);
    list.remove(node);

    assert list.first.item == 1;
    assert list.first.next.item == 3;
    assert list.first.next.next == null;
    assert list.last.item == 3;
    assert list.last.prev.item == 1;
    assert list.last.prev.prev == null;

    StdOut.println("All tests passed");
  }

  private static class DoublyLinkedList {
    public DoubleNode first;
    public DoubleNode last;

    public DoubleNode insertLeft(int item) {
      DoubleNode oldFirst = first;

      first = new DoubleNode();
      first.item = item;
      first.next = oldFirst;

      if (oldFirst == null) {
        last = first;
      } else  {
        oldFirst.prev = first;
      }

      return first;
    }

    public DoubleNode insertRight(int item) {
      DoubleNode oldLast = last;

      last = new DoubleNode();
      last.item = item;
      last.prev = oldLast;

      if (oldLast == null) {
        first = last;
      } else {
        oldLast.next = last;
      }

      return last;
    }

    public int popLeft() {
      int item = first.item;

      first = first.next;

      if (first == null) last = null;
      else first.prev = null;

      return item;
    }

    public int popRight() {
      int item = last.item;

      last = last.prev;

      if (last == null) first = null;
      else last.next = null;

      return item;
    }

    public void insertBefore(DoubleNode node, int item) {
      DoubleNode newNode = new DoubleNode();

      newNode.item = item;
      newNode.prev = node.prev;
      newNode.next = node;

      if (node.prev != null) node.prev.next = newNode;

      node.prev = newNode;

      if (node == first) first = newNode;
    }

    public void insertAfter(DoubleNode node, int item) {
      DoubleNode newNode = new DoubleNode();

      newNode.item = item;
      newNode.prev = node;
      newNode.next = node.next;

      if (node.next != null) node.next.prev = newNode;

      node.next = newNode;

      if (node == last) last = newNode;
    }

    public void remove(DoubleNode node) {
      if (node.prev != null) node.prev.next = node.next;
      if (node.next != null) node.next.prev = node.prev;
      if (node == first) first = node.next;
      if (node == last) last = node.prev;
    }
  }

  private static class DoubleNode {
    int item;
    DoubleNode next;
    DoubleNode prev;

    public String toString() {
      return Integer.toString(item);
    }
  }
}
