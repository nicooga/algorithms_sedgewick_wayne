package algsex.chapter1.section1;

import java.util.*;
import edu.princeton.cs.algs4.*;

public class Exercise36 {
  public static void main(String[] args) {
    int M = Integer.parseInt(args[0]);
    int N = Integer.parseInt(args[1]);

    int[][] resultsTable = new int[M][M];

    runTest(M, N, resultsTable);
    printResults(resultsTable);
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
      int indexToSwapWith = StdRandom.uniform(i, array.length);
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

  private static void printResults(int[][] resultsTable) {
    for (int i = 0; i < resultsTable.length; i++)
      StdOut.println(Arrays.toString(resultsTable[i]));
  }
}