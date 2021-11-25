package algsex.chapter1.section3;

import java.util.Iterator;
import java.util.Arrays;
import edu.princeton.cs.algs4.*;
import algsex.support.Test;

// 1.3.16 Using readInts() on page 126 as a model, write a static method readDates() for
// Date that reads dates from standard input in the format specified in the table on page 119
// and returns an array containing them.
public class Exercise16 {
  public static void main(String[] args) {
    Date[] dates = readDates();

    StdOut.println(Arrays.toString(dates));
  }

  public static void test() {
    Test.simulateInput("5/22/1939\n5/23/1923");
    Date[] dates = readDates();
    Test.assertTrue(dates[0].equals(new Date(5, 22, 1939)));
    Test.assertTrue(dates[1].equals(new Date(5, 23, 1923)));
  }

  private static Date[] readDates() {
    Queue<Date> queue = new Queue<>();

    while(!StdIn.isEmpty()) {
      Date date = parseDate(StdIn.readLine());
      queue.enqueue(date);
    }

    Date[] array = new Date[queue.size()];

    Iterator<Date> iterator = queue.iterator();

    for(int i = 0; i < array.length; i++)
      array[i] = queue.dequeue();

    return array;
  }

  private static Date parseDate(String str) {
    String[] fields = str.split("/");

    int month = Integer.parseInt(fields[0]);
    int day = Integer.parseInt(fields[1]);
    int year = Integer.parseInt(fields[2]);

    return new Date(month, day, year);
  }
}
