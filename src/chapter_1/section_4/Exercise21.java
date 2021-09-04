package algsex.chapter1.section4;

import java.util.*;
import edu.princeton.cs.algs4.*;
import algsex.support.Test;

// 1.4.21 Binary search on distinct values. Develop an implementation of binary search
// for StaticSETofInts (see page 99) where the running time of contains() is guar-
// anteed to be ~ lg R, where R is the number of different integers in the array given as
// argument to the constructor.
public class Exercise21 {
    public static void main(String[] args) {
        StaticSETofInts s = new StaticSETofInts(new int[] { 1, 2, 2, 3 });

        Test.assertTrue(s.contains(1));
        Test.assertTrue(s.contains(2));
        Test.assertTrue(s.contains(3));
        Test.assertFalse(s.contains(4));

        StdOut.println("Tests passed");
    }

    private static class StaticSETofInts {
        private int[] a;
  
        public StaticSETofInts(int[] keys) {
            int uniqueKeyCount = 0;

            for (int i = 0; i < keys.length; i++)
                if (i == 0 || keys[i] != keys[i-1])
                   uniqueKeyCount++;

            a = new int[uniqueKeyCount];
            int index = 0;

            for (int i = 0; i < keys.length; i++)
                if (i == 0 || keys[i] != keys[i-1]) {
                    a[index] = keys[i];
                    index++;
                }

            Arrays.sort(a);
        }
  
        public boolean contains(int key) {
            return rank(key) != -1;
        }

        private int rank(int key) {
            return rank(key, 0, a.length-1);
        }

        private int rank(int key, int lo, int hi) {
            if (lo > hi) return -1;

            int mid = (lo + hi) / 2;

            if (a[mid] > key) return rank(key, lo, mid-1);
            else if (a[mid] < key) return rank(key, mid+1, hi);
            else return mid;
        }
    }
}
