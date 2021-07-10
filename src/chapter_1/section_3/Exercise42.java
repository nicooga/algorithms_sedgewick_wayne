package algsex.chapter1.section3;

import java.util.*;
import edu.princeton.cs.algs4.*;

// 1.3.42 Copy a stack. Create a new constructor for the linked-list implementation of
// Stack so that
//
//     Stack<Item> t = new Stack<Item>(s);
//
// makes t a reference to a new and independent copy of the stack s.
public class Exercise42 {
  public static void main(String[] args) {
    Stack<Integer> s1 = new Stack();

    s1.push(1);
    s1.push(2);
    s1.push(3);

    Stack<Integer> s2 = new Stack(s1);

    assert s1.pop() == 3;
    assert s1.pop() == 2;
    assert s1.pop() == 1;

    assert s2.pop() == 3;
    assert s2.pop() == 2;
    assert s2.pop() == 1;

    StdOut.println("Tests passed");
  }

  private static class Stack<T> {
    private Node first;

    Stack() {}

    Stack(Stack original) {
     Node originalNode = original.first;
     Node prevNode = null;

      while (originalNode != null) {
        Node newNode = new Node();
        newNode.item = originalNode.item;

        if (prevNode == null) first = newNode;
        else prevNode.next = newNode;

        prevNode = newNode;
        originalNode = originalNode.next;
      }
    }

    public void push(T item) {
      Node oldFirst = first;

      first = new Node();
      first.item = item;
      first.next = oldFirst;
    }

    public T pop() {
      if (first == null) throw new RuntimeException("Stack is empty");
      T item = first.item;
      first = first.next;
      return item;
    }

    private class Node {
      T item;
      Node next;
    }
  }
}
