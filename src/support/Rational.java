package algsex.support;

import edu.princeton.cs.algs4.StdOut;

public class Rational {
  public static void main(String[] args) { Test.main(args); }

  public static class Test {
    public static void main(String[] args) {
      testEquality();
      testFractionSimplication();
      testAddition();
      testSubtraction();
      testMultiplication();
      testDivision();
  
      StdOut.println("All tests passed");

    }

    private static void testEquality() {
      assert new Rational(1, 3).equals(new Rational(1, 3));
      assert !new Rational(1, 3).equals(new Rational(2, 3));
      assert new Rational(-1, 3).equals(new Rational(1, -3));
      assert !new Rational(-1, 3).equals(new Rational(-1, -3));
      assert new Rational(1, 3).equals(new Rational(-1, -3));
    }
  
    private static void testFractionSimplication() {
      assert new Rational(2, 4).equals(new Rational(1, 2));
    }
  
    private static void testAddition() {
      Rational r1 = new Rational(1, 2);
      Rational r2 = new Rational(2, 3);
      Rational r3 = new Rational(-1, 4);
  
      assert r1.plus(r2).equals(new Rational(7, 6));
      assert r1.plus(r3).equals(new Rational(1, 4));
    }
  
    private static void testSubtraction() {
      Rational r1 = new Rational(1, 2);
      Rational r2 = new Rational(2, 3);
      Rational r3 = new Rational(-1, 4);
  
      assert r1.minus(r2).equals(new Rational(1, -6));
      assert r1.minus(r3).equals(new Rational(3, 4));
    }
  
    private static void testMultiplication() {
      assert new Rational(1, 3).times(new Rational(1, 3)).equals(new Rational(1, 9));
      assert new Rational(1, 3).times(new Rational(-1, 1)).equals(new Rational(-1, 3));
      assert new Rational(1, 3).times(new Rational(3, 2)).equals(new Rational(1, 2));
    }
  
    private static void testDivision() {
      assert new Rational(1, 3).dividedBy(new Rational(2, 3)).equals(new Rational(1, 2));
      assert new Rational(1, 3).dividedBy(new Rational(-1, 1)).equals(new Rational(-1, 3));
    }
  }

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
    long gcd = greatestCommonDivisor(numerator, denominator);
    long newDenominator = (this.denominator * rhs.denominator)/gcd;

    return new Rational(
      newDenominator / this.denominator * this.numerator +
      newDenominator / rhs.denominator * rhs.numerator,
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

  public int toInteger() {
    return (int) (this.numerator / this.denominator);
  }

  private long greatestCommonDivisor(long p, long q) {
    if (q == 0) return p;
    long r = p % q;
    return greatestCommonDivisor(q, r);
  }
}