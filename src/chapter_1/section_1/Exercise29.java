package algsex.chapter1.section1;

import java.util.*;
import edu.princeton.cs.algs4.*;

// 1.1.29 Equal keys. Add to BinarySearch a static method rank() that takes a key and
// a sorted array of int values (some of which may be equal) as arguments and returns the
// number of elements that are smaller than the key and a similar method count() that
// returns the number of elements equal to the key. Note : If i and j are the values returned
// by rank(key, a) and count(key, a) respectively, then a[i..i+j-1] are the values in
// the array that are equal to key.
public class Exercise29 {
  public static void main(String[] args) {
    int key = Integer.parseInt(args[0]);
    int[] a = new int[args.length - 1];

    for (int i = 0; i < a.length; i++) {
      a[i] = Integer.parseInt(args[i+1]);
    }

    Arrays.sort(a);

    StdOut.println(Arrays.toString(a));
    int ranked = rank(key, a);
    StdOut.printf("rank(key, a): %s\n", ranked);
    StdOut.printf("count(key, a): %s\n", count(ranked, key, a));
  }

  // Add to BinarySearch a static method rank() that takes a key and
  // a sorted array of int values (some of which may be equal) as arguments and returns the
  // number of elements that are smaller than the key ...
  private static int rank(int key, int[] a) {
    if (key > a[a.length - 1]) return a.length;

    int lo = 0;
    int hi = a.length - 1;
    int match = -1;

    while (lo <= hi) {
      int mid = lo + (hi - lo) / 2;
      if (key < a[mid]) hi = mid - 1;
      else if (key > a[mid]) lo = mid + 1;
      else {
        match = mid;
        break;
      }
    }

    if (match == -1) return -1;

    for (int i = match - 1; i >= 0 && a[i] == key; i--) match--;

    return match;
  }

  // ... and a similar method count() that returns the number of elements equal to the key.
  // Note : If i and j are the values returned by rank(key, a) and count(key, a) respectively,
  // then a[i..i+j-1] are the values in the array that are equal to key.
  private static int count(int ranked, int key, int[] a) {
    if (ranked == -1) return 0;

    int result = 1;

    for (int i = ranked + 1; i < a.length && a[i] == key; i++) result++;

    return result;
  }
}