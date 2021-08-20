package algsex.chapter1.section4;

import java.util.*;
import edu.princeton.cs.algs4.*;

// 1.4.12 Write a program that, given two sorted arrays of N int values, prints all ele-
// ments that appear in both arrays, in sorted order. The running time of your program
// should be proportional to N in the worst case.
public class Exercise12 {
  public static void main(String[] args) {
    PrintIntersection.print(
      new int[] { 1, 1, 1, 2, 2, 3, 3, 4, 4, 5 ,5 ,5 },
      new int[] { 2, 2, 4, 4, 7, 7 ,7, 10, 10 }
    );

    StdOut.println("===");

    PrintIntersection.print(
      new int[] { 1, 1, 2, 3, 3, 5, 9, 14, 17, 23, 23 },
      new int[] { 3, 3, 8, 8, 8, 9, 13, 14, 14, 15, 18, 22, 25 }
    );
  }

  private static class PrintIntersection {
    private static void print(int[] a, int[] b) {
      int aIndex = 0;
      int bIndex = 0;

      while (aIndex < a.length && bIndex < a.length) {
        if (a[aIndex] < b[bIndex]) aIndex++;
        else if (b[bIndex] < a[aIndex]) bIndex++;
        else {
          int match = a[aIndex];

          do aIndex++; while (a[aIndex] == match);
          do bIndex++; while (b[bIndex] == match);

          StdOut.println(match);
        }
      }
    }
  }
}
