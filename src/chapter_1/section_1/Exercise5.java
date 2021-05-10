package algsex.chapter1.section1;

import edu.princeton.cs.algs4.StdOut;

public class Exercise5 {
  public static void main(String[] args) {
    double double1 = Double.parseDouble(args[0]);
    double double2 = Double.parseDouble(args[1]);

    if (isWithinBounds(double1) && isWithinBounds(double2)) {
      StdOut.println("true");
    } else {
      StdOut.println("false");
    }
  }

  private static boolean isWithinBounds(double value) {
    return value > 0 && value < 1;
  }
}