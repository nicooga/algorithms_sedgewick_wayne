package algsex.chapter1.section1;

import edu.princeton.cs.algs4.*;

public class Exercise18 {
  public static void main(String[] _args) {
    StdOut.println(mystery(2, 25));
  }

  private static int mystery(int a, int b) {
    if (b == 0) return 0;
    if (b % 2 == 0) return mystery(a+a, b/2);
    return mystery(a+a, b/2) + a;
  }
}