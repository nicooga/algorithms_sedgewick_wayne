package algsex.chapter1.section2;

import java.util.*;
import java.lang.*;
import javax.swing.*;
import edu.princeton.cs.algs4.*;

public class Exercise17 {
  public static void main(String[] args) {
    testEquality();
    testFractionSimplication();
    testAddition();
    testSubtraction();
    testMultiplication();
    testDivision();
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

    assertRaisesAssertionException(
      () -> new Rational(1, 3).plus(new Rational(1, halfMaxPlusOne)),
      String.format("Multiplication of 3 and %s would cause overflow", halfMaxPlusOne)
    );

    assertRaisesAssertionException(
      () -> new Rational(2, halfMinMinusOne).plus(new Rational(1, 2)),
      String.format("Multiplication of %s and 2 would cause overflow", Math.abs(halfMinMinusOne))
    );

    assertRaisesAssertionException(
      () -> new Rational(halfMaxPlusOne, 1).plus(new Rational(halfMaxPlusOne, 1)),
      String.format("Addition of %s and %s would cause overflow", halfMaxPlusOne, halfMaxPlusOne)
    );

    assertRaisesAssertionException(
      () -> new Rational(halfMinMinusOne, 1).plus(new Rational(halfMinMinusOne, 1)),
      String.format("Addition of %s and %s would cause underflow", halfMinMinusOne, halfMinMinusOne)
    );

    assertRaisesAssertionException(
      () -> new Rational(halfMinMinusOne, 1).plus(new Rational(halfMinMinusOne, 1)),
      String.format("Addition of %s and %s would cause underflow", halfMinMinusOne, halfMinMinusOne)
    );

    assertRaisesAssertionException(
      () -> new Rational(halfMinMinusOne, 1).times(new Rational(2, 1)),
      String.format("Multiplication of %s and %s would cause overflow", halfMinMinusOne, 2)
    );

    assertRaisesAssertionException(
      () -> new Rational(1, halfMinMinusOne).times(new Rational(1, 2)),
      String.format("Multiplication of %s and %s would cause overflow", halfMinMinusOne, 2)
    );

    assertRaisesAssertionException(
      () -> new Rational(1, halfMinMinusOne).dividedBy(new Rational(2, 1)),
      String.format("Multiplication of %s and %s would cause overflow", halfMinMinusOne, 2)
    );

    assertRaisesAssertionException(
      () -> new Rational(halfMinMinusOne, 1).dividedBy(new Rational(1, 2)),
      String.format("Multiplication of %s and %s would cause overflow", halfMinMinusOne, 2)
    );
  }

  private static void assertRaisesAssertionException(
    Runnable runnable,
    String expectedAssertionMessage
  ) {
    Object marker = Integer.toString(new Object().hashCode());

    try {
      runnable.run();
      assert false : marker;
    } catch (AssertionError e) {
      assert e.getMessage() != marker : String.format(
        "Expected to raise AssertionError with message \"%s\", but did not raise.",
        expectedAssertionMessage
      );

      assert e.getMessage().equals(expectedAssertionMessage) : String.format(
        "Expected to raise AssertionError with message \"%s\", but raised \"%s\".",
        expectedAssertionMessage,
        e.getMessage()
      );
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
        multiplySafely(this.numerator, rhs.numerator),
        multiplySafely(this.denominator, rhs.denominator)
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
      String overflowErrorMessage = String.format("Addition of %d and %d would cause overflow", lhs, rhs);
      String underflowErrorMessage = String.format("Addition of %d and %d would cause underflow", lhs, rhs);

      if (lhs >= 0)
        assert rhs <= Long.MAX_VALUE - lhs : overflowErrorMessage;
      else if (rhs >= 0)
        assert lhs <= Long.MAX_VALUE - rhs : overflowErrorMessage;

      if (lhs <= 0)
        assert rhs >= Long.MIN_VALUE - lhs : underflowErrorMessage;
      else if (rhs <= 0)
        assert lhs >= Long.MIN_VALUE - rhs : underflowErrorMessage;

      return lhs + rhs;
    }
  }
}