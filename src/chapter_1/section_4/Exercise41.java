package algsex.chapter1.section4;

import java.util.*;
import edu.princeton.cs.algs4.*;
import algsex.chapter1.section4.Exercise38;
import algsex.support.Test;
import algsex.support.DoublingRatioTest;

// 1.4.41 Running times. Estimate the amount of time it would take to run TwoSumFast,
// TwoSum, ThreeSumFast and ThreeSum on your computer to solve the problems for a file
// of 1 million numbers. Use DoublingRatio to do so.
//
// Running experiment "TwoSum"
// Runs per N: 4
// N, avg. time (ms), avg. ratio, time std., time CV
// 1         0.8     0.0     0.5     0.7
// 2         1.3     1.7     0.5     0.4
// 4         1.3     1.0     0.5     0.4
// 8         1.3     1.0     0.5     0.4
// 16        1.0     0.8     0.0     0.0
// 32        1.0     1.0     0.0     0.0
// 64        1.8     1.8     0.5     0.3
// 128       1.8     1.0     0.5     0.3
// 256       2.0     1.1     0.8     0.4
// 512       3.8     1.9     3.5     0.9
// 1024      3.3     0.9     1.3     0.4
// 2048      4.8     1.5     2.2     0.5
// 4096      9.8     2.1     1.0     0.1
// 8192     33.0     3.4     0.8     0.0
// 16384   140.0     4.2     8.3     0.1
//
// Running experiment "TwoSumFast"
// Runs per N: 4
// N, avg. time (ms), avg. ratio, time std., time CV
// 1         1.3     0.0     0.5     0.4
// 2         1.3     1.0     0.5     0.4
// 4         1.0     0.8     0.0     0.0
// 8         1.8     1.8     1.5     0.9
// 16        0.8     0.4     0.5     0.7
// 32        0.8     1.0     0.5     0.7
// 64        1.5     2.0     0.6     0.4
// 128       1.0     0.7     0.0     0.0
// 256       1.3     1.3     0.5     0.4
// 512       2.5     2.0     0.6     0.2
// 1024      2.5     1.0     0.6     0.2
// 2048      3.8     1.5     1.5     0.4
// 4096      6.3     1.7     2.6     0.4
// 8192      9.3     1.5     1.9     0.2
// 16384    28.8     3.1     2.5     0.1
//
// Running experiment "ThreeSum"
// Runs per N: 4
// N, avg. time (ms), avg. ratio, time std., time CV
// 1         1.3     0.0     0.5     0.4
// 2         2.0     1.6     0.8     0.4
// 4         1.0     0.5     0.8     0.8
// 8         1.5     1.5     0.6     0.4
// 16        1.3     0.8     0.5     0.4
// 32        1.0     0.8     0.0     0.0
// 64        2.5     2.5     1.3     0.5
// 128       5.0     2.0     1.2     0.2
// 256      14.3     2.9     6.5     0.5
// 512      33.0     2.3    26.7     0.8
// 1024    145.3     4.4     2.2     0.0
// 2048    1209.5    8.3   129.9     0.1
// 4096    8654.8    7.2   223.0     0.0
// 8192    74320.0   8.6   4143.8    0.1
// 16384   658019.0          8.9   55529.2   0.1
//
// Running experiment "ThreeSumFast"
// Runs per N: 4
// N, avg. time (ms), avg. ratio, time std., time CV
// 1         1.8     0.0     0.5     0.3
// 2         2.3     1.3     0.5     0.2
// 4         1.3     0.6     0.5     0.4
// 8         1.5     1.2     0.6     0.4
// 16        1.5     1.0     0.6     0.4
// 32        1.5     1.0     0.6     0.4
// 64        2.0     1.3     0.8     0.4
// 128       4.5     2.3     1.3     0.3
// 256       5.5     1.2     3.1     0.6
// 512      11.8     2.1     1.3     0.1
// 1024     48.3     4.1     7.5     0.2
// 2048    182.8     3.8     5.1     0.0
// 4096    831.3     4.5     9.3     0.0
// 8192    4054.5    4.9   378.6     0.1
// 16384   15834.8   3.9   585.6     0.0
public class Exercise41 {
    private static final int MAX_N = 32768;

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
        StdOut.println("Priming test data ...");
        TestDataGenerator.prime(1, MAX_N);
        StdOut.println("Finished priming test data");

        new TwoSumTest().run();
        new TwoSumFastTest().run();
        new ThreeSumTest().run();
        new ThreeSumFastTest().run();
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
        protected boolean iterationCondition(int N) { return N <= MAX_N; }

        @Override
        protected int defaultRunsPerN() { return 4; };
    }

    private static class TestDataGenerator {
        private static final int INTERVAL_SIZE = MAX_N;
        private static Map<Integer, Integer[]> cache = new HashMap<>();

        public static void prime(int minN, int maxN) {
            assert minN <= maxN;
            for (int N = minN; N <= maxN; N++) getOrCreateTestData(N);
        }

        public static int[] getOrCreateTestData(int N) {
            if (!cache.containsValue(N)) {
                Integer[] a = createTestData(N);
                cache.put(N, a);
            }

            Integer[] a = cache.get(N);

            // Trick to cast Integer[] to int[]
            return Arrays.stream(a).mapToInt(Integer::intValue).toArray();
        }

        private static Integer[] createTestData(int N) {
            assert INTERVAL_SIZE >= N;

            Set<Integer> s = new HashSet<>();

            for (int i = 0; i < N; i++) {
                int x;
                do x = StdRandom.uniform(-INTERVAL_SIZE/2, INTERVAL_SIZE/2);
                while (s.contains(x));
                s.add(x);
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