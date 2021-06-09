package algsex.chapter1.section3;

import java.util.*;
import edu.princeton.cs.algs4.*;

public class Exercise2 {
  public static void main(String[] args) {
    Stack<String> s = new Stack<String>();

    consumeLine(s, "it");    // it
    consumeLine(s, "was");   // it was
    consumeLine(s, "-");     // it (was)
    consumeLine(s, "the");   // it the
    consumeLine(s, "best");  // it the best
    consumeLine(s, "-");     // it the (best)
    consumeLine(s, "of");    // it the of
    consumeLine(s, "times"); // it the of times
    consumeLine(s, "-");     // it the of (times)
    consumeLine(s, "-");     // it the (of)
    consumeLine(s, "-");     // it (the)
    consumeLine(s, "it");    // it it
    consumeLine(s, "was");   // it it was
    consumeLine(s, "-");     // it it (was)
    consumeLine(s, "the");   // it it the
    consumeLine(s, "-");     // it it (the)
    consumeLine(s, "-");     // it (it)
  }

  private static void consumeLine(Stack<String> stack, String line) {
    if (!line.equals("-")) stack.push(line);
    else if (!stack.isEmpty()) StdOut.println(stack.pop() + " ");
  }

  private static class Stack<Item> {
    private Node first;
    private int N;

    private class Node {
      Item item;
      Node next;
    }

    public boolean isEmpty() { return first == null; }
    public int size() { return N; }
    public void push(Item item) {
      Node oldfirst = first;
      first = new Node();
      first.item = item;
      first.next = oldfirst;
      N++;
    }

    public Item pop() {
      Item item = first.item;
      first = first.next;
      N--;
      return item;
    }
  }
}
