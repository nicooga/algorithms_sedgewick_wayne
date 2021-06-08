package algsex.chapter1.section3;

import java.util.*;
import edu.princeton.cs.algs4.*;

public class Exercise1 {
  public static void main(String[] args) {
    FixedCapacityStackOfStrings stack = new FixedCapacityStackOfStrings(10);

    while(!StdIn.isEmpty()) {
      String line = StdIn.readLine().trim();

      if (line.equals("-"))
        StdOut.println(stack.pop());
      else if (stack.isFull())
        StdOut.println("stack is full");
      else
       stack.push(line);
    }
  }

  private static class FixedCapacityStackOfStrings {
    private String[] a; // stack entries
    private int N; // size
    private int cap;

    public FixedCapacityStackOfStrings(int cap) {
      this.cap = cap;
      this.a = new String[cap];
     }

    public boolean isEmpty() { return N == 0; }
    public int size() { return N; }
    public void push(String item) { a[N++] = item; }
    public String pop() { return a[--N]; }
    public boolean isFull() { return N >= cap - 1; }
  }
}
