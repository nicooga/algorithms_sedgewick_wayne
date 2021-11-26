package algsex.chapter1.section4;

import java.util.Arrays;
import edu.princeton.cs.algs4.*;
import algsex.support.Test;

// 1.4.8 Write a program to determine the number pairs of values in an input file that
// are equal. If your first try is quadratic, think again and use Arrays.sort() to develop
// a linearithmic solution.
public class Exercise8 {
  public static void main(String[] args) {
    if (StdIn.isEmpty()) runTests();
    else countFromStdIn();
  }

  private static void countFromStdIn() {
    Queue<String> queue = new Queue<>();

    while (!StdIn.isEmpty()) queue.enqueue(StdIn.readLine());

    String[] array = new String[queue.size()];

    for (int i = 0; !queue.isEmpty(); i++) array[i] = queue.dequeue();

    StdOut.println(CountEqualPairs.count(array));
  }

  private static void runTests() {
    Test.assertEqual(CountEqualPairs.count(new String[] { "asdf", "asdf", "qwer" }), 2);
    Test.assertEqual(CountEqualPairs.count(new String[] { "qwer", "qwer", "qwer" }), 3);
  }

  private static class CountEqualPairs {
    public static int count(String[] a) {
      String tempLine = a[0];
      int count = 0;

      Arrays.sort(a);

      for (int i = 0; i < a.length; i++) {
        if (i < a.length-1 && a[i] == a[i+1]) count++;
        else if (i > 0 && a[i] == a[i-1]) count++;
      }

      return count;
    }
  }
}
