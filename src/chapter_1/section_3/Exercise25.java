package algsex.chapter1.section3;

import java.util.*;
import edu.princeton.cs.algs4.*;

// 1.3.25 Write a method insertAfter() that takes two linked-list Node arguments and inserts
// the second after the first on its list (and does nothing if either argument is null).
public class Exercise25 {
  public static void main(String[] args) {
    LinkedList<Integer> list = new LinkedList<>();

    Node<Integer> node1 = list.push(1);
    Node<Integer> node2 = list.push(2);
    Node<Integer> node3 = list.push(3);

    Node<Integer> node4 = new Node();
    node4.item = 4;

    list.insertAfter(node3, node4);

    assert node1.next == null;
    assert node2.next == node1;
    assert node4.next == node2;
    assert node3.next == node4;

    StdOut.println("Tests passed");
  }

  private static class LinkedList<T> {
    public Node<T> first;

    public Node push(T item) {
      Node<T> oldFirst = first;

      first = new Node<T>();
      first.item = item;
      first.next = oldFirst;

      return first;
    }

    public void insertAfter(Node lhs, Node rhs) {
      Node temp = lhs.next;
      lhs.next = rhs;
      rhs.next = temp;
    }
  }

  private static class Node<T> {
    T item;
    Node next;
  }
}
