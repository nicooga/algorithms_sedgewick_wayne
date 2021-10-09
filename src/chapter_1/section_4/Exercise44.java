package algsex.chapter1.section4;

import java.util.*;
import edu.princeton.cs.algs4.*;

// 1.4.44 Birthday problem. Write a program that takes an integer N from the command
// line and uses StdRandom.uniform() to generate a random sequence of integers be-
// tween 0 and N – 1. Run experiments to validate the hypothesis that the number of
// integers generated before the first repeated value is found is ~√(pi*N/2).
public class Exercise44 {
    public static void main(String[] args) {
        BirthdayProblem.runExperiment();
    }

    private static class BirthdayProblem {
        public static void runExperiment() {
            for (int N = 3000; true; N *= 2) runExperiment(N);
        }

        public static void runExperiment(int N) {
            int expectedG = (int) Math.floor(Math.sqrt(Math.PI * N / 2));
            double averageG = 0;

            for (int i = 1; i < N/100; i++) {
                int G = triesBeforeFirstRepeat(N);
                if (i == 1) averageG = G;
                else averageG = (averageG * (i-1) + G) / i;
            }

            double deviation = Math.abs(1 - (averageG / expectedG));

            assert deviation <= 0.2 : "Actual deviation was " + deviation; // This confirms our hypotesis

            StdOut.printf("N: %d, expected vs average G: %d / %.2f, deviation: %.1f\n", N, expectedG, averageG, deviation);
        }

        public static int triesBeforeFirstRepeat(int N) {
            Set<Integer> set = new HashSet();
            int generatedIntCount = 0;

            while (true) {
                int x = generateRandomInt(N);
                if (set.contains(x)) return generatedIntCount;
                set.add(x);
                generatedIntCount++;
            }
        }

        public static int generateRandomInt(int N) {
            return StdRandom.uniform(N);
        }
    }
}