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
        // for (int i = 4; i > 0; i *= 2)
        //     runExperiment(i, i);

        // for (int i = 4; i > 0; i *= 2)
        //     runExperiment(i);

        // StdOut.println("Tests passed");

        int N = 100;
        for (int F = 1; F < N; F++) {
            EggThrowExperiment experiment = new EggThrowExperiment(N, F);
            assert experiment.maxSafeThrowHeight() == F;
            StdOut.println(experiment.eggThrows);
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
        int maxThrows = (int) (6 * Math.sqrt(F));
        int actualThrows = experiment.eggThrows;

        try {
            Test.assertLessThanOrEqual(actualF, F, "expected F to be " + F + " but it was " + actualF);
            Test.assertLessThanOrEqual(actualThrows, maxThrows, "expected throws to be less than or equal to " + maxThrows + " they were " + actualThrows);
        } catch (AssertionError e) {
            StdOut.printf(
                "Experiment failed for N=%s, F=%s, expected max throws: %s, actual throws: %s\n",
                N, F, maxThrows, actualThrows
            );

            throw e;
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
            int tIndex = indexOfTriangularNumberEqualOrGreaterThan(N);
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

        // 1 indexed. E.g: 1st = 1, 2nd = 3, 3rd = 6, 4th = 10, 5th = 15, and so on.
        private static int indexOfTriangularNumberEqualOrGreaterThan(int y) {
            return (int) Math.ceil(Math.sqrt(2 * y + 1/4) - 1/2.0);
        }

        private boolean eggBreaksWhenThrownFrom(int h) {
            assert eggsLeft > 0;
            eggThrows++;
            boolean broken = h >= F;
            if (broken) eggsLeft--;
            return broken;
        }
    }
}
