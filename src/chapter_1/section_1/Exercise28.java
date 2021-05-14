package algsex.chapter1.section1;

import java.util.*;
import edu.princeton.cs.algs4.*;

public class Exercise28 {
  public

  static void main(String[] args) {
    int[] array = new int[args.length];

    for (int i = 0; i < args.length; i++)
      array[i] = Integer.parseInt(args[i]);

    Arrays.sort(array);

    array = removeDuplicates(array);

    StdOut.println(Arrays.toString(array));
  }

  private

  static int[] removeDuplicates(int[] array) {
    int[] newArray = new int[array.length];
    int lastCopiedIndex = 0;

    newArray[0] = array[0];

    for (int i = 1; i < array.length; i++) {
      int originalEl = array[i];
      int lastCopiedEl = newArray[lastCopiedIndex];

      if (originalEl != lastCopiedEl) {
        newArray[lastCopiedIndex + 1] = originalEl;
        lastCopiedIndex++;
      }
    }

    return truncate(newArray, lastCopiedIndex + 1);
  }

  static int[] truncate(int[] array, int newSize) {
    int[] newArray = new int[newSize];
    for (int i = 0; i < newSize; i++) newArray[i] = array[i];
    return newArray;
  }
}