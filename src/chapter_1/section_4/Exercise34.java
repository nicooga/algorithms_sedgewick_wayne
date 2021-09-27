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

        for (int i = 0; i < 100; i++) {
            Player p = Player.withRandomAnswerWithinOneAnd(N);
            Test.assertEqual(HotColdV1.guess(p, N), p.secretNumber);
        }

        for (int i = 0; i < 100; i++) {
            Player p = Player.withRandomAnswerWithinOneAnd(N);

            try {
                Test.assertEqual(HotColdV2.guess(p, N), p.secretNumber);
            } catch (Throwable e) {
                StdOut.println(p.secretNumber);
                throw e;
            }
        }

        StdOut.println("Tests passed");
    }

    private static class HotColdV1 {
        // private static int loopCount; // debug

        public static int guess(Player p, int N) {
            return guess(p, 1, N);
        }

        public static int guess(Player p, int lo, int hi) {
            // loopCount++; // debug
            // if (loopCount > Math.log(1000) / Math.log(2)) throw new RuntimeException("Too deep"); // debug

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

    private static class HotColdV2 {
        public static int guess(Player p, int N) {
            int lo = 1;
            int hi = 2;

            if (p.guess(lo) == GuessAnswer.CORRECT) return lo;

            GuessAnswer answer = p.guess(hi);

            while (answer == GuessAnswer.HOT) {
                int temp = hi;
                // hi = Math.min(hi*2, N);
                hi *= 2;
                lo = temp;

                StdOut.println("=====");
                StdOut.println("lo: " + lo);
                StdOut.println("hi: " + hi);

                answer = p.guess(hi);
            }

            StdOut.println("Finished looping: " + answer);

            switch (answer) {
                case CORRECT: return hi;
                case EQUALLY_APART: {
                    // StdOut.println("Guesses were equally apart");
                    return (lo + hi) / 2;
                }
                case COLD: {
                    // StdOut.println("=====");
                    // StdOut.println("lo: " + lo);
                    // StdOut.println("hi: " + hi);
                    return HotColdV1.guess(p, (3*lo)/4, (lo+hi)/2);
                }
                case HOT: {
                    // StdOut.println("=====");
                    // StdOut.println("lo: " + lo);
                    // StdOut.println("hi: " + hi);
                    return HotColdV1.guess(p, (lo+hi)/2, hi);
                }
                default: throw new RuntimeException("This statement should not have been reached");
            }
        }
    }

    private enum GuessAnswer { UNKNOWN, CORRECT, HOT, COLD, EQUALLY_APART }

    private static class Player {
        public final int secretNumber;
        public int guesses = 0;
        private int lastGuess = -1;

        public static Player withRandomAnswerWithinOneAnd(int N) {
            return new Player(StdRandom.uniform(1, N+1));
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

            // StdOut.printf("Guessing %s, given last guess %s: %s\n", guess, lastGuess, result);

            lastGuess = guess;
            guesses++;

            return result;
        }
    }
}