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

    drawHistogram();
  }

  private

  static final int[] CANVAS_SIZE = { 2000, 1000 };
  static final double GRAPH_SIZE_PERCENTAGE = 0.8;
  static final double[] GRAPH_SIZE = {
    CANVAS_SIZE[0] * GRAPH_SIZE_PERCENTAGE,
    CANVAS_SIZE[1] * GRAPH_SIZE_PERCENTAGE
  };
  static final double GRAPH_LEFT_X = CANVAS_SIZE[0] * (1 - GRAPH_SIZE_PERCENTAGE) / 2.0;
  static final double GRAPH_RIGHT_X = CANVAS_SIZE[0] - CANVAS_SIZE[0] * (1 - GRAPH_SIZE_PERCENTAGE) / 2.0;
  static final double GRAPH_BOTTOM_Y = CANVAS_SIZE[1] * (1 - GRAPH_SIZE_PERCENTAGE) / 2.0;
  static final double GRAPH_TOP_Y = CANVAS_SIZE[1] * (GRAPH_SIZE_PERCENTAGE + 1) / 2.0;
  static final Point GRAPH_LEFT_BOTTOM_CORNER = new Point(GRAPH_LEFT_X, GRAPH_BOTTOM_Y);
  static final Point GRAPH_RIGHT_BOTTOM_CORNER = new Point(GRAPH_RIGHT_X, GRAPH_BOTTOM_Y);
  static final Point GRAPH_LEFT_TOP_CORNER = new Point(GRAPH_LEFT_X, GRAPH_TOP_Y);

  static final double BAR_PERCENTAGE_WIDTH = 75.0;
  static final double SCALE_MARK_WIDTH = 10.0;

  static int N;
  static double l;
  static double r;
  static int maxBucketSize = 0;
  static int[] buckets;
  static int maxDisplayValue;

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
    drawScaleMarks();
    drawBars();
  }

  static void setCanvasSizeAndScale() {
    StdDraw.setCanvasSize(CANVAS_SIZE[0], CANVAS_SIZE[1]); // Order is important here, set canvas size first
    StdDraw.setXscale(0, CANVAS_SIZE[0]);
    StdDraw.setYscale(0, CANVAS_SIZE[1]);
  }

  static void drawAxes() {
    drawLine(GRAPH_LEFT_BOTTOM_CORNER, GRAPH_RIGHT_BOTTOM_CORNER);
    drawLine(GRAPH_LEFT_BOTTOM_CORNER, GRAPH_LEFT_TOP_CORNER);
  }

  static void drawScaleMarks() {
    maxDisplayValue = (int) Math.ceil(maxBucketSize / 10.0) * 10;

    int numberOfMarks = maxDisplayValue / 5;

    for (int i = 0; i <= numberOfMarks; i++) {
      int markValue = (maxDisplayValue / numberOfMarks) * i;

      Point markStartPoint =
        GRAPH_LEFT_BOTTOM_CORNER.translateY(
          (GRAPH_SIZE[1] / (double) numberOfMarks) * i
        );

      Point markEndPoint = markStartPoint.translateX(-SCALE_MARK_WIDTH);

      drawLine(markStartPoint, markEndPoint);
      drawText(markEndPoint.translateX(-20), Integer.toString(markValue));
    }
  }

  static void drawBars() {
    for (int bucketIndex = 0; bucketIndex < buckets.length; bucketIndex++) {
      drawBar(bucketIndex);
      drawLabel(bucketIndex);
    }
  }

  static void drawBar(int bucketIndex) {
    double barHeight = barHeight(bucketIndex);
    double hw = segmentWidth() * BAR_PERCENTAGE_WIDTH/100.0 / 2;
    double hr = barHeight/2;

    StdDraw.setPenColor(StdDraw.GREEN);
    drawFilledRectangle(barHorizontalCenter(bucketIndex), hw, hr);
  }

  static void drawLabel(int bucketIndex) {
    drawText(
      barHorizontalCenter(bucketIndex).translateY(-5),
      bucketLabel(bucketIndex)
    );
  }

  static String bucketLabel(int bucketIndex) {
    double intevalStart = bucketIndex * bucketIntervalSize();
    double intervalEnd = (bucketIndex + 1) * bucketIntervalSize();
    StdDraw.setPenColor(StdDraw.BLACK);
    return String.format("[%s, %s)", intevalStart, intervalEnd);
  }

  static double bucketIntervalSize() {
    return (l + r) / N;
  }

  static Point barHorizontalCenter(int bucketIndex) {
    return GRAPH_LEFT_BOTTOM_CORNER.translateXY(
      segmentWidth() * (bucketIndex + 1/2.0),
      barHeight(bucketIndex) / 2.0
    );
  }

  static double segmentWidth() {
    return GRAPH_SIZE[0] / buckets.length;
  }

  static double barHeight(int bucketIndex) {
    int bucketSize = buckets[bucketIndex];
    return (GRAPH_SIZE[1] * bucketSize) / maxDisplayValue;
  }

  static void drawLine(Point pointA, Point pointB) {
    StdDraw.line(pointA.getX(), pointA.getY(), pointB.getX(), pointB.getY());
  }

  static void drawText(Point point, String text) {
    StdDraw.text(point.getX(), point.getY(), text);
  }

  static void drawFilledRectangle(Point point, double hw, double hr) {
    StdDraw.filledRectangle(point.getX(), point.getY(), hw, hr);
  }
}