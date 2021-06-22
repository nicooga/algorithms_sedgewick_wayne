package algsex.chapter1.section3;

import java.util.*;
import edu.princeton.cs.algs4.*;

// 1.3.30 Write a function that takes the first Node in a linked list as argument
// and (de-structively) reverses the list, returning the first Node in the result.
public class Exercise30 {
  public static void main(String[] args) {
    LinkedList list = new LinkedList();

    list.push(1);
    list.push(2);
    list.push(3);

    reverse(list);

    Node node = list.first;

    while(node != null) {
      StdOut.println(node.item);
      node = node.next;
    }

    assert list.first.item == 1;
    assert list.first.next.item == 2;
    assert list.first.next.next.item == 3;
    assert list.first.next.next.next == null;
  }

  private static void reverse(LinkedList list) {
    Node curNode = list.first;
    Node prevNode = null;
    Node nextNode = null;

    while(curNode != null) {
      nextNode = curNode.next;
      curNode.next = prevNode;
      prevNode = curNode;
      curNode = nextNode;
    }

    list.first = prevNode;
  }

  private static class LinkedList {
    public Node first;

    public void push(int item) {
      Node oldFirst = first;

      first = new Node();
      first.item = item;
      first.next = oldFirst;
    }
  }

  private static class Node {
    int item;
    Node next;

    public String toString() {
      return Integer.toString(item);
    }
  }
}
