package algsex.chapter1.section2;

import java.util.*;
import edu.princeton.cs.algs4.*;

// 1.2.15 File input. Develop a possible implementation of the static readInts() meth-
// od from In (which we use for various test clients, such as binary search on page 47) that
// is based on the split() method in String.
public class Exercise15 {
  public static void main(String[] args) {
    StdOut.println(Arrays.toString(readInts()));
  }

  private static int[] readInts() {
    In in = new In();
    String rawInput  = StdIn.readAll();
    String[] splittedInput = rawInput.split("\\s+");
    int[] ints = new int[splittedInput.length];
    for (int i = 0; i < splittedInput.length; i++)
      ints[i] = Integer.parseInt(splittedInput[i]);
    return ints;
  }
}
