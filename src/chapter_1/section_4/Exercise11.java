package algsex.chapter1.section4;

import java.util.*;
import edu.princeton.cs.algs4.*;

// 1.4.11 Add an instance method howMany() to StaticSETofInts (page 99) that finds the
// number of occurrences of a given key in time proportional to log N in the worst case.
public class Exercise11 {
  public static void main(String[] args) {
  }

  public class StaticSETofInts {
    private int[] a;

    public StaticSETofInts(int[] keys) {
      a = new int[keys.length];
      for (int i = 0; i < keys.length; i++)
      a[i] = keys[i]; // defensive copy
      Arrays.sort(a);
    }

    public boolean contains(int key) {
      return BinarySearch.rank(a, key) != -1;
    }

    public boolean howMany(int key) {
      BinarySearch.rank(key, a);
    }

    private abstract class BinarySearch {
      private int rank(int key, int[] a) {
        return rank(key, a, 0, a.length - 1);
      }

      private int rank(int key, int[] a, int lo, int hi) {
        if (lo > hi) return -1;

        while (lo <= hi) {
          int mid = lo + (hi - lo) / 2;
          if (key < a[mid]) return rank(key, lo, mid - 1);
          else if (key > a[mid]) return rank(key, mid + 1, hi);
          else return mid;
        }
      }
    }
  }
}
