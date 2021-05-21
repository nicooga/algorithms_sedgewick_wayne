package algsex.chapter1.section1;

import java.lang.*;
import java.util.*;
import edu.princeton.cs.algs4.*;

public class Exercise35 {
  static final int DICE_SIDES = 6;

  public static void main(String[] args) {
    double[] expectedThrowDistribution = calculateExpectedTrowDistribution(DICE_SIDES);
    double[] actualThrowDistribution;
    int N = 1;

    // StdOut.println(Arrays.toString(expectedThrowDistribution));

    do {
      StdOut.println("=================");
      StdOut.printf("Simluating %s throws\n", N);
      actualThrowDistribution = simulateThrowDistribution(N, DICE_SIDES);
      StdOut.println("Actual distribution was: " + Arrays.toString(actualThrowDistribution));
      StdOut.println("=================");
      N++;
    } while (!distributionsMatches(actualThrowDistribution, expectedThrowDistribution));

    StdOut.println("Actual distribution converged with expected one at N = " + N);
  }

  private static double[] calculateExpectedTrowDistribution(int diceSides) {
    double[] dist = createEmptyThrowDistribution(diceSides);

    for (int i = 1; i <= diceSides; i++)
      for (int j = 1; j <= diceSides; j++)
        dist[i+j] += 1.0;

    for (int k = 2; k <= 2*diceSides; k++)
      dist[k] /= Math.pow(diceSides, 2);

    truncateToDecimalPlaces(3, dist);

    return dist;
  }


  private static boolean distributionsMatches(double[] actual, double[] expected) {
    return Arrays.equals(actual, expected);
  }

  private static double[] simulateThrowDistribution(int N, int diceSides) {
    double[] actualDist = createEmptyThrowDistribution(diceSides);

    for (int i = 0; i < N; i++) {
      int dice1 = randomDiceThrow(diceSides);
      int dice2 = randomDiceThrow(diceSides);
      actualDist[dice1 + dice2] += 1.0;
    }

    for (int i = 0; i < actualDist.length; i++)
      actualDist[i] = truncateToDecimalPlaces(3, actualDist[i]/N);

    return actualDist;
  }

  private static void truncateToDecimalPlaces(int places, double[] numbers) {
    for (int i = 0; i < numbers.length; i++)
      numbers[i] = truncateToDecimalPlaces(places, numbers[i]);
  }

  private static double truncateToDecimalPlaces(int places, double number) {
    double scalar = Math.pow(10, places);
    return (double) Math.round(number * scalar) / scalar;
  }

  private static double[] createEmptyThrowDistribution(int diceSides) {
    return new double[2*diceSides + 1];
  }

  private static int randomDiceThrow(int diceSides) {
    return StdRandom.uniform(1, diceSides + 1);
  }
}