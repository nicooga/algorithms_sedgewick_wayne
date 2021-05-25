package algsex.chapter1.section2;

import edu.princeton.cs.algs4.*;

public class Exercise6 {
  public static void main(String[] args) {
    String str1 = args[0];
    String str2 = args[1];
    StdOut.println(areCircularPermutations(str1, str2));
  }

  private static boolean areCircularPermutations(String lhs, String rhs) {
    return lhs.length() == rhs.length() && (lhs + lhs).indexOf(rhs) != -1;
  }
}
