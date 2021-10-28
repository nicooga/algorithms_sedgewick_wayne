package algsex.support;

// A wrapper around edu.princeton.cs.algs4.StdDraw that exposes a more convenient API for our usecase.
public class StdDraw {
    public void drawLine(Point a, Point b) {
        edu.princeton.cs.algs4.StdDraw.line(a.getX(), a.getY(), b.getX(), b.getY());
    }
  
    public void drawText(Point p, String text) {
        edu.princeton.cs.algs4.StdDraw.text(p.getX(), p.getY(), text);
    }
  
    public void drawCircle(Point p, double radius) {
        drawCircle(p.getX(), p.getY(), radius);
    }
  
    public void drawCircle(double x, double y, double radius) {
        edu.princeton.cs.algs4.StdDraw.circle(x, y, radius);
    }
  
    public void drawFilledCircle(Point p, double radius) {
        drawFilledCircle(p.getX(), p.getY(), radius);
    }
  
    public void drawFilledCircle(double x, double y, double radius) {
        edu.princeton.cs.algs4.StdDraw.filledCircle(x, y, radius);
    }
  
    public void setCanvasSize(int w, int h) { edu.princeton.cs.algs4.StdDraw.setCanvasSize(w, h); }
  
    public void setScale(
        double minX, double maxX,
        double minY, double maxY
    ) {
        setXscale(minX, maxX);
        setYscale(minY, maxY);
    }
  
    public void setXscale(double min, double max) { edu.princeton.cs.algs4.StdDraw.setXscale(min, max); } 
    public void setYscale(double min, double max) { edu.princeton.cs.algs4.StdDraw.setYscale(min, max); } 
  
    public void setPenColor(java.awt.Color color) {
        edu.princeton.cs.algs4.StdDraw.setPenColor(color);
    }
  
    public void clear() { edu.princeton.cs.algs4.StdDraw.clear(); }
  
    public void clear(Point a, Point b) {
        double centerX = (a.getX() + b.getX()) / 2;
        double centerY = (a.getY() + b.getY()) / 2;
        double halfWidth = (b.getX() - a.getX()) / 2;
        double halfHeight = (b.getY() - a.getY()) / 2;
    
        edu.princeton.cs.algs4.StdDraw.setPenColor(java.awt.Color.WHITE);
    
        edu.princeton.cs.algs4.StdDraw.filledRectangle(
            centerX,
            centerY,
            halfWidth,
            halfHeight
        );
    
        edu.princeton.cs.algs4.StdDraw.setPenColor();
    }
}