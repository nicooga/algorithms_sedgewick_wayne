package algsex.chapter1.section1;

import edu.princeton.cs.algs4.*;

public class Exercise24 {
  public static void main(String[] args) {
    int p = Integer.parseInt(args[0]);
    int q = Integer.parseInt(args[1]);
    int greatestCommonDivisor = gcd(p, q);
    StdOut.printf("Greatest common divisor of %d and %d is %d\n", p, q, greatestCommonDivisor);
  }

  private static int gcd(int p, int q) {
    trace(p, q);
    if (q == 0) return p;
    int r = p % q;
    return gcd(q, r);
  }

  private static void trace(int p, int q) {
    StdOut.printf("p: %d, q: %d\n", p, q);
  }
}