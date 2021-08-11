package algsex.support;

import algsex.support.Point;

// A wrapper around edu.princeton.cs.algs4.StdDraw that exposes a more convenient API for our usecase.
public class StdDraw {
  public void drawLine(Point a, Point b) {
    edu.princeton.cs.algs4.StdDraw.line(a.getX(), a.getY(), b.getX(), b.getY());
  }

  public void drawText(Point p, String text) {
    edu.princeton.cs.algs4.StdDraw.text(p.getX(), p.getY(), text);
  }

  public void drawCircle(Point p, double radius) {
    edu.princeton.cs.algs4.StdDraw.circle(p.getX(), p.getY(), radius);
  }

  public void setCanvasSize(int w, int h) { edu.princeton.cs.algs4.StdDraw.setCanvasSize(w, h); }
  public void setXscale(double min, double max) { edu.princeton.cs.algs4.StdDraw.setXscale(min, max); } 
  public void setYscale(double min, double max) { edu.princeton.cs.algs4.StdDraw.setYscale(min, max); } 
  public void clear() { edu.princeton.cs.algs4.StdDraw.clear(); }
}