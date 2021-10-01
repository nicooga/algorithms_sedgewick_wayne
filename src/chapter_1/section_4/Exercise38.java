package algsex.chapter1.section4;

import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import algsex.support.DoublingRatioExperiment;

// 1.4.38 Naive 3-sum implementation. Run experiments to evaluate the following im-
// plementation of the inner loop of ThreeSum:
//
//     for (int i = 0; i < N; i++)
//         for (int j = 0; j < N; j++)
//             for (int k = 0; k < N; k++)
//                 if (i < j && j < k)
//                     if (a[i] + a[j] + a[k] == 0)
//                         cnt++;
//
// Do so by developing a version of DoublingTest that computes the ratio of the running
// times of this program and ThreeSum.
//
// Conclusions: as expected, FastThreeSum is ridicoulously faster than NaiveThreeSum.
//
// Running experiment for Fast ThreeSum
// 2   0.0   NaN
// 4   0.0   NaN
// 8   0.0   NaN
// 16   0.0   NaN
// 32   0.0   NaN
// 64   0.0   NaN
// 128   0.0 Infinity
// 256   0.0   1.0
// 512   0.0   3.0
// 1024   0.0   3.7
// 2048   0.1   6.8
// 4096   0.2   2.6
// 8192   0.9   4.4
// 16384   3.5   4.1
//
// Running experiment for Naive ThreeSum
// 2   0.0   NaN
// 4   0.0   NaN
// 8   0.0   NaN
// 16   0.0   NaN
// 32   0.0   NaN
// 64   0.0 Infinity
// 128   0.0   1.0
// 256   0.0   5.0
// 512   0.0   7.6
// 1024   0.3   7.4
// 2048   2.1   7.6
// 4096  18.4   8.6
// 8192 167.6   9.1
// 16384 1360.9   8.1
public class Exercise38 {
    public static int MAX_N_SIZE = 4096;

    public static void main(String[] args) {
        int[] a = new int[] { 1, 0, -1 };
        assert NaiveThreeSum.count(a) == 1;
        assert FastThreeSum.count(a) == 1;

        primeTestDataGenerator();

        while (true) {
            new FastThreeSumExperiment().run();
            new NaiveThreeSumExperiment().run();
        }
    }

    public static void primeTestDataGenerator() {
        for (int N = 2; N <= MAX_N_SIZE; N *= 2)
            TestDataGenerator.getOrCreateTestData(N);
    }

    public static class NaiveThreeSumExperiment extends Experiment {
        protected String label() { return "Naive ThreeSum"; }

        protected void doDoRunExperiment(int[] a) {
            NaiveThreeSum.count(a);
        }
    }

    public static class FastThreeSumExperiment extends Experiment {
        protected String label() { return "Fast ThreeSum"; }

        protected void doDoRunExperiment(int[] a) {
            FastThreeSum.count(a);
        }
    }

    private static abstract class Experiment extends DoublingRatioExperiment  {
        protected void doRunExperiment(int N) {
            int[] a = TestDataGenerator.getOrCreateTestData(N);
            doDoRunExperiment(a);
        }

        protected boolean iterationCondition(int N) {
            return N <= 16384;
        }

        protected abstract void doDoRunExperiment(int[] a);
    }

    // Generates and serves test data, ensuring we use the same data for all tests.
    public static class TestDataGenerator {
        private static Map<Integer, Integer[]> cache = new HashMap<>();

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
            Integer[] a = new Integer[N];
            for (int i = 0; i < N; i++) a[i] = StdRandom.uniform(-100, 100);
            return a;
        }
    }

    private static class NaiveThreeSum {
        public static int count(int[] a) {
            int N = a.length;
            int cnt = 0;

            for (int i = 0; i < N; i++)
                for (int j = 0; j < N; j++)
                    for (int k = 0; k < N; k++)
                        if (i < j && j < k)
                            if (a[i] + a[j] + a[k] == 0)
                                cnt++;

            return cnt;
        }
    }

    private class FastThreeSum {
        public static int count(int[] a) {
            Arrays.sort(a);

            int N = a.length;
            int cnt = 0;

            for (int i = 0; i < N; i++)
                for (int j = i+1; j < N; j++)
                    if (rank(-a[i]-a[j], a, j+1, a.length-1) > j)
                        cnt++;

            return cnt;
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
