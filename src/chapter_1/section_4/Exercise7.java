package algsex.chapter1.section4;

import java.util.*;
import edu.princeton.cs.algs4.*;
import algsex.support.Rational;

public class Exercise7 {
  public static void main(String[] args) {
    test(new int[] { 0, 1, -1, 2, 3, 4, 5, 6, 7, 8, 9, 2 }, 1);
  }

  private static void test(int[] a, int matchingPairs) {
    int expectedOperations = expectedOperations(a, matchingPairs);

    assert InstrumentedThreeSum.count(a) == 1;

    int actualOperations = InstrumentedThreeSum.operations;

    assert actualOperations == expectedOperations :
      String.format("Expected %s operations, but was %s.", expectedOperations, actualOperations); 
  }

  private static int expectedOperations(int[] a, int matchingPairs) {
    int N = a.length;

    Rational twoThirdsOfNCubed = r(2, 3).times(r(Math.pow(N, 3)));
    Rational threeHalvesNSquared = r(3, 2).times(r(Math.pow(N, 2)));
    Rational elevenSixthsN = r(11, 6).times(r(N));

    return
      twoThirdsOfNCubed
        .minus(threeHalvesNSquared)
        .plus(elevenSixthsN)
        .plus(r(matchingPairs))
        .toInteger();
  }

  private static Rational r(int a, int b) { return new Rational(a, b); }
  private static Rational r(int a) { return new Rational((long) a, (long) 1); }
  private static Rational r(double a) { return new Rational((long) a, (long) 1); }

  private class InstrumentedThreeSum {
    public static int operations = 0;

    public static int count(int[] a) {
      int N = a.length;
      int cnt = 0;

      operations = 0;

      for (int i = 0; i < N; i++) { // i is incremented N-1 times
        for (int j = i+1; j < N; j++) { // for each i, j is incremented (N-1) - (i + 1) times
          for (int k = j+1; k < N; k++) { // for each j, k is incremented (N - 1) - (j + 1) times
            if (a[i] + a[j] + a[k] == 0) { // for each k, 2 sums and and one comparison ocur
              cnt++; // depends on the number of triples found
              // operations++;
            }

            operations += 4;
          }

          operations++;
        }

        operations++;
      }
  
      return cnt;
    }
  }
}
