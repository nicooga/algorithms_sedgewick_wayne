package algsex.chapter1.section3;

import java.util.*;
import edu.princeton.cs.algs4.*;

public class Exercise28 {
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
    return max(node, 0);
  }

  private static int max(Node node, int acc) {
    if (node == null)
      return acc;
    else if (node.item > acc)
      return max(node.next, node.item);
    else
      return max(node.next, acc);
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
