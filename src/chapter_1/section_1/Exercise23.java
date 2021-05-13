package algsex.chapter1.section1;

import java.util.*;
import java.util.regex.*;
import edu.princeton.cs.algs4.*;

// 1.1.23 Add to the BinarySearch test client the ability to respond to a second argument:
// + to print numbers from standard input that are not in the whitelist, - to print
// numbers that are in the whitelist.
public class Exercise23 {
  public

  static void main(String[] args) {
    selectedBehaviour = args[0];
    whitelist = new int[args.length - 1];

    for (int i = 1; i < args.length; i++) {
      whitelist[i-1] = Integer.parseInt(args[i]);
    }

    Arrays.sort(whitelist);

    while (!StdIn.isEmpty()) {
      int key = StdIn.readInt();
      performStrategy(key);
    }
  }

  private

  static String selectedBehaviour;
  static int[] whitelist;

  static void performStrategy(int key) {
    switch(selectedBehaviour) {
      case "+":
        printNotWhitelisted(key);
        break;
      case "-":
        printWhitelisted(key);
        break;
      default:
        throw new Error(String.format("Unknown selectedBehaviour %s", selectedBehaviour));
    }
  }

  static void printNotWhitelisted(int key) {
    if (!isWhitelisted(key)) StdOut.println(key);
  }

  static void printWhitelisted(int key) {
    if (isWhitelisted(key)) StdOut.println(key);
  }

  static boolean isWhitelisted(int key) {
    return rank(key, whitelist) != -1;
  }

  static int rank(int key, int[] a) {
    int lo = 0;
    int hi = a.length - 1;

    while (lo <= hi) {
      int mid = lo + (hi - lo) / 2;
      if (key < a[mid]) hi = mid - 1;
      else if (key > a[mid]) lo = mid + 1;
      else return mid;
    }

    return -1;
  }
}