package algsex.chapter1.section2;

import java.util.*;
import java.lang.*;
import edu.princeton.cs.algs4.*;

public class Exercise7 {
  public static void main(String[] args) {
    StdOut.println(mystery(args[0]));
  }

  public static String mystery(String s) {
    int N = s.length();
    if (N <= 1) return s;
    String a = s.substring(0, N/2);
    String b = s.substring(N/2, N);
    return mystery(b) + mystery(a);
  }
}
