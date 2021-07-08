package algsex.chapter1.section3;

import java.util.*;
import edu.princeton.cs.algs4.*;

// 1.3.40 Move-to-front. Read in a sequence of characters from standard input and
// maintain the characters in a linked list with no duplicates. When you read in a previously
// unseen character, insert it at the front of the list. When you read in a duplicate
// character, delete it from the list and reinsert it at the beginning. Name your program
// MoveToFront: it implements the well-known move-to-front strategy, which is useful for
// caching, data compression, and many other applications where items that have been
// recently accessed are more likely to be reaccessed.
public class Exercise40 {
  public static void main(String[] args) {
    UniqueDeque<Integer> deck = new UniqueDeque<>();

    deck.put(1); // 1
    deck.put(2); // 1 2
    deck.put(4); // 1 2 4
    deck.put(5); // 1 2 4 5
    deck.put(7); // 1 2 4 5 7
    deck.put(2); // 2 1 4 5 7
    deck.put(4); // 4 2 1 5 7
    deck.put(4); // 4 2 1 5 7
    deck.put(3); // 4 2 1 5 7 3

    assert deck.toString().equals("4 2 1 5 7 3 ");

    StdOut.println("Tests passed");
  }

  private static class UniqueDeque<T> {
    private Node first;
    private Node last;

    public void put(T item) {
      if (first != null && first.item == item) return;

      Node node = first;

      while (node != null) {
        if (node.next != null && node.next.item == item) {
          node.next = node.next.next;
          insertLeft(item);
          return;
        }

        node = node.next;
      }

      insertRight(item);
    }

    private void insertLeft(T item) {
      Node oldFirst = first;

      first = new Node();
      first.item = item;
      first.next = oldFirst;

      if (last == null) last = first;
    }

    private void insertRight(T item) {
      Node oldLast = last;

      last = new Node();
      last.item = item;

      if (oldLast == null) first = last;
      else oldLast.next = last;
    }

    public String toString() {
      if (first == null) return "(empty)";

      StringBuilder builder = new StringBuilder();
      Node node = first;

      while (node != null) {
        builder.append(node.item);
        builder.append(' ');
        node = node.next;
      }

      return builder.toString();
    }

    private class Node {
      T item;
      Node next;
    }
  }
}
