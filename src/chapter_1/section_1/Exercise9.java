package algsex.chapter1.section1;

import java.lang.*;
import edu.princeton.cs.algs4.StdOut;

public class Exercise9 {
  public static void main(String[] args) {
    int x = Integer.parseInt(args[0]);
    StdOut.println(toBinaryString(x));
  }

  private static String toBinaryString(int x) {
    String result = "";
    int rest = x;

    for (int p = (int) Math.floor(log2(x)); p >= 0; p--) {
      int power_of_two = (int) Math.pow(2, p);

      if (rest > 0 && power_of_two <= rest) {
        result += "1";
        rest = rest % power_of_two;
      } else {
        result += "0";
      }

    }

    return result;
  }

  private static double log2(int x) {
    return Math.log(x) / Math.log(2);
  }
}