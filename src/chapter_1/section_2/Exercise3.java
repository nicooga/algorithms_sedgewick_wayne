package algsex.chapter1.section2;

import java.lang.*;
import edu.princeton.cs.algs4.*;
import algsex.chapter1.section2.exercise_3.*;

// 1.2.3 Write an Interval2D client that takes command-line arguments N, min, and max
// and generates N random 2D intervals whose width and height are uniformly distributed
// between min and max in the unit square. Draw them on StdDraw and print the number
// of pairs of intervals that intersect and the number of intervals that are contained in one
// another.
public class Exercise3 {
  public static void main(String[] args) {
    int N = Integer.parseInt(args[0]);
    double min = Double.parseDouble(args[1]);
    double max = Double.parseDouble(args[2]);

    assert max > min;

    ColoredInterval2D[] intervals = generateRandomIntervals(N, min, max);
    drawIntervals(intervals, min, max);
    printIntersectionAndContainmentCount(intervals);
  }

  private static ColoredInterval2D[] generateRandomIntervals(int N, double min, double max) {
    ColoredInterval2D[] intervals = new ColoredInterval2D[N];

    for (int i = 0; i < intervals.length; i++)
      intervals[i] = generateRandomInterval2D(min, max);

    return intervals;
  }

  private static ColoredInterval2D generateRandomInterval2D(double min, double max) {
    return new ColoredInterval2D(
      generateRandomInterval1D(min, max),
      generateRandomInterval1D(min, max)
    );
  }

  private static Interval1D generateRandomInterval1D(double min, double max) {
    double x1 = StdRandom.uniform(min, max) ;
    double x2 = StdRandom.uniform(min, max);

    if (x2 > x1) return new Interval1D(x1, x2);
    else return new Interval1D(x2, x1);
  }

  private static void drawIntervals(ColoredInterval2D[] intervals, double min, double max) {
    StdDraw.setCanvasSize(1024, 768);
    StdDraw.setXscale(min, max);
    StdDraw.setYscale(min, max);

    for (int i = 0; i < intervals.length; i++) {
      StdDraw.setPenColor(intervals[i].color);
      intervals[i].draw();
    }
  }

  private static int printIntersectionAndContainmentCount(ColoredInterval2D[] intervals) {
    int intersectionCount = 0;
    int containmentCount = 0;

    for (int i = 0; i < intervals.length; i++)
      for (int j = i+1; j < intervals.length; j++) {
        ColoredInterval2D lhs = intervals[i];
        ColoredInterval2D rhs = intervals[j];

        if (lhs.contains(rhs)) {
          StdOut.printf("%s ...\n  ... contains %s\n\n", lhs, rhs);
          containmentCount++;
        }

        if (lhs.intersects(rhs)) {
          StdOut.printf("%s ...\n  ... intersects %s\n\n", lhs, rhs);
          intersectionCount++;
        }
      }

    StdOut.println("intersection count: " + intersectionCount);
    StdOut.println("containment count: " + containmentCount);

    return intersectionCount;
  }
}
