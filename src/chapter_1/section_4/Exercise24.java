package algsex.chapter1.section4;

import java.util.*;
import edu.princeton.cs.algs4.*;
import algsex.support.Test;

// 1.4.24 Throwing eggs from a building. Suppose that you have an N-story building and
// plenty of eggs. Suppose also that an egg is broken if it is thrown off floor F or higher,
// and intact otherwise. First, devise a strategy to determine the value of F such that the
// number of broken eggs is ~lg N when using ~lg N throws, then find a way to reduce the
// cost to ~2 lg F.
public class Exercise24 {
    public static void main(String[] args) {
        runExperiment(10, 7);
        runExperiment(76, 23);
        runExperiment(18, 11);
        runExperiment(100, 0);
        runExperiment(100, 99);

        for (int i = 0; i < 1000; i++) runExperiment();

        StdOut.println("Tests passed");
    }

    private static void runExperiment() {
        int N = StdRandom.uniform(10, 100);
        int F = StdRandom.uniform(0, N);
        runExperiment(N, F);
    }

    private static void runExperiment(int N, int F) {
        EggThrowExperiment experiment = new EggThrowExperiment(N, F);
        int maxBrokenEggsAndThrows = (int) (2 * lg(N));
        Test.assertEqual(experiment.maxSafeThrowHeight(), F);
        Test.assertLessThanOrEqual(experiment.brokenEggs, maxBrokenEggsAndThrows);
        Test.assertLessThanOrEqual(experiment.eggThrows, maxBrokenEggsAndThrows);
    }

    private static double lg(double N) {
        return Math.log(N) / Math.log(2);
    }

    private static class EggThrowExperiment {
        public int brokenEggs = 0;
        public int eggThrows = 0;

        private int N;
        private int F;

        public EggThrowExperiment(int N, int F) {
            assert F < N;
            this.N = N;
            this.F = F;
        }

        public int maxSafeThrowHeight() {
            int lo = 0;
            int hi = N-1;

            brokenEggs = 0;
            eggThrows = 0;

            while (lo <= hi) {
                if (lo == hi) return lo;
                int mid = (lo + hi) / 2;
                if (eggBreaksWhenThrownFrom(mid)) hi = mid;
                else lo = mid+1;
            }

            return -1;
        }

        private boolean eggBreaksWhenThrownFrom(int h) {
            boolean broken = h >= F;
            eggThrows++;
            if (broken) brokenEggs++;
            return broken;
        }
    }
}
