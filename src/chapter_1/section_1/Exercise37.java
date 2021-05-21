package algsex.chapter1.section1;

import java.util.*;
import edu.princeton.cs.algs4.*;

public class Exercise37 {
  public static void main(String[] args) {
    int M = Integer.parseInt(args[0]);
    int N = Integer.parseInt(args[1]);

    int[][] resultsTable = new int[M][M];

    runTest(M, N, resultsTable);
    printResults(resultsTable, N);
  }

  private static void runTest(int M, int N, int[][] resultsTable) {
    int[] array = new int[M];

    while (N > 0) {
      resetArray(array);
      shuffle(array);
      storeResults(array, resultsTable);
      N--;
    }
  }

  private static void shuffle(int[] array) {
    for (int i = 0; i < array.length; i++) {
      int indexToSwapWith = StdRandom.uniform(array.length);
      int temp = array[i];
      array[i] = array[indexToSwapWith];
      array[indexToSwapWith] = temp;
    }
  }

  private static void resetArray(int[] a) {
    for (int i = 0; i < a.length; i++) a[i] = i;
  }

  private static void storeResults(int[] array, int[][] resultsTable) {
    for (int i = 0; i < array.length; i++) {
      int result = array[i];
      resultsTable[result][i]++;
    }
  }

  private static void printResults(int[][] resultsTable, int N) {
    StdDraw.setXscale(0, 11);
    StdDraw.setYscale(0, 11);

    for (int i = 0; i < resultsTable.length; i++) {
      StdDraw.text(0.5, 9.5 - i, Integer.toString(i)); // print vertical header
      StdDraw.text(i + 1.5, 10.5, Integer.toString(i)); // print horizontal header
    }

    for (int i = 0; i < resultsTable.length; i++) {
      StdOut.print(i + " ");

      for (int j = 0; j < resultsTable[i].length; j++) {
        int numberOfTimesAppeared = resultsTable[i][j];
        double ratio = (double) numberOfTimesAppeared / (double) N;

        StdDraw.setPenRadius(0.8 * ratio);
        StdDraw.point(i + 1.5, j + 0.5);

        StdOut.printf("%.2f \t", ratio);
      }

      StdOut.println("");
    }
  }

  private static int sum(int[] a) {
    int result = 0;
    for (int i = 0; i < a.length; i++) result += a[i];
    return result;
  }
}