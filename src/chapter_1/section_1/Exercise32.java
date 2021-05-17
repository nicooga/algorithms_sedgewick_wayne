package algsex.chapter1.section1;

import java.util.*;
import java.lang.*;
import edu.princeton.cs.algs4.*;

public class Exercise32 {
  public static void main(String[] args) {
    N = Integer.parseInt(args[0]);
    l = Double.parseDouble(args[1]);
    r = Double.parseDouble(args[2]);

    Double[] values = readValues();

    bucketValues(values);

    StdOut.println("buckets: " + Arrays.toString(buckets));

    drawHistogram();
  }

  private

  static final int[] CANVAS_SIZE = { 800, 600 };
  static final double GRAPH_SIZE_PERCENTAGE = 0.8;
  static final double BAR_WIDTH = 20.0;
  static final double BAR_SPACING = 30.0;
  static final double SCALE_MARK_WIDTH = 5.0;

  static int N;
  static double l;
  static double r;
  static int maxBucketSize = 0;
  static int[] buckets;

  static Double[] readValues() {
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
    // drawScale(); // DEBUG
    // drawBars(); // DEBUG
  }

  static void setCanvasSizeAndScale() {
    StdDraw.setXscale(0, CANVAS_SIZE[0]);
    StdDraw.setYscale(0, CANVAS_SIZE[1]);
    StdDraw.setCanvasSize(CANVAS_SIZE[0], CANVAS_SIZE[1]);
  }

  static void drawAxes() {
    double leftX = CANVAS_SIZE[0] * (1 - GRAPH_SIZE_PERCENTAGE) / 2.0;
    double rightX = CANVAS_SIZE[0] - CANVAS_SIZE[0] * (1 - GRAPH_SIZE_PERCENTAGE) / 2.0;
    double bottomY = CANVAS_SIZE[1] * (1 - GRAPH_SIZE_PERCENTAGE) / 2.0;
    double topY = CANVAS_SIZE[1] * (GRAPH_SIZE_PERCENTAGE + 1) / 2.0;

    double[] leftBottomCorner = { leftX, bottomY };
    double[] rightBottomCorner = { rightX, bottomY };
    double[] leftTopCorner = { leftX, topY };

    drawLine(leftBottomCorner, rightBottomCorner);
    drawLine(leftBottomCorner, leftTopCorner);
  }

  static void drawScale() {
    int closestTenMultiple = (int) Math.ceil(maxBucketSize / 10.0) * 10;
    int numberOfMarks = closestTenMultiple / 10;

    double leftX = CANVAS_SIZE[0] * (1 - GRAPH_SIZE_PERCENTAGE) / 2.0;
    double bottomY = CANVAS_SIZE[1] * (1 - GRAPH_SIZE_PERCENTAGE) / 2.0;

    for (int i = 0; i <= numberOfMarks; i++) {
      double[] markStartPoint = {
        leftX,
        bottomY + (CANVAS_SIZE[1] / (double) numberOfMarks) * i
      };

      double[] markEndPoint = {
        markStartPoint[0] - SCALE_MARK_WIDTH,
        markStartPoint[1]
      };

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

  static void drawLine(double[] pointA, double[] pointB) {
    assert pointA.length == 2;
    assert pointB.length == 2;

    StdDraw.line(pointA[0], pointA[1], pointB[0], pointB[1]);
  }
}