package algsex.chapter1.section4;

import java.util.*;
import edu.princeton.cs.algs4.*;
import algsex.chapter1.section4.Exercise38;
import algsex.support.Test;
import algsex.support.DoublingRatioTest;

// 1.4.41 Running times. Estimate the amount of time it would take to run TwoSumFast,
// TwoSum, ThreeSumFast and ThreeSum on your computer to solve the problems for a file
// of 1 million numbers. Use DoublingRatio to do so.
public class Exercise41 {
    private static final int MAX_N = 32768;

    public static void main(String[] args) {
        TestDataGenerator.getOrCreateTestData(1);
        TestDataGenerator.getOrCreateTestData(1);
        // runBasicTests();
        // runDoublingRatioTests();
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
        TestDataGenerator.prime(1, MAX_N);

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
            int[] a = TestDataGenerator.getOrCreateTestData(N);
            doRunExperiment(a);
        }

        @Override
        protected int defaultRunsPerN() { return 4; };
    }

    private static class TestDataGenerator {
        private static final int INTERVAL_SIZE = MAX_N;
        private static Map<Integer, Integer[]> cache = new HashMap<>();

        public static void prime(int minN, int maxN) {
            assert minN <= maxN;

            StdOut.println("Priming test data ...");

            for (int N = minN; N <= maxN && N > 0; N *= 2)
                getOrCreateTestData(N);

            StdOut.println("Finished priming test data");
        }

        public static int[] getOrCreateTestData(int N) {
            if (!cache.containsValue(N)) cache.put(N, createTestData(N));

            Integer[] a = cache.get(N);

            // Trick to cast Integer[] to int[]
            return Arrays.stream(a).mapToInt(Integer::intValue).toArray();
        }

        private static Integer[] createTestData(int N) {
            assert INTERVAL_SIZE >= N;

            StdOut.println("Generating integer set of size " + N);

            Set<Integer> s = new HashSet<>();

            for (int i = 0; i < N; i++) {
                int x;
                do x = StdRandom.uniform(-INTERVAL_SIZE/2, INTERVAL_SIZE/2);
                while (s.contains(x));
                s.add(x);
                StdOut.printf("(%d/%d)\r", i, N);
            }

            assert s.size() == N;

            Integer[] a = new Integer[N];
            int index = 0;
            for (Integer x : s) a[index++] = x;

            return a;
        }
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