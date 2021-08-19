package algsex.chapter1.section4;

import java.util.*;
import edu.princeton.cs.algs4.*;

// 1.4.10 Modify binary search so that it always returns the element with the smallest
// index that matches the search element (and still guarantees logarithmic running time).
public class Exercise10 {
  public static void main(String[] args) {
    runTest(1, new int[] { 5, 3, 4, 1, 2 }, 0);
    runTest(5, new int[] { 5, 2, 4, 1, 2 }, 4);
    runTest(1, new int[] { 5, 2, 4, 9, 1, 1, 1, 2, 2, 2 }, 0);
    runTest(2, new int[] { 5, 2, 4, 9, 1, 1, 1, 2, 2, 2 }, 3);

    StdOut.println("Tests passed");
  }

  private static void runTest(int key, int[] array, int expectedReturnValue) {
    Arrays.sort(array);

    int actualReturnValue = BinarySearch.rank(key, array);

    assert actualReturnValue == expectedReturnValue :
      String.format(
        "Expected BinarySearch.rank(%s, %s) to return %s, but instead was %s",
        key,
        Arrays.toString(array),
        expectedReturnValue,
        actualReturnValue
      );

    StdOut.println("> Check");
  }

  private static class BinarySearch {
    public static int rank(int key, int[] a)  {
      int lo = 0;
      int hi = a.length - 1;

      while (lo <= hi) {
        int mid = (lo + hi) / 2;

        if (a[lo] == key) return lo;
        else if (a[mid] < key) lo = mid+1;
        else if (a[mid] > key) hi = mid-1;
        else {
          while (a[mid-1] == key) mid--;
          return mid;
        }
      }

      return -1;
    }
  }
}
