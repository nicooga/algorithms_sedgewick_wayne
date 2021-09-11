package algsex.chapter1.section4;

import java.util.*;
import edu.princeton.cs.algs4.*;
import algsex.support.Test;

// 1.4.25 Throwing two eggs from a building. Consider the previous question, but now
// suppose you only have two eggs, and your cost model is the number of throws. Devise a
// strategy to determine F such that the number of throws is at most 2√N, then find a way
// to reduce the cost to ~c√F for some constant c. This is analogous to a situation where
// search hits (egg intact) are much cheaper than misses (egg broken).
public class Exercise25 {
    public static void main(String[] args) {
        MainTest.main();
        StdOut.println("Tests passed");
    }

    private static class MainTest {
        public static void main() {
            for (int N = 4; N > 0; N *= 2) {
                runExperiment(N, 1);
                runExperiment(N, N);
                runExperiment(N);
            }
        }

        private static void runExperiment() {
            int N = StdRandom.uniform(10, 1000);
            runExperiment(N);
        }

        private static void runExperiment(int N) {
            int F = StdRandom.uniform(1, N+1);
            runExperiment(N, F);
        }

        private static void runExperiment(int N, int F) {
            EggThrowExperiment experiment = new EggThrowExperiment(N, F);
            int actualF = experiment.maxSafeThrowHeight();
            int maxThrows = (int) (4 * Math.sqrt(F));
            int expectedThrows = expectedThrows(N, F);
            int actualThrows = experiment.eggThrows;

            try {
                Test.assertLessThanOrEqual(actualF, F, "expected F to be " + F + " but it was " + actualF);
                Test.assertLessThanOrEqual(actualThrows, maxThrows, "expected throws to be less than or equal to " + maxThrows + " they were " + actualThrows);
                Test.assertLessThanOrEqual(actualThrows, expectedThrows, "expected throws to be " + expectedThrows + " they were " + actualThrows);
            } catch (AssertionError e) {
                StdOut.printf(
                    "Experiment failed for N=%s, F=%s, expected max throws: %s, actual throws: %s\n",
                    N, F, maxThrows, actualThrows
                );

                throw e;
            }
        }

        private static int expectedThrows(int N, int F) {
            int ti = TriangularNumber.indexOfTriangularNumberEqualToOrGreaterThan(N);
            int l = TriangularNumber.layerFromBottomFor(F, ti);
            return l + Math.min(F - TriangularNumber.partialSumForLayersUpTo(l-1, ti), TriangularNumber.numberCountForLayer(l, ti)-1);
        }
    }

    // Note: Floors are 1 indexed. I.e: first floor is 1, not 0.
    private static class EggThrowExperiment {
        public int eggsLeft;
        public int eggThrows;

        private int N;
        private int F;

        public EggThrowExperiment(int N, int F) {
            assert F <= N;
            this.N = N;
            this.F = F;
        }

        public int maxSafeThrowHeight() {
            int tIndex = TriangularNumber.indexOfTriangularNumberEqualToOrGreaterThan(N);
            int lo = 1;
            int hi = (int) tIndex;

            eggsLeft = 2;
            eggThrows = 0;

            while (eggsLeft > 0) {
                if (eggBreaksWhenThrownFrom(hi)) {
                    for (int i = lo; i < hi; i++)
                        if (eggBreaksWhenThrownFrom(i))
                            return i;
                    
                    return hi;
                } 

                lo = hi+1;
                hi += tIndex - eggThrows;
                hi = Math.min(N, hi);
            }

            throw new RuntimeException("This should not have happened");
        }

        private boolean eggBreaksWhenThrownFrom(int h) {
            assert eggsLeft > 0;
            eggThrows++;
            boolean broken = h >= F;
            if (broken) eggsLeft--;
            return broken;
        }
    }

    // ti stands for "triangular number index", which happens to be the same as the length of the biggest row in any triangular number
    private static class TriangularNumber {
        // 1 indexed. E.g: 1st = 1, 2nd = 3, 3rd = 6, 4th = 10, 5th = 15, and so on.
        public static int indexOfTriangularNumberEqualToOrGreaterThan(int x) {
            return (int) Math.ceil(indexOfTriangularNumberEqualTo(x));
        }

        private static double indexOfTriangularNumberEqualTo(int x) {
            return Math.sqrt(2 * x + 1/4.0) - 1/2.0;
        }

        private static int triangularNumberAtIndex(int i) {
            return (int) ((Math.pow(i, 2) + i) / 2.0);
        }

        public static int layerFromBottomFor(int x, int ti) {
            return (int) Math.ceil(ti + 1/2.0 - Math.sqrt(Math.pow(ti + 1/2.0, 2) - 2*x));
        }

        public static int partialSumForLayersUpTo(int layer, int ti) {
            return (int) (layer * ti - Math.pow(layer, 2) / 2.0 + layer / 2.0);
        }

        public static int numberCountForLayer(int l, int ti) {
            return ti - l + 1;
        }
    }
}
