package algsex.chapter1.section2;

import java.util.*;
import java.lang.*;
import edu.princeton.cs.algs4.*;

// 1.2.18 Variance for accumulator. Validate that the following code, which adds the
// methods var() and stddev() to Accumulator, computes both the sample mean, sample
// variance, and sample standard deviation of the numbers presented as arguments to
// addDataValue():
public class Exercise18 {
  public static void main(String[] args) {
    Accumulator acc = new Accumulator();

    Runtime.getRuntime().addShutdownHook(new Thread() {
      public void run() { StdOut.println(acc); }
    });

    while(!StdIn.isEmpty()) {
      String value = StdIn.readLine();
      acc.addDataValue(Double.parseDouble(value));
    }
  }

  private static class Accumulator {
    private double m;
    private double s;
    private int N;

    public void addDataValue(double x) {
      StdOut.println("===");
      StdOut.printf("N: %d, s: %f, m: %f\n", N, s, m);
      N++;
      s = s + 1.0 * (N-1) / N * (x - m) * (x - m);
      m = m + (x - m) / N;
      StdOut.printf("N: %d, s: %f, m: %f\n", N, s, m);
      StdOut.println("===");
    }

    public double mean() { return m; }
    public double var() { return s/(N - 1); }
    public double stddev() { return Math.sqrt(this.var()); }

    public String toString() {
      return String.format(
        "mean: %f, var: %f, stddev: %f\n",
        mean(), var(), stddev()
      );
    }
  }
}
