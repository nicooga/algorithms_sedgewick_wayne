package algsex.chapter1.section4;

import java.util.Arrays;
import edu.princeton.cs.algs4.*;

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
    assert CountEqualPairs.count(new String[] { "asdf", "asdf", "qwer" }) == 1;
    assert CountEqualPairs.count(new String[] { "qwer", "qwer", "qwer" }) == 3;
    StdOut.println("Tests passed");
  }

  private static class CountEqualPairs {
    public static int count(String[] a) {
      String tempLine = a[0];
      int count = 0;

      Arrays.sort(a);

      for (int i = 1; i < a.length; i++) {
        String line = a[i];

        if (line.equals(tempLine)) count++;
        else tempLine = line;
      }

      return count;
    }
  }
}
