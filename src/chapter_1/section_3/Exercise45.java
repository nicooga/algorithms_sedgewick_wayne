package algsex.chapter1.section3;

import java.util.*;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

// 1.3.45 Stack generability.
//
// Suppose that we have a sequence of intermixed push and pop operations as with
// our test stack client, where the integers 0, 1, ..., N-1 in that order (push directives)
// are intermixed with N minus signs (pop directives).
//
// Devise an algorithm that determines whether the intermixed sequence causes the stack to under-flow.
// (You may use only an amount of space independent of N—you cannot store the integers in a data structure.)
// Devise a linear-time algorithm that determines whether a given permutation can be generated as output by
// our test client (depending on where the pop directives occur).
public class Exercise45 {
  public static void main(String[] args) {
    assert causesUnderflow("01---23456789");
    assert causesUnderflow("012-3---4--56789");
    assert !causesUnderflow("01--234---56--789---");
    assert !causesUnderflow("012-345-6789");

    assert isValidOutput("0123456789");
    assert isValidOutput("9876543210");
    assert isValidOutput("3698754210");
    assert !isValidOutput("9867543210");
    assert !isValidOutput("4562987310");
    assert !isValidOutput("3597864210");

    StdOut.println("Tests passed");
  }

  private static boolean causesUnderflow(String sequence) {
    int stackSize = 0;

    for (int i = 0; i < sequence.length(); i++) {
      char c = sequence.charAt(i);

      if (c == '-') stackSize--;
      else if (Character.isDigit(c)) stackSize++;
      else assert false;

      if (stackSize < 0) return true;
    }

    return false;
  }

  private static boolean isValidOutput(String sequence) {
    Stack<Integer> stack = new Stack();
    int nextIntToPush = 0;

    for (int i = 0; i < sequence.length(); i++) {
      int n = Integer.parseInt(Character.toString(sequence.charAt(i)));

      if (stack.isEmpty() || stack.peek() < n) {
        for (int j = nextIntToPush; j <= n; j++) stack.push(j);
        nextIntToPush = n + 1;
        int poppedValue = stack.pop();
        assert poppedValue == n;
      } else if (stack.peek() == n) {
        int poppedValue = stack.pop();
        assert poppedValue == n;
      } else
        return false;
    }

    return true;
  }
}
