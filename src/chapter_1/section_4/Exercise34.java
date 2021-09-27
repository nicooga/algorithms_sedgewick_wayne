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
    public static void main(String[] _args) {
        int N = 1000;

        for (int i = 0; i < 10000; i++) {
            Player p = Player.withRandomAnswerWithinTwoAnd(N);
            Test.assertEqual(HotColdV1.guess(p, N), p.secretNumber);
            Test.assertLessThanOrEqual(p.guessCount, (int) (2 * lg(N)));
        }

        for (int i = 0; i < 10000; i++) {
            Player p = Player.withRandomAnswerWithinTwoAnd(N);
            double maxP = Math.ceil(lg(4*p.secretNumber/3));
            double maxGuesses = maxP + 1 + 2 * lg(Math.pow(2, maxP - 1));

            Test.assertEqual(HotColdV2.guess(p), p.secretNumber);
            Test.assertLessThanOrEqual(p.guessCount, (int) (maxGuesses));
        }

        StdOut.println("Tests passed");
    }

    private static double lg(double x) {
        return Math.log(x) / Math.log(2);
    }

    private static class HotColdV1 {
        public static int guess(Player p, int N) {
            return guess(p, 1, N);
        }

        public static int guess(Player p, int lo, int hi) {
            int mid = (lo + hi) / 2;

            if (p.guess(mid) == GuessAnswer.CORRECT) return mid;

            switch (p.guess(mid+1)) {
                case CORRECT: return mid+1;
                case HOT: return guess(p, mid+2, hi);
                case COLD: return guess(p, lo, mid-1);
            }

            throw new RuntimeException("This statement should not have been reached");
        }
    }

    // AFAIU, this algorithm has better complexity than ~ 2 lg N, but it is still not ~ 1 lg N.
    private static class HotColdV2 {
        public static int guess(Player p) {
            int lo = 1;
            int hi = 2;

            if (p.guess(lo) == GuessAnswer.CORRECT) return lo;

            GuessAnswer answer = p.guess(hi);

            while (answer == GuessAnswer.HOT) {
                lo *= 2;
                hi *= 2;
                answer = p.guess(hi);
            }

            switch (answer) {
                case CORRECT: return hi;
                case EQUALLY_APART: return (lo + hi) / 2;
                case COLD: return HotColdV1.guess(p, (3*lo)/4, (lo+hi)/2);
                default: throw new RuntimeException("This statement should not have been reached");
            }
        }
    }

    private enum GuessAnswer { UNKNOWN, CORRECT, HOT, COLD, EQUALLY_APART }

    private static class Player {
        public final int secretNumber;
        public int guessCount = 0;
        private int lastGuess = -1;

        public static Player withRandomAnswerWithinTwoAnd(int N) {
            return new Player(StdRandom.uniform(2, N+1));
        }

        public Player(int secretNumber) {
            this.secretNumber = secretNumber;
        }

        public GuessAnswer guess(int guess) {
            GuessAnswer result = GuessAnswer.UNKNOWN;

            if (guess == secretNumber) result = GuessAnswer.CORRECT;
            else if (lastGuess != -1) {
                int guessDistance = Math.abs(guess - secretNumber);
                int lastGuessDistance = Math.abs(lastGuess - secretNumber);

                if (guessDistance < lastGuessDistance) result = GuessAnswer.HOT;
                else if (guessDistance > lastGuessDistance) result = GuessAnswer.COLD;
                else result = GuessAnswer.EQUALLY_APART;
            }

            lastGuess = guess;
            guessCount++;

            return result;
        }
    }
}