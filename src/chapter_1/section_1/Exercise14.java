package algsex.chapter1.section1;

import java.math.BigDecimal;
import edu.princeton.cs.algs4.StdOut;

public class Exercise14 {
  public static void main(String[] _args) {
    StdOut.printf("lg(2) is %s and should be 0\n", lg(2));
    StdOut.printf("lg(8) is %s and should be 2\n", lg(8));
    StdOut.printf("lg(16) is %s and should be 3\n", lg(16));
  }

  private

  static int lg(int n) {
    int result = 0;

    while (n / 2 >= 1) {
      n /= 2;
      result++;
    }

    return result - 1;
  }
}