package algsex.chapter1.section2;

import java.util.*;
import edu.princeton.cs.algs4.*;

// 1.2.2 Write an Interval1D client that takes an int value N as command-line argument,
// reads N intervals (each defined by a pair of double values) from standard input,
// and prints all pairs that intersect.
public class Exercise2 {
  public static void main(String[] args) {
    int N = Integer.parseInt(args[0]);

    Interval1D[] intervals = readIntervals(N);
    List<Interval1D[]> intersectingIntervals = findIntersectingIntervals(intervals);
    printIntersectingIntervals(intersectingIntervals);
  }

  private static Interval1D[] readIntervals(int N) {
    Interval1D[] intervals = new Interval1D[N];

    for (int i = 0; i < N; i++) {
      double min = StdIn.readDouble();
      double max = StdIn.readDouble();
      intervals[i] = new Interval1D(min, max);
    }

    return intervals;
  }

  private static List<Interval1D[]> findIntersectingIntervals(Interval1D[] intervals) {
    List<Interval1D[]> intersectingIntervals = new ArrayList<>();

    for (int i = 0; i < intervals.length; i++)
      for (int j = i + 1; j < intervals.length; j++)
        if (intervals[i].intersects(intervals[j]))
          intersectingIntervals.add(new Interval1D[] { intervals[i], intervals[j] });

    return intersectingIntervals;
  }

  private static void printIntersectingIntervals(List<Interval1D[]> intersectingIntervals) {
    Iterator<Interval1D[]> iterator = intersectingIntervals.iterator();
    while(iterator.hasNext()) StdOut.println(Arrays.toString(iterator.next()));
  }
}