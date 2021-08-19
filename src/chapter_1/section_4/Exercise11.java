package algsex.chapter1.section4;

import java.util.*;
import edu.princeton.cs.algs4.*;
import algsex.support.Test;

// 1.4.11 Add an instance method howMany() to StaticSETofInts (page 99) that finds the
// number of occurrences of a given key in time proportional to log N in the worst case.
public class Exercise11 {
  public static void main(String[] args) {
    StaticSETofInts set = new StaticSETofInts(new int[] { 8, 1, 5, 2, 3, 4, 5, 5, 5, 2 });

    Test.assertTrue(set.contains(8));
    Test.assertTrue(set.contains(5));
    Test.assertFalse(set.contains(100));
    Test.assertEqual(set.howMany(5), 4);

    StdOut.println("Tests passed");
  }

  private static class StaticSETofInts {
    private int[] a;

    public StaticSETofInts(int[] keys) {
      a = new int[keys.length];
      for (int i = 0; i < keys.length; i++)
      a[i] = keys[i]; // defensive copy
      Arrays.sort(a);
    }

    public boolean contains(int key) {
      return new BinarySearch().rank(key, a) != -1;
    }

    public int howMany(int key) {
      return new BinaryCount().count(key, a);
    }

    private abstract class AbstractBinarySearch {
      public int rank(int key, int[] a) {
        return rank(key, a, 0, a.length - 1);
      }

      public int rank(int key, int[] a, int lo, int hi) {
        if (lo > hi) return -1;

        int mid = lo + (hi - lo) / 2;

        if (a[mid] > key) return rank(key, a, lo, mid - 1);
        else if (a[mid] < key) return rank(key, a, mid + 1, hi);
        else return onMatch(key, a, lo, hi, mid);
      }

      abstract protected int onMatch(int key, int[] a, int lo, int hi, int mid);
    }

    private class BinarySearch extends AbstractBinarySearch {
      protected int onMatch(int _key, int[] _a, int _lo, int _hi, int mid) {
        return mid;
      }
    }

    private class LeftBinarySearch extends AbstractBinarySearch {
      protected int onMatch(int key, int[] a, int lo, int hi, int mid) {
        int potentialLowerDup = rank(key, a, lo, mid-1);
        if (potentialLowerDup != -1) return potentialLowerDup;
        return mid;
      }
    }

    private class RightBinarySearch extends AbstractBinarySearch {
      protected int onMatch(int key, int[] a, int lo, int hi, int mid) {
        int potentialHigherDup = rank(key, a, mid+1, hi);
        if (potentialHigherDup != -1) return potentialHigherDup;
        return mid;
      }
    }

    private class BinaryCount extends AbstractBinarySearch {
      public int count(int key, int[] a) {
        return rank(key, a);
      }

      protected int onMatch(int key, int[] a, int lo, int hi, int mid) {
        int min = new LeftBinarySearch().rank(key, a, lo, mid - 1);
        int max = new RightBinarySearch().rank(key, a, mid + 1, hi);

        if (min == -1) min = mid;
        if (max == -1) max = mid;

        return max - min + 1;
      }
    }
  }
}
