package algsex.chapter1.section1;

import java.util.*;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Exercise15 {
  public static void main(String[] _args) {
    int[] a1 = { 1, 2, 3, 4, 5, 1, 1, 3, 5 ,6 };
    int M1 = 8;
    runCase(a1, M1);

    int[] a2 = { 0, 0, 0, 0, 0, 0, 0, 0, 1 ,2 };
    int M2 = 3;
    runCase(a2, M2);
  }

  private

  static void runCase(int[] a, int M) {
    StdOut.println("");
    StdOut.printf("Running case a = %s, and M = %s\n", Arrays.toString(a), M);

    int[] result = histogram(a, M);

    StdOut.printf("Result is: %s\n", Arrays.toString(result));
    StdOut.println("");

    // If the values in a[] are all between 0 and M–1, the sum of the values in the returned array should be equal to a.length.
    if (areAllWithinBounds(a, 0, M-1)) {
      StdOut.println("All numbers are within 0 and M-1");
      assert sum(result) == a.length;
    }
  }

  // 1.1.15 Write a static method histogram() that takes an array a[] of int values and
  // an integer M as arguments and returns an array of length M whose ith entry is the number
  // of times the integer i appeared in the argument array.
  static int[] histogram(int[] a, int M) {
    int[] result = new int[M];

    for (int i = 0; i < a.length; i++) {
      int value = a[i];
      if (value < M) result[value] += 1;
    }

    return result;
  }

  static int sum(int[] array) {
    int result = 0;
    for (int i = 0; i < array.length; i++) result += array[i];
    return result;
  }

  static boolean areAllWithinBounds(int[] a, int lowerBound, int upperBound) {
    for (int i = 0; i < a.length; i++)
      if (!isWithinBounds(a[i], lowerBound, upperBound))
        return false;

    return true;
  }

  static boolean isWithinBounds(int n, int lowerBound, int upperBound) {
    return n >= lowerBound && n <= upperBound;
  }
}