package algsex.chapter1.section1;

import java.util.*;
import edu.princeton.cs.algs4.*;

public class Exercise27 {
  public static void main(String[] args) {
    int N = Integer.parseInt(args[0]);
    int k = Integer.parseInt(args[1]);
    double p = Double.parseDouble(args[2]);
    double result = binomial(N, k, p);
    StdOut.printf("binomial(%d, %d, %s) is %s\n", N, k, p, result);
  }

  private

  static double binomial(int N, int k, double p) {
    double[][] cache = new double[N+1][k+1];
    return binomial(N, k, p, cache);
  }

  static double binomial(int N, int k, double p, double[][] cache) {
    if (cache[N][k] == -1) {
      double result;

      if ((N == 0) && (k == 0)) result = 1.0;
      if ((N < 0) || (k < 0)) result = 0.0;
      result = (1 - p) * binomial(N-1, k, p) + p * binomial(N-1, k-1, p);

      cache[N][k] = result;
    }

    return cache[N][k];
  }
}