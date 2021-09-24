package algsex.chapter1.section4;

import edu.princeton.cs.algs4.*;
import algsex.support.Test;

// 1.4.34 Hot or cold. Your goal is to guess a secret integer between 1 and N. You repeat-
// edly guess integers between 1 and N. After each guess you learn if your guess equals the
// secret integer (and the game stops). Otherwise, you learn if the guess is hotter (closer to)
// or colder (farther from) the secret number than your previous guess. Design an algo-
// rithm that finds the secret number in at most ~2 lg N guesses. Then design an algorithm
// that finds the secret number in at most ~ 1 lg N guesses.
public class Exercise34 {
    public static void main(String[] args) {
        int N = 1000;

        for (int i = 0; i < 100; i++) {
            int secretInt = StdRandom.uniform(1, N+1);
            Test.assertEqual(HotColdV1.guess(secretInt, N), secretInt);
        }

        StdOut.println("Tests passed");
    }

    private static class HotColdV1 {
        public static int guess(int secretInt, int N) {
            return guess(secretInt, 1, N);
        }

        private static int guess(int secretInt, int lo, int hi) {
            int guess = (lo + hi) / 2;

            if (guess == secretInt) return guess;
            if (guess+1 == secretInt) return guess+1;

            int guessDistance = Math.abs(guess - secretInt);
            int nextGuessDistance = Math.abs(guess+1 - secretInt);

            if (guessDistance < nextGuessDistance) return guess(secretInt, lo, guess-1);
            else return guess(secretInt, guess+2, hi);
        }
    }
}
