package algsex.chapter1.section1;

import java.math.*;
import edu.princeton.cs.algs4.*;

public class Exercise20 {
  // 1.1.20 Write a recursive static method that computes the value of ln(N!)
  public static void main(String[] _args) {
    for (int i = 0; i <= 1000; i++)
      StdOut.printf("factorial of %d is %s\n", i, factorial(i));
  }

  private static double foo(double n) {
    return Math.log(factorial(n));
  }

  private static double factorial(double n) {
    if (n == 0 || n == 1) return 1;
    if (n == 2) return n;
    return factorial(n - 1) * n;
  }
}