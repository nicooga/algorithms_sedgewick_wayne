package algsex.chapter1.section1;

import java.util.*;
import edu.princeton.cs.algs4.*;

public class Exercise19 {
  private static ArrayList<Long> cache = new ArrayList<>();

  static {
    cache.add(0L);
    cache.add(1L);
  }

  public static void main(String[] args) {
    for (int N = 0; N < 100; N++)
      StdOut.println(N + " " + F(N));
  }

  public static long F(int N) {
    if (cache.size() < (N + 1) || cache.get(N) == null) {
      cache.add(N, F(N-1) + F(N-2));
    }

    return cache.get(N);
  }
}