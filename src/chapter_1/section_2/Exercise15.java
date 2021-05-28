package algsex.chapter1.section2;

import java.util.*;
import edu.princeton.cs.algs4.*;

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
