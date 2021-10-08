package algsex.chapter1.section4;

import java.util.*;
import edu.princeton.cs.algs4.*;
import algsex.chapter1.section4.Exercise38;
import algsex.support.Test;
import algsex.support.TestDataGenerator;
import algsex.support.DoublingRatioTest;

// 1.4.41 Running times. Estimate the amount of time it would take to run TwoSumFast,
// TwoSum, ThreeSumFast and ThreeSum on your computer to solve the problems for a file
// of 1 million numbers. Use DoublingRatio to do so.
public class Exercise41 {
    private static final int MAX_N = (int) Math.pow(2, 19);
    private static final int RUNS_PER_N = 10;
    private static final TestDataGenerator testDataGenerator = new TestDataGenerator(-MAX_N/2, MAX_N/2);

    public static void main(String[] args) {
        runBasicTests();
        runDoublingRatioTests();
    }

    private static void runBasicTests() {
        int[] a = new int[] { -1, 0, 1, 2, -2 };

        Test.assertEqual(TwoSum.count(a), 2);
        Test.assertEqual(TwoSumFast.count(a), 2);
        Test.assertEqual(ThreeSum.count(a), 2);
        Test.assertEqual(ThreeSumFast.count(a), 2);

        StdOut.println("Tests passed");
    }

    private static void runDoublingRatioTests() {
        testDataGenerator.prime(1, MAX_N);

        new TwoSumTest().run();
        // new TwoSumFastTest().run();
        // new ThreeSumTest().run();
        // new ThreeSumFastTest().run();
    }

    private static class TwoSumTest extends BaseDoublingRatioTest {
        @Override
        protected String label() { return "TwoSum"; }
        @Override
        protected void doRunExperiment(int[] a) { TwoSum.count(a); }
    }

    private static class TwoSumFastTest extends BaseDoublingRatioTest {
        @Override
        protected String label() { return "TwoSumFast"; }
        @Override
        protected void doRunExperiment(int[] a) { TwoSumFast.count(a); }
    }

    private static class ThreeSumTest extends BaseDoublingRatioTest {
        @Override
        protected String label() { return "ThreeSum"; }
        @Override
        protected void doRunExperiment(int[] a) { ThreeSum.count(a); }
    }

    private static class ThreeSumFastTest extends BaseDoublingRatioTest {
        @Override
        protected String label() { return "ThreeSumFast"; }
        @Override
        protected void doRunExperiment(int[] a) { ThreeSumFast.count(a); }
    }

    private static abstract class BaseDoublingRatioTest extends DoublingRatioTest {
        abstract protected void doRunExperiment(int[] a);

        @Override
        protected void runExperiment(int N) {
            int[] a = testDataGenerator.getOrCreateTestData(N);
            doRunExperiment(a);
        }

        @Override
        protected int defaultRunsPerN() { return RUNS_PER_N; };

        @Override
        protected boolean iterationCondition(int N) { return N <= MAX_N; }
    }

    private static class TwoSum {
        public static int count(int[] a) {
            int N = a.length;
            int cnt = 0;

            for (int i = 0; i < N; i++)
                for (int j = i+1; j < N; j++)
                    if (a[i] + a[j] == 0)
                        cnt++;

            return cnt;
        }
    }

    private static class TwoSumFast {
        public static int count(int[] a) {
            Arrays.sort(a);

            int N = a.length;
            int cnt = 0;

            for (int i = 0; i < N; i++)
                if (BinarySearch.rank(-a[i], a) > i)
                    cnt++;

            return cnt;
        }
    }

    private static class ThreeSum {
        public static int count(int[] a) {
            int N = a.length;
            int cnt = 0;

            for (int i = 0; i < N; i++)
                for (int j = i+1; j < N; j++)
                    for (int k = j+1; k < N; k++)
                        if(a[i] + a[j] + a[k] == 0)
                            cnt++;

            return cnt;
        }
    }

    private static class ThreeSumFast {
        public static int count(int[] a) {
            Arrays.sort(a);

            int N = a.length;
            int cnt = 0;

            for (int i = 0; i < N; i++)
                for (int j = i+1; j < N; j++)
                    if (BinarySearch.rank(-a[i]-a[j], a) > j)
                        cnt++;

            return cnt;
        }
    }

    private static class BinarySearch {
        public static int rank(int key, int[] a) {
            return rank(key, a, 0, a.length-1);
        }

        private static int rank(int key, int[] a, int lo, int hi) {
            if (lo > hi) return -1;

            int mid = (lo + hi) / 2;

            if (a[mid] > key) return rank(key, a, lo, mid-1);
            else if (a[mid] < key) return rank(key, a, mid+1, hi);
            else return mid;
        }
    }
}