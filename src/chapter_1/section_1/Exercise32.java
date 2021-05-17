package algsex.chapter1.section1;

import java.util.*;
import java.lang.*;
import edu.princeton.cs.algs4.*;
import algsex.support.Point;

public class Exercise32 {
  public static void main(String[] args) {
    N = Integer.parseInt(args[0]);
    l = Double.parseDouble(args[1]);
    r = Double.parseDouble(args[2]);

    // TODO: Remove intermediate step, read and bucket values in a single operation.
    Double[] values = readDoublesFromStdIn();
    bucketValues(values);

    StdOut.println("buckets: " + Arrays.toString(buckets));

    drawHistogram();
  }

  private

  static final int[] CANVAS_SIZE = { 800, 600 };
  static final double GRAPH_SIZE_PERCENTAGE = 0.8;
  static final double[] GRAPH_SIZE = {
    CANVAS_SIZE[0] * GRAPH_SIZE_PERCENTAGE,
    CANVAS_SIZE[1] * GRAPH_SIZE_PERCENTAGE
  };
  static final double BAR_WIDTH = 20.0;
  static final double BAR_SPACING = 30.0;
  static final double SCALE_MARK_WIDTH = 10.0;

  static int N;
  static double l;
  static double r;
  static int maxBucketSize = 0;
  static int[] buckets;

  static Double[] readDoublesFromStdIn() {
    ArrayList<Double> values = new ArrayList<>();
    while (!StdIn.isEmpty()) values.add(StdIn.readDouble());
    Double[] valuesArray = new Double[values.size()];
    return values.toArray(valuesArray);
  }

  static void bucketValues(Double[] values) {
    buckets = new int[N];

    for (int i = 0; i < values.length; i++) {
      double value = values[i];

      if (value < l || value >= r) continue;

      int bucketIndex = (int) Math.floor(value / bucketIntervalSize());

      buckets[bucketIndex]++;

      if (buckets[bucketIndex] > maxBucketSize) maxBucketSize = buckets[bucketIndex];
    }
  }

  static void drawHistogram() {
    setCanvasSizeAndScale();

    drawAxes();
    drawScale();
    // drawBars(); // DEBUG
  }

  static void setCanvasSizeAndScale() {
    StdDraw.setCanvasSize(CANVAS_SIZE[0], CANVAS_SIZE[1]); // Order is important here, set canvas size first
    StdDraw.setXscale(0, CANVAS_SIZE[0]);
    StdDraw.setYscale(0, CANVAS_SIZE[1]);
  }

  static void drawAxes() {
    double leftX = CANVAS_SIZE[0] * (1 - GRAPH_SIZE_PERCENTAGE) / 2.0;
    double rightX = CANVAS_SIZE[0] - CANVAS_SIZE[0] * (1 - GRAPH_SIZE_PERCENTAGE) / 2.0;
    double bottomY = CANVAS_SIZE[1] * (1 - GRAPH_SIZE_PERCENTAGE) / 2.0;
    double topY = CANVAS_SIZE[1] * (GRAPH_SIZE_PERCENTAGE + 1) / 2.0;

    Point leftBottomCorner = new Point(leftX, bottomY);
    Point rightBottomCorner = new Point(rightX, bottomY);
    Point leftTopCorner = new Point(leftX, topY);

    drawLine(leftBottomCorner, rightBottomCorner);
    drawLine(leftBottomCorner, leftTopCorner);
  }

  static void drawScale() {
    int closestTenMultiple = (int) Math.ceil(maxBucketSize / 10.0) * 10;
    int numberOfMarks = closestTenMultiple / 10;

    double leftX = CANVAS_SIZE[0] * (1 - GRAPH_SIZE_PERCENTAGE) / 2.0; // TODO: DRY up
    double bottomY = CANVAS_SIZE[1] * (1 - GRAPH_SIZE_PERCENTAGE) / 2.0; // TODO: DRY up

    for (int i = 0; i <= numberOfMarks; i++) {
      Point markStartPoint = new Point(
        leftX,
        bottomY + (GRAPH_SIZE[1] / (double) numberOfMarks) * i
      );

      Point markEndPoint = markStartPoint.translateX(-SCALE_MARK_WIDTH);
      drawLine(markStartPoint, markEndPoint);
    }
  }

  static void drawBars() {
    for (int bucketIndex = 0; bucketIndex < buckets.length; bucketIndex++) {
      int bucketSize = buckets[bucketIndex];
      drawBar(bucketIndex, bucketSize);
      drawLabel(bucketIndex);
    }
  }

  static void drawBar(int bucketIndex, int bucketSize) {
    double x = bucketBarHorizontalCenter(bucketIndex);
    double y = bucketSize / 2.0;
    double hw = BAR_WIDTH / 2.0;
    double hr = bucketSize / 2.0;

    StdDraw.setPenColor(StdDraw.BLACK);
    StdDraw.filledRectangle(x, y, hw, hr);
  }

  static void drawLabel(int bucketIndex) {
    double[] labelPosition = bucketLabelPosition(bucketIndex);
    String label = bucketLabel(bucketIndex);
    StdDraw.text(labelPosition[0], labelPosition[1], label);
  }

  static double[] bucketLabelPosition(int bucketIndex) {
    double x = bucketBarHorizontalCenter(bucketIndex);
    double y = bucketIndex % 2 == 0 ? -0.5 : -1.5;
    double[] position = { x, y };
    return position;
  }

  static String bucketLabel(int bucketIndex) {
    double intevalStart = bucketIndex * bucketIntervalSize();
    double intervalEnd = (bucketIndex + 1) * bucketIntervalSize();
    StdDraw.setPenColor(StdDraw.BLACK);
    return String.format("[%s, %s)", intevalStart, intervalEnd);
  }

  static double bucketBarHorizontalCenter(int bucketIndex) {
    return (bucketIndex + 0.5) * (BAR_WIDTH + BAR_SPACING * 2);
  }

  static double bucketIntervalSize() {
    return (l + r) / N;
  }

  static void drawLine(Point pointA, Point pointB) {
    StdOut.printf("pointA: %s, pointB: %s\n", pointA, pointB);
    StdDraw.line(pointA.getX(), pointA.getY(), pointB.getX(), pointB.getY());
  }
}