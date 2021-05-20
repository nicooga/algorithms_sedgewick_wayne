package algsex.chapter1.section1;

import java.util.*;
import edu.princeton.cs.algs4.*;
import algsex.support.Matrix;

public class Exercise33 {
  public static void main(String[] _args) {
    testVectorDotProduct();
    testMatrixMatrixMultiplication();
    testMatrixTransposal();
    testMatrixVectorMultiplication();
    testVectorMatrixMultiplication();

    StdOut.println("Tests passed");
  }

  private static void testVectorDotProduct() {
    double[] a = { 3, 2, -1, 9 };
    double[] b = { -3, 2, -1, 0 };
    double[] c = { 0, -5, 13, 32 };

    assert Matrix.dot(a, b) == -4;
    assert Matrix.dot(b, c) == -23;
  }

  private static void testMatrixMatrixMultiplication() {
    double[][] A = {
      { 2, 3, -5 },
      { 8, 2, 0 }
    };

    double[][] B = {
      { 8, 9, 23, 2 },
      { -1, 0, 3, 4},
      { 3, 2, 8 , -9 }
    };

    assert matrixEquals(
      Matrix.mult(A, B),
      new double[][] {
        { -2, 8, 15, 61 },
        { 62, 72, 190, 24 }
      }
    );
  }

  private static void testMatrixTransposal() {
    assert matrixEquals(
      Matrix.transpose(
        new double[][] {
          { 1, 2 }
        }
      ),
      new double[][] {
        { 1 },
        { 2 }
      }
    );

    assert matrixEquals(
      Matrix.transpose(
        new double[][] {
          { 1, 2 },
          { 3, 4 }
        }
      ),
      new double[][] {
        { 1, 3 },
        { 2, 4 }
      }
    );

    assert matrixEquals(
      Matrix.transpose(
        new double[][] {
          { 1, 2 },
          { 3, 4 },
          { 5, 6 }
        }
      ),
      new double[][] {
        { 1, 3, 5 },
        { 2, 4, 6 }
      }
    );
  }

  private static void testMatrixVectorMultiplication() {
    assert matrixEquals(
      Matrix.mult(
        new double[][] {
          { 1, -1, 2 },
          { 0, -3, 1 }
        },
        new double[] { 2, 1, 0 }
      ),
      new double[][] {
        { 1 },
        { -3 }
      }
    );
  }

  private static void testVectorMatrixMultiplication() {
    assert arrayEquals(
      Matrix.mult(
        new double[] { 0, 1, 2 },
        new double[][] {
          { 1, 2, 3, 4 },
          { 4, 5, 6, 7 },
          { 7, 8, 9, 10 }
        }
      ),
      new double[] { 18, 21, 24, 27 }
    );
  }

  private static boolean matrixEquals(double[][] A, double[][] B) {
    for (int i = 0; i < A.length; i++)
      if (!arrayEquals(A[i], B[i]))
        return false;

    return true;
  }

  // I ditched java.utils.Arrays.equals() because it does not
  // consider 0.0 and -0.0 to be equal values.
  private static boolean arrayEquals(double[] a, double[] b) {
    if (!(a.length == b.length)) return false;

    for (int i = 0; i < a.length; i++)
      if (a[i] != a[i]) return false;

    return true;
  }
}