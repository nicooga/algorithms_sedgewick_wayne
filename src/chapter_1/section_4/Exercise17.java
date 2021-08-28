package algsex.chapter1.section4;

import java.util.*;
import edu.princeton.cs.algs4.*;
import algsex.support.Test;

// 1.4.17 Farthest pair (in one dimension). Write a program that, given an array a[] of N
// double values, finds a farthest pair : two values whose difference is no smaller than the
// the difference of any other pair (in absolute value). The running time of your program
// should be linear in the worst case.
public class Exercise17 {
    public static void main(String[] args) {
        Test.assertArrayEquals(
            FarthestPair.find(new int[] { -1000, 1000, 0, 1, 2, 1001, 1002 }),
            new int[] { -1000, 1002 }
        );

        Test.assertArrayEquals(
            FarthestPair.find(new int[] { -2, 0, 0, 1, 2, 1000, 1000 }),
            new int[] { -2, 1000 }
        );

        StdOut.println("Tests passed");
    }

    private static class FarthestPair {
        public static int[] find(int[] a) {
            Arrays.sort(a);
            return new int[] { a[0], a[a.length-1] };
        }
    }
}
