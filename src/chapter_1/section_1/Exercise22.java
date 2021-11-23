package algsex.chapter1.section1;

import java.util.*;
import edu.princeton.cs.algs4.*;

// 1.1.22 Write a version of BinarySearch that uses the recursive rank() given on page
// 25 and traces the method calls. Each time the recursive method is called, print the argument
// values lo and hi, indented by the depth of the recursion. Hint: Add an argument
// to the recursive method that keeps track of the depth.
public class Exercise22 {
  public static void main(String[] args) {
    int[] a = { 1, 2, 3, 4, 5, 6, 7, 8 ,9 , 10 ,11, 12, 13, 14, 15, 16, 17, 18, 19 ,20 };
    int key = 0;
    StdOut.printf("rank(%s, %d) returns: %d\n", Arrays.toString(a), key, rank(key, a));
  }

  private static int rank(int key, int[] a) {
    return rank(key, a, 0, a.length - 1, 0);
  }

  private static int rank(int key, int[] a, int lo, int hi, int recursionLevel) {
    trace(lo, hi, recursionLevel);

    // Index of key in a[], if present, is not smaller than lo
    // and not larger than hi.
    if (lo > hi) return -1;
    int mid = lo + (hi - lo) / 2;
    if (key < a[mid]) return rank(key, a, lo, mid - 1, recursionLevel + 1);
    else if (key > a[mid]) return rank(key, a, mid + 1, hi, recursionLevel + 1);
    else return mid;
  }

  private static void trace(int lo, int hi, int recursionLevel) {
    if (recursionLevel > 0)
      for (int i = 1; i <= recursionLevel; i++)
        StdOut.print("  ");

    StdOut.printf("lo: %s, hi: %s\n", lo, hi);
  }
}