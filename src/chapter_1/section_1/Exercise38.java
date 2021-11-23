package algsex.chapter1.section1;

import java.util.*;
import edu.princeton.cs.algs4.*;

// 1.1.38 Binary search versus brute-force search. Write a program BruteForceSearch
// that uses the brute-force search method given on page 48 and compare its running time
// on your computer with that of BinarySearch for largeW.txt and largeT.txt.
public class Exercise38 {
  public static void main(String[] args) {
    int key = Integer.parseInt(args[0]);
    int[] ints = readIntsFromStdIn();
    runBruteForceSearch(key, ints);
  }

  private static int[] readIntsFromStdIn() {
    List<Integer> list = new ArrayList<>();
    while(!StdIn.isEmpty()) list.add((Integer) StdIn.readInt());
    return list.stream().mapToInt(i -> i).toArray();
  }

  private static void runBruteForceSearch(int key, int[] list) {
    long startTime = System.currentTimeMillis();
    int rank = bruteForceSearch(key, list);
    long endTime = System.currentTimeMillis();

    StdOut.printf("bruteForceSearch(%s, ints) = %s\n", key, rank);
    StdOut.printf("Elapsed time: %sms\n", endTime - startTime);
  }

  private static int bruteForceSearch(int key, int[] list) {
    for (int i = 0; i < list.length; i++)
      if (list[i] == key)
        return i;

    return -1;
  }

  private static void runBinarySearch(int key, int[] list) {
    long startTime = System.currentTimeMillis();
    Arrays.sort(list);
    int rank = binarySearch(key, list);
    long endTime = System.currentTimeMillis();

    StdOut.printf("binarySearch(%s, ints) = %s\n", key, rank);
    StdOut.printf("Elapsed time: %sms\n", endTime - startTime);
  }

  private static int binarySearch(int key, int[] list) {
    int lo = 0;
    int hi = list.length - 1;

    while (lo <= hi) {
      int mid = lo + (hi - lo) / 2;
      if (key < list[mid]) hi = mid - 1;
      else if (key > list[mid]) lo = mid + 1;
      else return mid;
    }

    return -1;
  }
}