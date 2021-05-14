package algsex.chapter1.section1;

import java.lang.*;
import edu.princeton.cs.algs4.*;

// 1.1.31 Random connections. Write a program that takes as command-line arguments
// an integer N and a double value p (between 0 and 1), plots N equally spaced dots of size
// .05 on the circumference of a circle, and then, with probability p for each pair of points,
// draws a gray line connecting them.
public class Exercise31 {
  public static void main(String[] args) {
    int N = Integer.parseInt(args[0]);
    double p = Double.parseDouble(args[1]);

    double[][] points = calculatePoints(N);

    setScale();
    drawCircle();
    drawLines(points, p);
    drawDots(points);
  }

  private

  static final double PADDING=0.1;
  static final double CIRCLE_BORDER_WIDTH = 0.01;
  static final double CIRCLE_RADIUS = 32.0;
  static final double DOT_RADIUS = 0.05;
  static final double LINE_RADIUS = 0.01;
  static final java.awt.Color LINE_COLOR = StdDraw.GRAY;

  static void setScale() {
    double usedRadius = CIRCLE_RADIUS + DOT_RADIUS + PADDING;

    StdDraw.setXscale(-usedRadius, usedRadius);
    StdDraw.setYscale(-usedRadius, usedRadius);
  }

  static void drawCircle() {
    StdDraw.setPenRadius(CIRCLE_BORDER_WIDTH);
    StdDraw.circle(0, 0, CIRCLE_RADIUS);
  }

  static double[][] calculatePoints(int N) {
    double circleDiameter = 2 * Math.PI * CIRCLE_RADIUS;
    double[][] points = new double[N][2];

    for (int i = 0; i < N; i++) {
      double L = circleDiameter * i/N;
      double phi = L / CIRCLE_RADIUS;
      double x = Math.cos(phi) * CIRCLE_RADIUS;
      double y = Math.sin(phi) * CIRCLE_RADIUS;

      points[i][0] = x;
      points[i][1] = y;
    }

    return points;
  }

  static void drawLines(double[][] points, double p) {
    for (int i = 0; i < points.length; i++) {
      double[] pointA = points[i];

      for (int j = 0; j < points.length; j++) {
        double[] pointB = points[j];
        drawLine(pointA, pointB, p);
      }
    }
  }

  static void drawLine(double[] pointA, double[] pointB, double p) {
    if (p == 0.0) return;
    if (StdRandom.bernoulli(p)) drawLine(pointA, pointB);
  }

  static void drawLine(double[] pointA, double[] pointB) {
    StdDraw.setPenRadius(LINE_RADIUS);
    StdDraw.setPenColor(LINE_COLOR);
    StdDraw.line(pointA[0], pointA[1], pointB[0], pointB[1]);
  }

  static void drawDots(double[][] points) {
    for (int i = 0; i < points.length; i++) drawDot(points[i]);
  }

  static void drawDot(double[] point) {
    StdDraw.setPenRadius(DOT_RADIUS);
    StdDraw.point(point[0], point[1]);
  }
}