package algsex.chapter1.section2;

import java.lang.*;
import edu.princeton.cs.algs4.*;

// 1.2.10 Develop a class VisualCounter that allows both increment and decrement
// operations. Take two arguments N and max in the constructor, where N specifies the
// maximum number of operations and max specifies the maximum absolute value for
// the counter. As a side effect, create a plot showing the value of the counter each time its
// tally changes.
public class Exercise10 {
  public static void main(String[] args) {
    int maxOperations = Integer.parseInt(args[0]);
    int maxValue = Integer.parseInt(args[1]);
    In in = new In();

    VisualCounter counter = new VisualCounter(maxOperations, maxValue);

    try {
      while (!in.isEmpty()) {
        String line = in.readLine();

        switch(line) {
          case "i":
            counter.increment();
            break;

          case "d":
            counter.decrement();
            break;

          default:
            StdOut.printf("Invalid value \"%s\"\n", line);
        }
      }
    } finally {
      StdOut.println("asdf qwer");
      in.close();
    }
  }

  private static class VisualCounter {
    private int count = 0;
    private int operations = 0;
    private int maxOperations;
    private int maxValue;

    public VisualCounter(int maxOperations, int maxValue) {
      assert maxOperations > 0;
      assert maxValue > 0;

      this.maxOperations = maxOperations;
      this.maxValue = maxValue;

      setupStdDraw();
    }

    public void increment() {
      tryUpdateCount(count + 1);
    }

    public void decrement() {
      tryUpdateCount(count - 1);
    }

    private void tryUpdateCount(int newCount) {
      validate();
      count = newCount;
      operations++;
      plot();
    }

    private void plot() {
      StdDraw.filledRectangle(
        operations - 0.5,
        count / 2.0,
        0.25,
        Math.abs(count) / 2.0
      );
    }

    private void validate() {
      if (Math.abs(count) == maxValue)
        throw new RuntimeException("Counter has reached max value");

      if (operations == maxOperations)
        throw new RuntimeException("Counter has reached max operations");
    }

    private void setupStdDraw() {
      StdOut.println("maxOperations: " + maxOperations);
      StdOut.println("maxValue: " + maxValue);
      StdDraw.setCanvasSize(1024, 768);
      StdDraw.setXscale(0, maxOperations);
      StdDraw.setYscale(-maxValue, maxValue);
    }
  }
}
