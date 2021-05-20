package algsex.support;

public class Matrix  {
  public static double dot(double[] x, double[] y) {
    assert x.length == y.length;
    double product = 0.0;
    for (int i = 0; i < x.length; i++) product += x[i] * y[i];
    return product;
  }

  public static double[][] mult(double[][] a, double[][] b) {
    assert cols(a) == rows(b);

    double[][] product = new double[rows(a)][cols(b)];

    for (int i = 0; i < rows(product); i++)
      for (int j = 0; j < cols(product); j++)
        product[i][j] = dot(row(a, i), col(b, i));

    return product;
  }

  public static double[][] transpose(double[][] a) {
    double[][] result = new double[cols(a)][rows(a)];

    for (int i = 0; i < rows(a); i++)
      for (int j = 0; j < cols(a); j++)
        result[j][i] = a[i][j];

    return result;
  }

  public static double[][] mult(double[][] a, double[] x) { // matrix-vector product
    assert cols(a) == x.length;
    double[][] result = new double[rows(a)][1];
    for (int i = 0; i < rows(a); i++) result[i][0] = dot(row(a, i), x);
    return result;
  }

  public static double[] mult(double[] y, double[][] a) { // vector-matrix product
    assert y.length == rows(a);
    double[] result = new double[cols(a)];
    for (int i = 0; i < cols(a); i++) result[i] = dot(y, col(a, i));
    return result;
  }

  private static int rows(double[][] a) { return a.length; }
  private static int cols(double[][] a) { return a[0].length; }
  private static double[] row(double[][]a, int n) { return a[n]; }
  private static double[] col(double[][]a, int n) {
    double[] vector = new double[rows(a)];

    for (int i = 0; i < rows(a); i++) {
      vector[i] = row(a, i)[n];
    }

    return vector;
  }
}