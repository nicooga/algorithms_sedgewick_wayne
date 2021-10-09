package algsex.chapter1.section4;

import java.util.*;
import edu.princeton.cs.algs4.*;

// 1.4.45 Coupon collector problem. Generating random integers as in the previous exer-
// cise, run experiments to validate the hypothesis that the number of integers generated
// before all possible values are generated is ~N HN.
public class Exercise45 {
    public static void main(String[] args) {
        Experiment.run();
    }

    private static class Experiment {
        public static void run() {
            for (int N = 32; true; N *= 2) run(N);
        }

        private static void run(int N) {
            double averageG = 0;
            double Hn = HarmonicNumber.nth(N);
            double expectedG = N * Hn;

            for (int i = 1; i < N/10; i++) {
                int G = G(N);
                if (i == 0) averageG = G;
                else averageG = (averageG * (i-1) + G)/i;
            }

            double deviation = Math.abs(1 - (averageG / expectedG)); // Not as effective as standard deviation, but easier to compute.

            StdOut.printf("N: %d Hn: %.2f, average vs expected G: %.1f / %.1f, deviation: %.1f\n", N, Hn, averageG, expectedG, deviation);

            assert deviation < 0.2;
        }

        private static int G(int N) {
            Set<Integer> set = new HashSet<>();
            int generatedNumbers = 0;

            while (set.size() < N) {
                set.add(randomInt(N));
                generatedNumbers++;
            }

            return generatedNumbers;
        }

        private static int randomInt(int N) {
            return StdRandom.uniform(N);
        }
    }

    private static class HarmonicNumber {
        private static Map<Integer, Double> cache = new HashMap<>();

        public static double nth(int N) {
            if (N == 1) return 1.0;

            if (!cache.containsKey(N)) {
                if (cache.containsKey(N-1))
                    cache.put(N, nth(N-1) + 1.0/N);
                else {
                    double result = 0;
                    for (int i = 1; i <= N; i++) result += 1.0/i;
                    cache.put(N, result);
                }
            }

            return cache.get(N);
        }
    }
}
