package algsex.chapter1.section4;

import java.util.*;
import edu.princeton.cs.algs4.*;
import algsex.support.Test;

// 1.4.16 Closest pair (in one dimension). Write a program that, given an array a[] of N
// double values, finds a closest pair : two values whose difference is no greater than the
// the difference of any other pair (in absolute value). The running time of your program
// should be linearithmic in the worst case.
public class Exercise16 {
    public static void main(String[] args) {
        Test.assertArrayEquals(
            ClosestPair.find(new int[] { 0, 0, 10, 10 }),
            new int[] { 0, 0 }
        );

        Test.assertArrayEquals(
            ClosestPair.find(new int[] { 100, 109, -2, -8, -99, 10 }),
            new int[] { -8, -2 }
        );

        StdOut.println("Tests passed");
    }

    private static class ClosestPair {
        public static int[] find(int[] a) {
            int minDiff = -1;
            int minIndex = -1;

            Arrays.sort(a);

            for (int i = 0; i < a.length-1; i++) {
                int diff = Math.abs(a[i] - a[i+1]);
                if (minDiff == -1 || diff < minDiff) {
                    minDiff = diff;
                    minIndex = i;
                }
            }

            return new int[] { a[minIndex], a[minIndex+1] };
        }
    }
}
