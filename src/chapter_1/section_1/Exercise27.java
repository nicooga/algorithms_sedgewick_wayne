package algsex.chapter1.section1;

import java.util.*;
import edu.princeton.cs.algs4.*;

public class Exercise27 {
  private static ArrayList<Integer> cache = new ArrayList<>();

  public static void main(String[] args) {
    int N = Integer.parseInt(args[0]);
    int k = Integer.parseInt(args[1]);
    double p = Double.parseDouble(args[2]);
    double result = binomial(N, k, p);
    StdOut.printf("binomial(%d, %d, %s) is %s\n", N, k, p, result);
  }

  private

  static double binomial(int N, int k, double p) {
    if ((N == 0) && (k == 0)) return 1.0;
    if ((N < 0) || (k < 0)) return 0.0;
    return (1 - p) * binomial(N-1, k, p) + p * binomial(N-1, k-1, p);
  }
}