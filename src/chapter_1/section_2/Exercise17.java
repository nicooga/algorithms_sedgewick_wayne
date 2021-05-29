package algsex.chapter1.section2;

import java.util.*;
import java.lang.*;
import edu.princeton.cs.algs4.*;

public class Exercise17 {
  public static void main(String[] args) {
    // testEquality();
    // testFractionSimplication();
    // testAddition();
    // testSubtraction();
    // testMultiplication();
    // testDivision();
    testOverflowAndUnderflow();

    StdOut.println("All tests passed");
  }

  private static void testEquality() {
    assert new Rational(1, 3).equals(new Rational(1, 3));
    assert !new Rational(1, 3).equals(new Rational(2, 3));
    assert new Rational(-1, 3).equals(new Rational(1, -3));
    assert !new Rational(-1, 3).equals(new Rational(-1, -3));
    assert new Rational(1, 3).equals(new Rational(-1, -3));

    StdOut.println("Equality tests passed");
  }

  private static void testFractionSimplication() {
    assert new Rational(2, 4).equals(new Rational(1, 2));

    StdOut.println("Fraction simplication tests passed");
  }

  private static void testAddition() {
    Rational r1 = new Rational(1, 2);
    Rational r2 = new Rational(2, 3);
    Rational r3 = new Rational(-1, 4);

    assert r1.plus(r2).equals(new Rational(7, 6));
    assert r1.plus(r3).equals(new Rational(1, 4));
    assert new Rational(1, 4).plus(new Rational(1, 7)).equals(new Rational(11, 28));

    StdOut.println("Addition tests passed");
  }

  private static void testSubtraction() {
    Rational r1 = new Rational(1, 2);
    Rational r2 = new Rational(2, 3);
    Rational r3 = new Rational(-1, 4);

    assert r1.minus(r2).equals(new Rational(1, -6));
    assert r1.minus(r3).equals(new Rational(3, 4));

    StdOut.println("Subtraction tests passed");
  }

  private static void testMultiplication() {
    assert new Rational(1, 3).times(new Rational(1, 3)).equals(new Rational(1, 9));
    assert new Rational(1, 3).times(new Rational(-1, 1)).equals(new Rational(-1, 3));
    assert new Rational(1, 3).times(new Rational(3, 2)).equals(new Rational(1, 2));

    StdOut.println("Multiplication tests passed");
  }

  private static void testDivision() {
    assert new Rational(1, 3).dividedBy(new Rational(2, 3)).equals(new Rational(1, 2));
    assert new Rational(1, 3).dividedBy(new Rational(-1, 1)).equals(new Rational(-1, 3));

    StdOut.println("Division tests passed");
  }

  private static void testOverflowAndUnderflow() {
    long halfMaxPlusOne = Long.MAX_VALUE / 2 + 1;
    long halfMinMinusOne = (Long.MIN_VALUE) / 2 - 1;

    assert halfMaxPlusOne * 2 < 0; // Assert overflow would happen
    assert halfMinMinusOne * 2 > 0; // Assert underflow would happen

    try {
      new Rational(1, 3).plus(new Rational(1, halfMaxPlusOne));
      assert false;
    } catch (AssertionError e) {
      String expectedErrorMessage = String.format("Multiplication of 3 and %s would cause overflow", halfMaxPlusOne);

      assert e.getMessage().equals(expectedErrorMessage);
    }

    try {
      new Rational(2, halfMinMinusOne).plus(new Rational(1, 2));
    } catch (AssertionError e) {
      String expectedErrorMessage = String.format("Multiplication of %s and 2 would cause overflow", Math.abs(halfMinMinusOne));
      assert e.getMessage().equals(expectedErrorMessage);
    }

    try {
      Rational r = new Rational(halfMaxPlusOne, 1).plus(new Rational(halfMaxPlusOne, 1));
      StdOut.println(r);
      assert false;
    } catch (AssertionError e) {
      String expectedErrorMessage = String.format("Addition of %s and %s would cause overflow", halfMaxPlusOne, halfMaxPlusOne);

      assert e.getMessage().equals(expectedErrorMessage);
    }

    try {
      Rational r = new Rational(halfMinMinusOne, 1).plus(new Rational(halfMinMinusOne, 1));
      assert false;
    } catch (AssertionError e) {
      String expectedErrorMessage = String.format("Addition of %s and %s would cause underflow", halfMinMinusOne, halfMinMinusOne);

      assert e.getMessage().equals(expectedErrorMessage);
    }

    try {
      Rational r = new Rational(halfMinMinusOne, 1).plus(new Rational(halfMinMinusOne, 1));
      assert false;
    } catch (AssertionError e) {
      String expectedErrorMessage = String.format("Addition of %s and %s would cause underflow", halfMinMinusOne, halfMinMinusOne);

      assert e.getMessage().equals(expectedErrorMessage);
    }
  }

  private static class Rational {
    protected long numerator;
    protected long denominator;

    public Rational(long numerator, long denominator) {
      long gcd = greatestCommonDivisor(numerator, denominator);

      this.numerator = numerator/gcd;
      this.denominator = denominator/gcd;
    }

    public String toString() {
      return String.format("(%d/%d)", numerator, denominator);
    }

    public Rational plus(Rational rhs) {
      long gcd = greatestCommonDivisor(this.denominator, rhs.denominator);
      long newDenominator = multiplySafely(this.denominator / gcd, rhs.denominator);

      return new Rational(
        addSafely(
          (newDenominator / this.denominator) * this.numerator,
          (newDenominator / rhs.denominator) * rhs.numerator
        ),
        newDenominator
      );
    }

    public Rational minus(Rational rhs) {
      Rational negativeRhs = new Rational(-rhs.numerator, rhs.denominator);
      return plus(negativeRhs);
    }

    public Rational times(Rational rhs) {
      return new Rational(
        this.numerator * rhs.numerator,
        this.denominator * rhs.denominator
      );
    }

    public Rational dividedBy(Rational rhs) {
      return times(new Rational(rhs.denominator, rhs.numerator));
    }

    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null) return false;
      if (this.getClass() != o.getClass()) return false;

      Rational otherRational = (Rational) o;

      if (this.denominator != otherRational.denominator) return false;
      if (this.numerator != otherRational.numerator) return false;

      return true;
    }

    private long greatestCommonDivisor(long p, long q) {
      if (q == 0) return p;
      long r = p % q;
      return greatestCommonDivisor(q, r);
    }

    private long multiplySafely(long lhs, long rhs) {
      assert rhs <= Long.MAX_VALUE / lhs :
        String.format("Multiplication of %d and %d would cause overflow", lhs, rhs);

      assert rhs >= Long.MIN_VALUE / lhs :
        String.format("Multiplication of %d and %d would cause underflow", lhs, rhs);

      return lhs * rhs;
    }

    private long addSafely(long lhs, long rhs) {
      assert rhs <= Long.MAX_VALUE - lhs :
        String.format("Addition of %d and %d would cause overflow", lhs, rhs);

      assert rhs >= Long.MIN_VALUE - lhs :
        String.format("Addition of %d and %d would cause underflow", lhs, rhs);

      return lhs + rhs;
    }
  }
}