package algsex.support;

import edu.princeton.cs.algs4.*;

class Asdf {
  public static void main(String[] _args) {
    StdDraw.setXscale(0.0, 100.0);
    StdDraw.setYscale(0.0, 100.0);

    StdDraw.setPenRadius(0.01);

    StdDraw.line(0, 0, 80, 80);

    StdDraw.setPenColor(StdDraw.RED);
    StdDraw.rectangle(0, 0, 1, 100);

    StdDraw.setPenColor(StdDraw.BLUE);
    StdDraw.rectangle(0, 50, 10, 100);

    StdDraw.setPenColor(StdDraw.GREEN);
    StdDraw.rectangle(0, 0, 1, 100);
  }
}