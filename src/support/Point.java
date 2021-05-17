package algsex.support;

public class Point {
  private double x;
  private double y;

  public Point(double x, double y) {
    this.x = x;
    this.y = y;
  }

  public double getX() {
    return this.x;
  }

  public double getY() {
    return this.y;
  }

  public Point translateX(double distance) {
    return new Point(this.x + distance, this.y);
  }

  public Point translateY(double distance) {
    return new Point(this.x, this.y + distance);
  }

  public Point translateXY(double xDistance, double yDistance) {
    return new Point(this.x + xDistance, this.y + yDistance);
  }

  public String toString() {
    return String.format("(%s, %s)", x, y);
  }

  public static void main(String[] _args) {
    Point pointA = new Point(0, 1);
    Point pointB = new Point(2, 3);
    Point pointC = pointA.translateX(10);
    Point pointD = pointA.translateY(-10);
    Point pointE = pointA.translateXY(-10, -10);

    assert pointA.getX() == 0.0;
    assert pointA.getY() == 1.0;
    assert pointA.toString().equals("(0.0, 1.0)");

    assert pointB.getX() == 2.0;
    assert pointB.getY() == 3.0;
    assert pointB.toString().equals("(2.0, 3.0)");

    assert pointC.getX() == 10.0;
    assert pointC.getY() == 1.0;
    assert pointC.toString().equals("(10.0, 1.0)");

    assert pointD.getX() == 0.0;
    assert pointD.getY() == -9.0;
    assert pointD.toString().equals("(0.0, -9.0)");

    assert pointE.getX() == -10.0;
    assert pointE.getY() == -9.0;
    assert pointE.toString().equals("(-10.0, -9.0)");

    System.out.println("Tests passed");
  }
}