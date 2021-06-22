package algsex.chapter1.section3;

import java.util.*;
import edu.princeton.cs.algs4.*;

// 1.3.26 Write a method remove() that takes a linked list and a string key as arguments
// and removes all of the nodes in the list that have key as its item field.
public class Exercise26 {
  public static void main(String[] args) {
    LinkedList list = new LinkedList();

    list.push("asdf");
    list.push("asdf");
    list.push("qwer");
    list.push("asdf");
    list.push("uiop");
    list.push("zxcv");

    remove(list, "asdf");

    assert list.first.item.equals("zxcv");
    assert list.first.next.item.equals("uiop");
    assert list.first.next.next.item.equals("qwer");
    assert list.first.next.next.next == null;

    StdOut.println("Tests passed");
  }

  private static void remove(LinkedList list, String key) {
    Node prevNode = null;
    Node curNode = list.first;

    while(curNode != null) {
      if (curNode.item.equals(key)) {
        if (curNode == list.first) list.first = curNode.next;
        else prevNode.next = curNode.next;
      } else {
        prevNode = curNode;
      }

      curNode = curNode.next;
    }
  }

  private static class LinkedList {
    public Node first;

    public Node push(String item) {
      Node oldFirst = first;

      first = new Node();
      first.item = item;
      first.next = oldFirst;

      return first;
    }
  }

  private static class Node {
    String item;
    Node next;

    public String toString() {
      return "Node: " + item;
    }
  }
}
