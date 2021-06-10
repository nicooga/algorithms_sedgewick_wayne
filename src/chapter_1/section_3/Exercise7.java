package algsex.chapter1.section3;

import java.util.*;
import edu.princeton.cs.algs4.*;

// 1.3.7 Add a method peek() to Stack that returns the most recently inserted item on the stack (without popping it).
public class Exercise7 {
  public static void main(String[] args) {
    Stack<Integer> stack = new Stack<>();

    stack.push(1);

    assert stack.peek() == 1;

    stack.push(2);

    assert stack.peek() == 2;

    stack.push(3);

    assert stack.peek() == 3;

    StdOut.println("All tests passed");
  }

  public static class Stack<T> {
    private Node first;

    public void push(T item) {
      Node oldFirst = first;

      first = new Node();
      first.item = item;
      first.next = oldFirst;
    }

    public T peek() {
      if (first == null) throw new RuntimeException("Stack is empty");
      return first.item;
    }

    private class Node {
      T item;
      Node next;
    }
  }
}
