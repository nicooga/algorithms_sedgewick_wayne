package algsex.chapter1.section3;

import java.util.*;
import edu.princeton.cs.algs4.*;

// I wish I could express the problem in purely mathematical terms.
// Instead I came up with an implementation of a sequence validation algorithm.
// Even so, I'm not 100% sure that my algorithm is correct.
public class Exercise3 {
  public static void main(String[] args) {
    OptionalLogger logger = new OptionalLogger(true);
    SequenceValidator1 validator1 = new SequenceValidator1(logger);

    assert validator1.isValid(new int[] { 4, 3, 2, 1, 0, 9, 8, 7, 6, 5 });
    assert !validator1.isValid(new int[] { 4, 6, 8, 7, 5, 3, 2, 9, 0, 1 });
    assert validator1.isValid(new int[] { 2, 5, 6, 7, 4, 8, 9, 3, 1, 0 });
    assert validator1.isValid(new int[] { 4, 3, 2, 1, 0, 5, 6, 7, 8, 9 });
    assert validator1.isValid(new int[] { 1, 2, 3, 4, 5, 6, 9, 8, 7, 0 });
    assert !validator1.isValid(new int[] { 0, 4, 6, 5, 3, 8, 1, 7, 2, 9 });
    assert !validator1.isValid(new int[] { 1, 4, 7, 9, 8, 6, 5, 3, 0, 2 });
    assert validator1.isValid(new int[] { 2, 1, 4, 3, 6, 5, 8, 7, 9, 0 });

    StdOut.println("Tests passed");
  }

  private static class OptionalLogger {
    private boolean enabled;

    public OptionalLogger(boolean enabled) {
      this.enabled = enabled;
    }

    public void print(String str) {
      if (!enabled) return;
      StdOut.print(str);
    }

    public void println(String str) {
      if (!enabled) return;
      StdOut.println(str);
    }

    public void printf(String str, Object... params) {
      if (!enabled) return;
      StdOut.printf(str, params);
    }
  }

  private static class SequenceValidator1 {
    private OptionalLogger logger;

    public SequenceValidator1(OptionalLogger logger) {
      this.logger = logger;
    }

    private boolean isValid(int[] seq) {
      int N = 0;
      Stack stack = new Stack(logger);

      logger.println("================================================");
      logger.println("Analyzing sequence " + Arrays.toString(seq));

      for(int x : seq) {
        logger.println("===");
        logger.println("Analyzing sequence element " + x);

        if (stack.size() > 0 && x < stack.top()) {
          fail(seq, String.format("Sequence element %s is less than stack top", x));
          return false;
        } else if (stack.size() == 0 || x > stack.top() && N <= x) {
          while(N <= x) {
            stack.push(N);
            N++;
          }
        }

        assert stack.pop() == x;

        logger.println("===");
      }

      logger.printf("Sequence %s is a valid sequence\n", Arrays.toString(seq));
      logger.println("================================================");

      return true;
    }

    private void fail(int[] seq, String message) {
      logger.printf(
        "%s. Sequence %s is an invalid sequence\n",
        message,
        Arrays.toString(seq)
      );

      logger.println("================================================");
    }
  }

  private static class Stack {
    private Node first;
    private int N;
    private OptionalLogger logger;

    public Stack(OptionalLogger logger) {
      this.logger = logger;
    }

    private class Node {
      int item;
      Node next;
    }

    public void push(int item) {
      Node oldFirst = first;

      first = new Node();
      first.item = item;
      first.next = oldFirst;

      N++;

      debug("Pushed " + item);
    }

    public int pop() {
      int item = first.item;
      first = first.next;
      N--;

      debug("Popped " + item);

      return item;
    }

    public int size() { return N; }

    public int top() {
      return first.item;
    }

    private void debug(String message) {
      Node node = first;

      logger.print(message + ". Stack contents are:");

      while(node != null) {
        logger.print(" " + node.item);

        if (node.next != null)
          assert node.item > node.next.item;

        node = node.next;
      }

      logger.println("");
    }
  }
}