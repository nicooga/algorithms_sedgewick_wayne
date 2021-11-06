package algsex.support;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

class PrintRandomDoubles {
  public static void main(String[] args) {
    int quantity = Integer.parseInt(args[0]);
    double min = Double.parseDouble(args[1]);
    double max = Double.parseDouble(args[2]);

    printRandomDoubles(quantity, min, max);
  }

  private static void printRandomDoubles(int quantity, double min, double max) {
    for (int i = 0; i < quantity; i++)
      StdOut.println(StdRandom.uniform(min, max));
  }
}