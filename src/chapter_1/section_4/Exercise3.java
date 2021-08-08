package algsex.chapter1.section4;

import java.util.*;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.ThreeSum;
import edu.princeton.cs.algs4.Stopwatch;
import algsex.support.Point;
import algsex.support.StdDraw;

// 1.4.3 Modify DoublingTest to use StdDraw to produce plots like the standard and
// log-log plots in the text, rescaling as necessary so that the plot always fills a substantial
// portion of the window.
public class Exercise3 {
  private static BiGraph biGraph = new BiGraph();

  public static void main(String[] args) {
    DoublingTest.run();
  }

  private static class DoublingTest {
    double max = 16.0;

    public static void run() { // Print table of running times.
      for (int N = 10; N == 10; N *= 2) { // Print time for problem size N.
        // StdOut.println("asdf");
        // double time = timeTrial(N);
        // StdOut.printf("%7d %5.1f\n", N, time);
        // StdOut.printf("f(%s) = %s\n", N, Math.pow(N, 2));
        biGraph.plotValue(N, Math.pow(N, 2));
      }
    }

    private static double timeTrial(int N) { // Time ThreeSum.count() for N random 6-digit ints.
      int MAX = 1000000;
      int[] a = new int[N];

      for (int i = 0; i < N; i++) a[i] = StdRandom.uniform(-MAX, MAX);

      Stopwatch timer = new Stopwatch();
      int cnt = ThreeSum.count(a);

      return timer.elapsedTime();
    }
  }

  private static class BiGraph {
    private final int[] WINDOW_SIZE = new int[] { 1920, 1080 };
    private final Point WINDOW_BOTTOM_LEFT_CORNER = new Point(0, 0);
    private final double PLOT_PADDING_RATIO = 0.1;
    private final double PLOT_SCALE_PADDING = 50;
    private final double NOTCH_LENGTH = 10;

    private final double[] PLOT_SIZE = new double[] { WINDOW_SIZE[0] / 2.0, WINDOW_SIZE[1] };
    private final double PLOT_HORIZONTAL_PADDING = PLOT_SIZE[0] * PLOT_PADDING_RATIO;
    private final double PLOT_VERTICAL_PADDING = PLOT_SIZE[1] * PLOT_PADDING_RATIO;
    private final double X_AXIS_LENGTH = PLOT_SIZE[0] - PLOT_HORIZONTAL_PADDING * 2 - PLOT_SCALE_PADDING;
    private final double Y_AXIS_LENGTH = PLOT_SIZE[1] - PLOT_VERTICAL_PADDING * 2 - PLOT_SCALE_PADDING;

    private final StdDraw stdDraw = new StdDraw();
    private int maxValue = 16;

    public BiGraph() {
      stdDraw.setCanvasSize(WINDOW_SIZE[0], WINDOW_SIZE[1]);
      stdDraw.setXscale(0, WINDOW_SIZE[0]);
      stdDraw.setYscale(0, WINDOW_SIZE[1]);
    }

    public void plotValue(double x, double fX) {
      stdDraw.clear();
      drawPlots();
    }

    private void drawPlots() {
      Point logLogPlotBottomLeftCorner =
        WINDOW_BOTTOM_LEFT_CORNER
          .translateX(PLOT_HORIZONTAL_PADDING)
          .translateY(PLOT_VERTICAL_PADDING);

      Point standardPlotBottomLeftCorner =
        WINDOW_BOTTOM_LEFT_CORNER
          .translateX(PLOT_SIZE[0] + PLOT_HORIZONTAL_PADDING)
          .translateY(PLOT_VERTICAL_PADDING);

      drawPlot(logLogPlotBottomLeftCorner);
      drawPlot(standardPlotBottomLeftCorner);
    }

    private void drawPlot(Point bottomLeftCorner) {
      Point origin = bottomLeftCorner.translateXY(PLOT_SCALE_PADDING, PLOT_SCALE_PADDING);

      drawAxes(origin);
      drawScale(origin);
    }

    private void drawAxes(Point origin) {
      drawXAxis(origin);
      drawYAxis(origin);
    }

    private void drawXAxis(Point origin) {
      Point axisEnd = origin.translateX(X_AXIS_LENGTH);
      stdDraw.drawLine(origin, axisEnd);
    }

    private void drawYAxis(Point origin) {
      Point axisEnd = origin.translateY(Y_AXIS_LENGTH);
      stdDraw.drawLine(origin, axisEnd);
    }

    private void drawScale(Point origin) {
      double notchesCount = lg(maxValue);
      double notchSpacing = X_AXIS_LENGTH / (notchesCount - 1);

      for (int i = 0; i < notchesCount; i++) {
        Point notchStart = origin.translateX(i * notchSpacing);
        Point notchEnd = notchStart.translateY(-NOTCH_LENGTH);

        stdDraw.drawLine(notchStart, notchEnd);

        String notchLabel = Integer.toString(i + 1);

        stdDraw.drawText(notchEnd.translateY(-PLOT_SCALE_PADDING / 2), notchLabel);
      }
    }

    private double lg(double value) {
      return Math.log(maxValue) / Math.log(2);
    }
  }
}
