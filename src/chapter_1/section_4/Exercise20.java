package algsex.chapter1.section4;

import java.util.*;
import edu.princeton.cs.algs4.*;
import algsex.support.Test;

// 1.4.20 Bitonic search. An array is bitonic if it is comprised of an increasing sequence
// of integers followed immediately by a decreasing sequence of integers. Write a program
// that, given a bitonic array of N distinct int values, determines whether a given integer is
// in the array. Your program should use ~3 lg N compares in the worst case. Extra credit:
// use only ~2 lg N compares in the worst case.
public class Exercise20 {
    public static void main(String[] args) {
        int[] arrayA = new int[] { 1, 2, 3, 4, 8, 9, 12, 15, 18, 13, 11, 10, 7, 6, 5, 0 };
        int[] arrayB = new int[] { 1, 2, 3, 4, 8, 9, 12, 18, 15, 13, 11, 10, 7, 6, 5, 0 };
        int[] arrayC = new int[] { 1, 2, 3, 4, 35, 30, 27, 23, 18, 15, 13, 11, 10, 9, 8, 6, 5, 0 };
        int[] arrayD = new int[] { 1, 2, 3, 4, 8, 9, 12, 18, 22, 33, 55, 32, 6, 5, 0 };

        Test.assertTrue(BitonicSearch.contains(arrayA, 4));
        Test.assertTrue(BitonicSearch.contains(arrayA, 18));
        Test.assertTrue(BitonicSearch.contains(arrayA, 5));
        Test.assertFalse(BitonicSearch.contains(arrayA, -2));
        Test.assertFalse(BitonicSearch.contains(arrayA, 23));
        Test.assertFalse(BitonicSearch.contains(arrayA, 100));

        Test.assertTrue(BitonicSearch.contains(arrayB, 4));
        Test.assertTrue(BitonicSearch.contains(arrayB, 18));
        Test.assertTrue(BitonicSearch.contains(arrayB, 5));
        Test.assertFalse(BitonicSearch.contains(arrayB, -2));
        Test.assertFalse(BitonicSearch.contains(arrayB, 23));
        Test.assertFalse(BitonicSearch.contains(arrayB, 100));

        Test.assertTrue(BitonicSearch.contains(arrayC, 1));
        Test.assertTrue(BitonicSearch.contains(arrayC, 35));
        Test.assertTrue(BitonicSearch.contains(arrayC, 3));
        Test.assertFalse(BitonicSearch.contains(arrayC, -2));
        Test.assertFalse(BitonicSearch.contains(arrayC, 99));
        Test.assertFalse(BitonicSearch.contains(arrayC, 14));

        Test.assertTrue(BitonicSearch.contains(arrayD, 2));
        Test.assertTrue(BitonicSearch.contains(arrayD, 55));
        Test.assertTrue(BitonicSearch.contains(arrayD, 5));
        Test.assertFalse(BitonicSearch.contains(arrayD, -2));
        Test.assertFalse(BitonicSearch.contains(arrayD, 99));
        Test.assertFalse(BitonicSearch.contains(arrayD, 14));

        StdOut.println("Tests passed");
    }

    private static class BitonicSearch {
        public static boolean contains(int[] a, int key) {
            int midPoint = rankBitonicMidPoint(a);

            return (
                rank(key, a, 0, midPoint) != -1 ||
                inverseRank(key, a, midPoint, a.length-1) != -1
            );
        }

        private static int rank(int key, int[] a, int lo, int hi) {
            if (lo > hi) return -1;

            int mid = (lo + hi) / 2;

            if (a[mid] > key) return rank(key, a, lo, mid-1);
            else if (a[mid] < key) return rank(key, a, mid+1, hi);
            else return mid;
        }

        private static int inverseRank(int key, int[] a, int lo, int hi) {
            if (lo > hi) return -1;

            int mid = (lo + hi) / 2;

            if (a[mid] < key) return inverseRank(key, a, lo, mid-1);
            else if (a[mid] > key) return inverseRank(key, a, mid+1, hi);
            else return mid;
        }

        private static int rankBitonicMidPoint(int[] a) {
            return rankBitonicMidPoint(a, 0, a.length-1);
        }

        private static int rankBitonicMidPoint(int[] a, int lo, int hi) {
            if (lo == hi) return lo;

            int mid = (lo + hi) / 2;

            if (a[mid] < a[mid+1]) {
                assert a[mid] > a[lo] || mid == lo;
                return rankBitonicMidPoint(a, mid+1, hi);
            } else {
                assert a[mid] > a[hi];
                return rankBitonicMidPoint(a, lo, mid);
            }
        }
    }
}
