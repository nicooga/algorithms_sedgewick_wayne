package algsex.chapter1.section4;

import java.util.*;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.ThreeSum;
import edu.princeton.cs.algs4.Stopwatch;
import edu.princeton.cs.algs4.Queue;
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
    public static void run() { // Print table of running times.
      for (int N = 3; N > 0.0 && N <= 3 * Math.pow(2, 4); N *= 2) { // Print time for problem size N.
        // StdOut.println("asdf");
        // double time = timeTrial(N);
        // StdOut.printf("%7d %5.1f\n", N, time);
        // StdOut.printf("f(%s) = %s\n", N, Math.pow(N, 2));
        StdOut.println("=====");
        StdOut.printf("Plotting value f(%s) = %s\n", N, Math.pow(N, 2));
        biGraph.addValue(N, Math.pow(N, 2));
        StdOut.println("=====");
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
    // Modify to tweak behaviour
    private final int[] WINDOW_SIZE = new int[] { 1920, 1080 };
    private final Point WINDOW_BOTTOM_LEFT_CORNER = new Point(0, 0);
    private final double PLOT_PADDING_RATIO = 0.1;
    private final double PLOT_SCALE_PADDING = 50;
    private final double NOTCH_LENGTH = 10;

    // Derivated constants. Not intended to be modified to weak behaviour.
    private final double[] PLOT_SIZE = new double[] { WINDOW_SIZE[0] / 2.0, WINDOW_SIZE[1] };
    private final double PLOT_HORIZONTAL_PADDING = PLOT_SIZE[0] * PLOT_PADDING_RATIO;
    private final double PLOT_VERTICAL_PADDING = PLOT_SIZE[1] * PLOT_PADDING_RATIO;
    private final double X_AXIS_LENGTH = PLOT_SIZE[0] - PLOT_HORIZONTAL_PADDING * 2 - PLOT_SCALE_PADDING;
    private final double Y_AXIS_LENGTH = PLOT_SIZE[1] - PLOT_VERTICAL_PADDING * 2 - PLOT_SCALE_PADDING;
    private final Point LOG_LOG_PLOT_BOTTOM_LEFT_CORNER =
      WINDOW_BOTTOM_LEFT_CORNER
        .translateX(PLOT_HORIZONTAL_PADDING)
        .translateY(PLOT_VERTICAL_PADDING);
    private final Point STANDARD_PLOT_BOTTOM_LEFT_CORNER =
      WINDOW_BOTTOM_LEFT_CORNER
        .translateX(PLOT_SIZE[0] + PLOT_HORIZONTAL_PADDING)
        .translateY(PLOT_VERTICAL_PADDING);

    private final StdDraw stdDraw = new StdDraw();
    private final Queue<Value> values = new Queue<>();
    private double maxX = 16;
    private double maxY = 16;
    private Plot logLogPlot;
    private Plot standardPlot;

    public BiGraph() {
      stdDraw.setCanvasSize(WINDOW_SIZE[0], WINDOW_SIZE[1]);
      stdDraw.setXscale(0, WINDOW_SIZE[0]);
      stdDraw.setYscale(0, WINDOW_SIZE[1]);

      this.logLogPlot = new LogLogPlot();
      logLogPlot.initialize(LOG_LOG_PLOT_BOTTOM_LEFT_CORNER);
      this.standardPlot = new StandardPlot();
      standardPlot.initialize(STANDARD_PLOT_BOTTOM_LEFT_CORNER);

      logLogPlot.draw();
      standardPlot.draw();
    }

    public void addValue(double x, double y) {
      stdDraw.clear();

      Value value = new Value();
      value.x = x;
      value.y = y;

      values.enqueue(value);

      logLogPlot.addValue(value);
      StdOut.println("==");
      standardPlot.addValue(value);
    }

    private abstract class Plot {
      Point bottomLeftCorner;
      Point origin;
      double maxX = 16;
      double maxY = 16;
      int horizontalNotchesCount;
      int verticalNotchesCount;

      public void initialize(Point bottomLeftCorner) {
        setBottomLeftCorner(bottomLeftCorner);
      }

      public void setBottomLeftCorner(Point bottomLeftCorner) {
        this.bottomLeftCorner = bottomLeftCorner;
        this.origin = bottomLeftCorner.translateXY(PLOT_SCALE_PADDING, PLOT_SCALE_PADDING);
      }

      public void addValue(Value value) {
        boolean needsRedraw = false;

        if (value.x > maxX) {
          setMaxX(value.x);
          needsRedraw = true;
        }

        if (value.y > maxY) {
          setMaxY(value.y);
          needsRedraw = true;
        }
        
        if (needsRedraw) {
          draw();
          for (Value v : values) drawValue(v);
        }
        else drawValue(value);
      }

      public void draw() {
        drawAxes();
        drawXScale();
        drawYScale();
        for (Value v : values ) drawValue(v);
      }

      private void drawAxes() {
        drawXAxis();
        drawYAxis();
      }

      private void drawXAxis() {
        Point axisEnd = origin.translateX(X_AXIS_LENGTH);
        stdDraw.drawLine(origin, axisEnd);
      }

      private void drawYAxis() {
        Point axisEnd = origin.translateY(Y_AXIS_LENGTH);
        stdDraw.drawLine(origin, axisEnd);
      }

      protected abstract void drawValue(Value value);
      protected abstract void drawXScale();
      protected abstract void drawYScale();
      protected abstract void setMaxX(double value);
      protected abstract void setMaxY(double value);
    }

    protected class LogLogPlot extends Plot {
      protected void drawValue(Value value) {
        assert value.x <= maxX;
        assert value.y <= maxY;

        Point circleCenter =
          origin.translateXY(
            X_AXIS_LENGTH / lg(maxX) * lg(value.x),
            Y_AXIS_LENGTH / lg(maxY) * lg(value.y)
          );

        assert circleCenter.getX() <= origin.getX() + X_AXIS_LENGTH;
        assert circleCenter.getY() <= origin.getY() + Y_AXIS_LENGTH;

        stdDraw.drawCircle(circleCenter, 2);
      }

      protected void drawXScale() {
        double notchesCount = Math.ceil(lg(maxX));
        double notchesSpacing = X_AXIS_LENGTH / (notchesCount - 1);

        for (int i = 0; i < notchesCount; i++) {
          Point notchStart = origin.translateX(i * notchesSpacing);
          Point notchEnd = notchStart.translateY(-NOTCH_LENGTH);

          stdDraw.drawLine(notchStart, notchEnd);

          String notchLabel = i > 13 ? String.format("2^%d", i) : String.format("%.0f", Math.pow(2, i + 1));
          double verticalOffset = i % 2 == 0 ? -PLOT_HORIZONTAL_PADDING / 3 : -2 * PLOT_HORIZONTAL_PADDING / 3;

          stdDraw.drawText(
            notchEnd.translateY(verticalOffset),
            notchLabel
          );
        }
      }

      protected void drawYScale() {
        double notchesCount = Math.ceil(lg(maxY));
        double notchesSpacing = Y_AXIS_LENGTH / (notchesCount - 1);

        for (int i = 0; i < notchesCount; i++) {
          Point notchStart = origin.translateY(i * notchesSpacing);
          Point notchEnd = notchStart.translateX(-NOTCH_LENGTH);

          stdDraw.drawLine(notchStart, notchEnd);

          String notchLabel = i > 13 ? String.format("2^%d", i) : String.format("%.0f", Math.pow(2, i + 1));
          double horizontalOffset = i % 2 == 0 ? -PLOT_HORIZONTAL_PADDING / 3 : -2 * PLOT_HORIZONTAL_PADDING / 3;

          stdDraw.drawText(
            notchEnd.translateX(horizontalOffset),
            notchLabel
          );
        }
      }

      protected void setMaxX(double value) {
        this.maxX = Math.pow(2, Math.ceil(lg(value)));
      }

      protected void setMaxY(double value) {
        this.maxY = Math.pow(2, Math.ceil(lg(value)));
      }
    }

    private class StandardPlot extends Plot {
      protected void drawValue(Value value) { }
      protected void drawXScale() { }
      protected void drawYScale() { }

      protected void setMaxX(double value) {
        // Math.pow(10, Math.ceil())
      }

      protected void setMaxY(double value) { }
    }

    private class Value {
      double x;
      double y;

      public String toString() {
        return String.format("y = f(%s) = %s", x, y);
      }
    }

    private double lg(double value) {
      return log(2, value);
    }
    
    private double log(int base, double value) {
      return Math.log(value) / Math.log(base);
    }
  }
}
