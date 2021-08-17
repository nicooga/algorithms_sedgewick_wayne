package algsex.chapter1.section4;

import java.util.*;
import edu.princeton.cs.algs4.*;
import algsex.support.Rational;

// 1.4.7 Analyze ThreeSum under a cost model that counts arithmetic operations (and
// comparisons) involving the input numbers.
public class Exercise7 {
  public static void main(String[] args) {
    assertCorrect(new int[] { 0, 1, -1, 2, 3, 4, 5, 6, 7, 8, 9, 2 }, 1);
    assertCorrect(new int[] { 0, 1, -1, 2, 3, 4, 5, 6, 7, 8, 9, 2, 23 }, 1);
    assertCorrect(new int[] { 0, 1, -1, 2, 3, 4, 5, 6, 7, 8, 9, 2, 23, -2, -3 }, 9);

    StdOut.println("Tests passed");
  }

  private static void assertCorrect(int[] a, int expectedTripleCount) {
    int actualTripleCount = InstrumentedThreeSum.count(a);

    assert actualTripleCount == expectedTripleCount :
      String.format("Expected triple count to be %s, but was %s.", expectedTripleCount, actualTripleCount);

    int expectedOperations = expectedOperations(a, expectedTripleCount);
    int actualOperations = InstrumentedThreeSum.operations;

    assert actualOperations == expectedOperations :
      String.format("Expected %s operations, but was %s.", expectedOperations, actualOperations); 
  }

  private static int expectedOperations(int[] a, int expectedTripleCount) {
    int N = a.length;

    return
      r(2, 3).times(r(Math.pow(N, 3)))
        .minus(r(3, 2).times(r(Math.pow(N, 2))))
        .plus(r(11, 6).times(r(N)))
        .plus(r(expectedTripleCount))
        .toInteger();
  }

  private static Rational r(double a, int b) { return new Rational((long) a, (long) b); }
  private static Rational r(int a, int b) { return new Rational((long) a, (long) b); }
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
              cnt++;
              operations++; // depends on the number of triples found
            }

            operations += 4; // k increments, sums and comparisons
          }

          operations++; // j increments
        }

        operations++; // i increments
      }
  
      return cnt;
    }
  }
}
