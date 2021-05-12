package algsex.chapter1.section1;

import java.math.*;
import edu.princeton.cs.algs4.*;

public class Exercise20 {
  // 1.1.20 Write a recursive static method that computes the value of ln(N!)
  public static void main(String[] _args) {
    for (int i = 1; i <= 1000; i++)
      StdOut.printf("given i is %s, ln(i!) is %s\n", i, lnFactorial(i));
  }

  private static double lnFactorial(int n) {
    if (n == 0) throw new Error("Can't compute ln(0)");
    if (n == 1) return 0;
    return Math.log(n) + lnFactorial(n - 1);
  }
}