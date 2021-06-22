package algsex.chapter1.section3;

import java.util.*;
import edu.princeton.cs.algs4.*;

// 1.3.27 Write a method max() that takes a reference to the first node in a linked list
// as argument and returns the value of the maximum key in the list.
// Assume that all keys are positive integers, and return 0 if the list is empty. 
public class Exercise27 {
  public static void main(String[] args) {
    LinkedList list = new LinkedList();

    list.push(322);
    list.push(22);
    list.push(938);
    list.push(493);
    list.push(422);
    list.push(123);

    assert max(list.first) == 938;

    list = new LinkedList();

    assert max(list.first) == 0;

    StdOut.println("Tests passed");
  }

  private static int max(Node node) {
    int result = 0;

    while (node != null) {
      if (node.item > result) result = node.item;
      node = node.next;
    }

    return result;
  }

  private static class LinkedList {
    Node first;

    public void push(int n) {
      Node oldFirst = first;

      first = new Node();
      first.item = n;
      first.next = oldFirst;
    }
  }

  private static class Node {
    int item;
    Node next;
  }
}
