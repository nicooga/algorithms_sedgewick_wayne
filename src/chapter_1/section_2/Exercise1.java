package algsex.chapter1.section2;

import edu.princeton.cs.algs4.*;

// 1.2.1 Write a Point2D client that takes an integer value N from the command line,
// generates N random points in the unit square, and computes the distance separating
// the closest pair of points.

// This solution has O(n^2) complexity.
public class Exercise1 {
  public static void main(String[] args) {
    int N = Integer.parseInt(args[0]);
    Point2D[] points = generateRandomPoints(N);
    StdOut.println(getClosestDistance(points));
  }

  private static Point2D[] generateRandomPoints(int N) {
    Point2D[] points = new Point2D[N];
    for (int i = 0; i < N; i++) points[i] = generateRandomPoint();
    return points;
  }

  private static Point2D generateRandomPoint() {
    return new Point2D(StdRandom.uniform(), StdRandom.uniform());
  }

  private static double getClosestDistance(Point2D[] points) {
    double minDistance = -1.0;

    for (int i = 0; i < points.length - 1; i++) {
      for (int j = i + 1; j < points.length; j++) {
        if (i == j) continue;

        double distance = points[i].distanceTo(points[j]);

        if (minDistance == -1.0 || distance < minDistance)
          minDistance = distance;
      }
    }

    return minDistance;
  }
}