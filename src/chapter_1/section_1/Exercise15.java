// 1.1.15 Write a static method histogram() that takes an array a[] of int values and
// an integer M as arguments and returns an array of length M whose ith entry is the number
// of times the integer i appeared in the argument array. If the values in a[] are all
// between 0 and M–1, the sum of the values in the returned array should be equal to
// a.length.
package algsex.chapter1.section1;

import java.util.*;
import edu.princeton.cs.algs4.StdOut;

public class Exercise15 {
  public static void main(String[] _args) {
    int[] a = { 1, 2, 3, 4, 5, 1, 1, 3, 5 ,7 };
    int M = 8;

    int[] result = histogram(a, M);

    StdOut.println(Arrays.toString(result));

    assert sum(result) == a.length;
  }

  private

  static int[] histogram(int[] a, int M) {
    int[] result = new int[M];
    return result;
  }

  static int sum(int[] array) {
    int result = 0;
    for (int i = 0; i < array.length; i++) result += array[i];
    return result;
  }
}