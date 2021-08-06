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
      for (int N = 10; N != 0.0; N *= 2) { // Print time for problem size N.
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
    private final int[] GRAPH_SIZE = new int[] { 1920, 1080 };
    private final Point GRAPH_BOTTOM_LEFT_CORNER = new Point(0, 0);
    private final double PLOT_PADDING_RATIO = 0.1;
    private final double NOTCH_LENGTH = 10;

    private final double PLOT_HORIZONTAL_PADDING = GRAPH_SIZE[0] * PLOT_PADDING_RATIO / 2;
    private final double PLOT_VERTICAL_PADDING = GRAPH_SIZE[1] * PLOT_PADDING_RATIO / 2;

    private final StdDraw stdDraw = new StdDraw();
    private int maxValue = 16;

    public BiGraph() {
      stdDraw.setCanvasSize(GRAPH_SIZE[0], GRAPH_SIZE[1]);
      stdDraw.setXscale(0, GRAPH_SIZE[0]);
      stdDraw.setYscale(0, GRAPH_SIZE[1]);
    }

    public void plotValue(double x, double fX) {
      stdDraw.clear();
      drawPlots();
    }

    private void drawPlots() {
      Point logLogPlotBottomLeftCorner =
        GRAPH_BOTTOM_LEFT_CORNER
          .translateX(PLOT_HORIZONTAL_PADDING)
          .translateY(PLOT_VERTICAL_PADDING);

      // Point standardPlotBottomLeftCorner = GRAPH_BOTTOM_LEFT_CORNER.translateX(GRAPH_SIZE[0] / 2 + PLOT_HORIZONTAL_PADDING);

      drawPlot(logLogPlotBottomLeftCorner);
      // drawPlot(standardPlotBottomLeftCorner);
    }

    private void drawPlot(Point bottomLeftCorner) {
      drawAxes(bottomLeftCorner);
      // drawScale(bottomLeftCorner);
    }

    private void drawAxes(Point bottomLeftCorner) {
      Point topLeftCorner = bottomLeftCorner.translateY(GRAPH_SIZE[1] - PLOT_VERTICAL_PADDING * 2);
      StdOut.println(bottomLeftCorner);
      StdOut.println(topLeftCorner);
      // Point bottomRightCorner = bottomLeftCorner.translateX(GRAPH_SIZE[0] - PLOT_HORIZONTAL_PADDING * 2);
      stdDraw.drawLine(bottomLeftCorner, topLeftCorner);
      // stdDraw.drawLine(bottomLeftCorner, bottomRightCorner);
    }

    // private void drawScale(Point bottomLeftCorner) {
    //   int notchesCount = (int) lg(maxValue);

    //   for (int i = 0; i < notchesCount; i++)
    //     stdDraw.drawLine(bottomLeftCorner, bottomLeftCorner.translateY(-NOTCH_LENGTH));
    // }

    // private double lg(double value) {
    //   return Math.log(maxValue) / Math.log(2);
    // }
  }
}
