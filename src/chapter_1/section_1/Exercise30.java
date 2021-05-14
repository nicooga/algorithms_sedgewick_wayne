package algsex.chapter1.section1;

import edu.princeton.cs.algs4.*;

// 1.1.30 Array exercise. Write a code fragment that creates an N-by-N boolean array
// a[][] such that a[i][j] is true if i and j are relatively prime (have no common factors),
// and false otherwise.
public class Exercise30 {
  public

  static void main(String[] args) {
    int width = Integer.parseInt(args[0]);
    int height = Integer.parseInt(args[1]);

    boolean[][] coprimesMatrix = new boolean[height][width];

    for (int i = 0; i < height; i++) {
      int rowNumber = i + 2;

      for (int j = 0; j < width; j++) {
        int columnNumber = j + 2;

        coprimesMatrix[i][j] = coprime(rowNumber, columnNumber);
      }
    }

    printMatrix(coprimesMatrix, 2);
  }

  static boolean coprime(int a, int b) {
    return gcd(a, b) == 1;
  }

  static int gcd(int p, int q) {
    if (q == 0) return p;
    int r = p % q;
    return gcd(q, r);
  }

  static void printMatrix(boolean[][] matrix, int labelOffset) {
    int cols = matrix.length;

    printHeader(cols, labelOffset);

    for (int row = 0; row < cols; row++) {
      printRow(matrix, row, labelOffset);
    }
  }

  static void printHeader(int rows, int labelOffset) {
    String header = "    |";
    String divider = "====|";

    for (int i = 0; i < rows; i++) {
      header += String.format(" %2d ", i + labelOffset);
      divider += "====";
    }

    StdOut.println(header);
    StdOut.println(divider);
  }

  static void printRow(boolean[][] matrix, int row, int labelOffset) {
    int cols = matrix[row].length;

    StdOut.printf(" %2d |", row + labelOffset);

    for (int col = 0; col < cols; col++)
      printCell(matrix, row, col);

    StdOut.print("\n");
  }

  static void printCell(boolean[][] matrix, int row, int col) {
    boolean value = matrix[row][col];
    String representation = value ? "*" : " ";
    StdOut.printf(" %s  ", representation);
  }
}
