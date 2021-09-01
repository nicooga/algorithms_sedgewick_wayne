package algsex.chapter1.section4;

import java.util.*;
import edu.princeton.cs.algs4.*;
import algsex.support.Test;

// 1.4.18 Local minimum of an array. Write a program that, given an array a[] of N
// distinct integers, finds a local minimum: an entry a[i] that is strictly less than its neigh-
// bors. Each internal entry (other than a[0] and a[N-1]) has 2 neighbors. Your program
// should use ~2 lg N compares in the worst case.
public class Exercise18 {
    public static void main(String[] args) {
        Test.assertEqual(LocalMinimum.find(new int[] { 1 }), 0);
        Test.assertEqual(LocalMinimum.find(new int[] { 1, 2 }), 0);
        Test.assertEqual(LocalMinimum.find(new int[] { 2, 1 }), 1);
        Test.assertEqual(LocalMinimum.find(new int[] { 10, 9, 11 }), 1);
        Test.assertEqual(LocalMinimum.find(new int[] { 9, 10, 11 }), 0);
        Test.assertEqual(LocalMinimum.find(new int[] { 11, 10, 9 }), 2);
        Test.assertEqual(LocalMinimum.find(new int[] { 9, 10, 11, 12 }), 0);
        Test.assertEqual(LocalMinimum.find(new int[] { 12, 11, 10, 9 }), 3);
        Test.assertEqual(LocalMinimum.find(new int[] { 0, 1, 2, 3 }), 0);
        Test.assertEqual(LocalMinimum.find(new int[] { 4, 3, 2, 1, 99 }), 3);

        StdOut.println("Tests passed");
    }

    private static class LocalMinimum {
        public static int find(int a[]) {
            if (a.length == 1) return 0;
            if (a.length == 2)
              if (a[0] > a[1]) return 1;
              else return 0;

            return find(a, 0, a.length-1);
        }

        public static int find(int[] a, int lo, int hi) {
            int mid = (lo + hi) / 2;

            if (lo > hi) return -1;

            if (mid == a.length-1 && a[mid] < a[mid-1]) return mid;

            if (a[mid] < a[mid+1])
              if (mid == 0 || a[mid] < a[mid-1]) return mid;
              else return find(a, lo, mid-1);
            else
              return find(a, mid+1, hi);
        }
    }
}
