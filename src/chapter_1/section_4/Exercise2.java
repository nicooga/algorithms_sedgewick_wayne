package algsex.chapter1.section4;

import java.util.*;
import edu.princeton.cs.algs4.*;
import java.util.Arrays;

// 1.4.2 Modify ThreeSum to work properly even when the int values are so large that
// adding two of them might cause overflow.
public class Exercise2 {
  public static void main(String[] args) {
    assert ThreeSum.count(new int[] { 1, 2, -3 }) == 1;
    assert ThreeSum.count(new int[] { 1, 2, -3, 4, -6 }) == 2;
    assert ThreeSum.count(new int[] { 1, 2, 3 }) == 0;

    assert ThreeSum.count(new int[] {
      Integer.MAX_VALUE, // 2^32 - 1
      -1,                // 2^32 - 1 - 1 = 2^32 - 2
      Integer.MIN_VALUE  // 2^32 - 2 - 2^32 = -2
    }) == 0;

    assert ThreeSum.count(new int[] {
      Integer.MIN_VALUE,
      1,
      Integer.MAX_VALUE
    }) == 0;

    StdOut.println("Tests passed");
  }

  private static class ThreeSum {
    public static int count(int[] a) { // Count triples that sum to 0.
      int N = a.length;
      int cnt = 0;

      Arrays.sort(a);

      for (int i = 0; i < N; i++) {
        int n1 = a[i];


        for (int j = i+1; j < N; j++) {
          int n2 = a[j];

          if (n1 > 0 && n2 > 0 && n1 > Integer.MAX_VALUE - n2) continue;
          if (n1 < 0 && n2 < 0 && n1 < Integer.MIN_VALUE - n2) continue;

          int n3 = -(n1 + n2);

          if (BinarySearch.indexOf(a, n3) > j) cnt++;
        }
      }

      return cnt;
    }
  }
}
