package algsex.misc;

import algsex.support.Test;

public class BinarySearchFrequencyTest {
    public static void main(String[] _args) {
        assert InstrumentedBinarySearch.rank(1, new int[] { 0, 0, 0, 1, 0, 0, 0 }) == 3;

        for (int p = 0; p < 10; p++) {
            int N = (int) Math.pow(2, p);
            InstrumentedBinarySearch.rank(1, new int[N]);

            Test.assertEqual(
                InstrumentedBinarySearch.loopCount,
                (int) (lg(N) + 2)
            );
        }

        System.out.println("Tests passed");
    }

    private static double lg(double value) {
        return Math.log(value) / Math.log(2);
    }

    private static class InstrumentedBinarySearch {
        public static int loopCount;

        private static int rank(int key, int[] a) {
            loopCount = 0;
            return rank(key, a, 0, a.length - 1);
        }
  
        private static int rank(int key, int[] a, int lo, int hi) {
            loopCount++;
            if (lo > hi) return -1;
  
            int mid = (lo + hi) / 2;
  
            if (a[mid] > key) return rank(key, a, lo, mid-1);
            else if (a[mid] < key) return rank(key, a, mid+1, hi);
            else return mid;
        }
    }
}