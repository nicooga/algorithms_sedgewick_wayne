package algsex.chapter1.section1;

import java.util.*;
import edu.princeton.cs.algs4.*;

// 1.1.39 Random matches. Write a BinarySearch client that takes an int value T as
// command-line argument and runs T trials of the following experiment for N = 10^3, 10^4,
// 10^5, and 10^6: generate two arrays of N randomly generated positive six-digit int values,
// and find the number of values that appear in both arrays. Print a table giving the average
// value of this quantity over the T trials for each value of N.
public class Exercise39 {
  private static final int RANDOM_INT_DIGITS = 6;
  private static final int MIN_TEN_POWER = 3;
  private static final int MAX_TEN_POWER = 6;
  private static final int TEN_POWERS_COUNT = MAX_TEN_POWER - MIN_TEN_POWER + 1;

  public static void main(String[] args) {
    int T = Integer.parseInt(args[0]);
    int[][] results = new int[T][TEN_POWERS_COUNT];
    for (int i = 0; i < results.length; i++) runExperiment(i, results);
    double[] averages = calculateAverages(results);
    printAverages(averages);
  }

  private static void runExperiment(int iteration, int[][] results) {
    for (int i = MIN_TEN_POWER; i <= MAX_TEN_POWER; i++) {
      int N = (int) Math.pow(10, i);
      results[iteration][i-MIN_TEN_POWER] = findIntersectionSizeBetweenNSizeRandomIntMaps(N);
    }
  }

  private static int findIntersectionSizeBetweenNSizeRandomIntMaps(int N) {
    Map<Integer, Integer> mapA = generateRandomInts(N);
    Map<Integer, Integer> mapB = generateRandomInts(N);
    return findIntersectionSize(mapA, mapB);
  }

  private static Map<Integer, Integer> generateRandomInts(int size) {
    Map<Integer, Integer> map = new HashMap<>(size);

    for (int i = 0; i < size; i++) {
      int randomInt = generateRandomInt();
      int count = Optional.ofNullable(map.get(randomInt)).orElse(0);
      map.put(randomInt, count + 1);
    }

    return map;
  }

  private static int generateRandomInt() {
    int min = (int) Math.pow(10, RANDOM_INT_DIGITS - 1);
    int max = (int) Math.pow(10, RANDOM_INT_DIGITS) - 1;
    return StdRandom.uniform(min, max + 1);
  }

  private static int findIntersectionSize(Map<Integer, Integer> mapA, Map<Integer, Integer> mapB) {
    int intersectionSize = 0;

    Iterator<Map.Entry<Integer, Integer>> iterator = mapA.entrySet().iterator();

    while(iterator.hasNext()) {
      int a = iterator.next().getKey();
      if (mapB.get(a) != null) intersectionSize++;
    }

    return intersectionSize;
  }

  private static double[] calculateAverages(int[][] results) {
    double[] averages = new double[results[0].length];

    for (int i = 0; i < results[0].length; i++) {
      double sum = 0.0;
      for (int j = 0; j < results.length; j++) sum += results[j][i];
      averages[i] = sum / results.length;
    }

    return averages;
  }

  private static void printAverages(double[] averages) {
    for (int i = 0; i < averages.length; i++) {
      int powerOfTen = MIN_TEN_POWER + i;
      StdOut.printf("10^%s\t", powerOfTen);
    }

    StdOut.println("");

    for (int i = 0; i < averages.length; i++)
      StdOut.printf("%.2f\t", averages[i]);

    StdOut.println("");
  }
}