package algsex.chapter1.section1;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Exercise11 {
  public static void main(String[] args) {
    int rows = (int) StdRandom.uniform(5, 10);
    int cols = (int) StdRandom.uniform(5, 10);
    boolean[][] matrix = createRandomMatrix(rows, cols);
    printMatrix(matrix, rows, cols);
  }

  private

  static boolean[][] createRandomMatrix(int rows, int cols) {
    boolean[][] matrix = new boolean[rows][cols];
    fillRandomly(matrix, rows, cols);
    return matrix;
  }

  static void fillRandomly(boolean[][] matrix, int rows, int cols) {
    for (int row = 0; row < rows; row++)
      for (int col = 0; col < cols; col++)
        matrix[row][col] = StdRandom.bernoulli();
  }

  static void printMatrix(boolean[][] matrix, int rows, int cols) {
    printHeader(cols);

    for (int row = 0; row < rows; row++)
      printRow(matrix, row, cols);
  }

  static void printHeader(int rows) {
    String header = "   |";
    String divider = "===|";

    for (int i = 0; i < rows; i++) {
      header += " " + i + " ";
      divider += "===";
    }

    StdOut.println(header);
    StdOut.println(divider);
  }

  static void printRow(boolean[][] matrix, int row, int cols) {
    StdOut.print(" " + row + " |");

    for (int col = 0; col < cols; col++)
      printCell(matrix, row, col);

    StdOut.print("\n");
  }

  static void printCell(boolean[][] matrix, int row, int col) {
    boolean value = matrix[row][col];
    String representation = value ? "*" : " ";
    StdOut.print(" " + representation + " ");
  }
}