package algsex.chapter1.section2.exercise_3;

import java.lang.reflect.*;
import java.awt.*;
import edu.princeton.cs.algs4.*;

// I use an extended version of Interval2D which assigns a random color for easier debugging.
//
// I'm not a fan of extending behaviour via inheritance, but it makes it easy to integrate with the rest
// of the code, because ColoredInterval2D's API is a superset of Interval2D's.
// Also, information we need from Interval2D is private, so this is one way to deal with that.
public class ColoredInterval2D extends Interval2D {
  public Color color;

  public ColoredInterval2D(Interval1D x, Interval1D y) {
    super(x, y);

    color = new Color(
      StdRandom.uniform(256),
      StdRandom.uniform(256),
      StdRandom.uniform(256)
    );
  }

  public boolean contains(ColoredInterval2D otherInterval) {
    return (
      contains(otherInterval.topLeftCorner()) &&
      contains(otherInterval.topRightCorner()) &&
      contains(otherInterval.bottomLeftCorner()) &&
      contains(otherInterval.bottomRightCorner())
    );
  }

  // Modified to output an ANSI color escape sequence
  public String toString() {
    int r = color.getRed();
    int g = color.getGreen();
    int b = color.getBlue();

    return String.format("\033[38;2;%d;%d;%dm%s\033[0m", r, g, b, super.toString());
  }

  protected Point2D topLeftCorner() { return new Point2D(x().min(), y().max()); }
  protected Point2D topRightCorner() { return new Point2D(x().max(), y().max()); }
  protected Point2D bottomLeftCorner() { return new Point2D(x().min(), y().min()); }
  protected Point2D bottomRightCorner() { return new Point2D(x().max(), y().min()); }

  // HACK: x and y intervals are private. This is how I get a handle on them.
  // I assume the author's did this on purpose to teach us a lesson about encapsulation.
  protected Interval1D x() { return (Interval1D) getPrivateFieldValue("x"); }
  protected Interval1D y() { return (Interval1D) getPrivateFieldValue("y"); }

  private Object getPrivateFieldValue(String name) {
    Class<?> superClass = getClass().getSuperclass();

    try {
      Field field = superClass.getDeclaredField(name);
      field.setAccessible(true);
      return field.get(this);
    } catch(NoSuchFieldException e) {
      assert false;
      throw new RuntimeException("This should not have happened");
    } catch(IllegalAccessException e) {
      assert false;
      throw new RuntimeException("This should not have happened");
    }
  }

  public static void main(String[] args) {
    ColoredInterval2D interval1 = createColoredInterval2D(0, 10, 0, 10);
    ColoredInterval2D interval2 = createColoredInterval2D(2, 8, 2, 8);
    ColoredInterval2D interval3 = createColoredInterval2D(-2, 8, -2, 8);
    ColoredInterval2D interval4 = createColoredInterval2D(-2, 11, -2, 11);

    assert interval1.contains(interval1);
    assert interval1.contains(interval2);
    assert !interval1.contains(interval3);
    assert !interval1.contains(interval4);

    assert interval2.contains(interval2);
    assert !interval2.contains(interval1);
    assert !interval2.contains(interval3);
    assert !interval2.contains(interval4);

    assert interval3.contains(interval3);
    assert !interval3.contains(interval1);
    assert interval3.contains(interval2);
    assert !interval3.contains(interval4);

    assert interval4.contains(interval4);
    assert interval4.contains(interval1);
    assert interval4.contains(interval2);
    assert interval3.contains(interval3);

    StdOut.println("Tests passed");
  }

  private static ColoredInterval2D createColoredInterval2D(double x1, double x2, double y1, double y2) {
    Interval1D x = new Interval1D(x1, x2);
    Interval1D y = new Interval1D(y1, y2);
    return new ColoredInterval2D(x, y);
  }
}