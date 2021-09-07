package algsex.chapter1.section4;

import java.util.*;
import edu.princeton.cs.algs4.*;
import algsex.support.Test;

// 1.4.22 Binary search with only addition and subtraction. [Mihai Patrascu] Write a
// program that, given an array of N distinct int values in ascending order, determines
// whether a given integer is in the array. You may use only additions and subtractions
// and a constant amount of extra memory. The running time of your program should be
// proportional to log N in the worst case.
public class Exercise22 {
    public static void main(String[] args) {
        Test.assertEqual(
            BinarySearch.rank(1, new int[] { 1, 2, 3, 4, 5, 6, 7, 8 }),
            0
        );

        Test.assertEqual(
            BinarySearch.rank(8, new int[] { 1, 2, 3, 4, 5, 6, 7, 8 }),
            7
        );

        Test.assertEqual(
            BinarySearch.rank(5, new int[] { 1, 2, 3, 4, 5, 6, 7, 8 }),
            4
        );

        Test.assertEqual(
            BinarySearch.rank(6, new int[] { 1, 2, 3, 4, 5, 6, 7, 8 }),
            5
        );

        StdOut.println("Tests passed");
    }

    private static class BinarySearch {
        public static int rank(int key, int[] a) {
            return rank(key, a, 0, a.length-1);
        }

        private static int rank(int key, int[] a, int lo, int hi) {
            if (lo > hi) return -1;

            int mid = (lo + hi) >>> 1;

            if (a[mid] > key) return rank(key, a, lo, mid-1);
            else if (a[mid] < key) return rank(key, a, mid+1, hi);
            else return mid;
        }
    }
}
