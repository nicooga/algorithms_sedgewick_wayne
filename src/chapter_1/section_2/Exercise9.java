package algsex.chapter1.section2;

import java.util.*;
import edu.princeton.cs.algs4.*;

// 1.2.9 Instrument BinarySearch (page 47) to use a Counter to count the total number
// of keys examined during all searches and then print the total after all searches are com-
// plete. Hint: Create a Counter in main() and pass it as an argument to rank().
public class Exercise9 {
  private static Counter counter = new Counter("visited indexes");

  public static void main(String[] args) {
    int N = Integer.parseInt(args[0]);
    int[] ints = readIntsFromStdIn();
    Arrays.sort(ints);
    StdOut.println("Sorted array: " + Arrays.toString(ints));
    StdOut.println("Result: " + binarySearch(N, ints));
    StdOut.println("Examined keys count: " + counter.tally());
  }

  private static int[] readIntsFromStdIn() {
    List<Integer> list = new ArrayList<>();
    while(!StdIn.isEmpty()) list.add((Integer) StdIn.readInt());
    return list.stream().mapToInt(i -> i).toArray();
  }

  private static int binarySearch(int key, int[] list) {
    int lo = 0;
    int hi = list.length - 1;

    while (lo <= hi) {
      int mid = lo + (hi - lo) / 2;
      counter.increment();
      if (key < list[mid]) hi = mid - 1;
      else if (key > list[mid]) lo = mid + 1;
      else return mid;
    }

    return -1;
  }
}
