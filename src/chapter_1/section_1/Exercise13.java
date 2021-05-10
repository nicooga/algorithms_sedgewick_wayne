package algsex.chapter1.section1;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Exercise13 {
  public static void main(String[] args) {
    int rows = (int) StdRandom.uniform(5, 10);
    int cols = (int) StdRandom.uniform(5, 10);

    int[][] matrix = createRandomMatrix(rows, cols);
    int[][] transposedMatrix = createTransposedMatrix(matrix);

    StdOut.println("Orignal matrix: ");
    printMatrix(matrix);

    StdOut.println("Transposed matrix: ");
    printMatrix(transposedMatrix);
  }

  static int[][] createTransposedMatrix(int[][] matrix) {
    int rows = matrix.length;
    int cols = matrix[0].length;
    int newRows = cols;
    int newCols = rows;

    int[][] newMatrix = new int[newRows][newCols];

    for (int newRow = 0; newRow < newRows; newRow++) {
      for (int newCol = 0; newCol < newCols; newCol++) {
        newMatrix[newRow][newCol] = matrix[newCol][newRow];
      }
    }

    return newMatrix;
  }

  static int[][] createRandomMatrix(int rows, int cols) {
    int[][] matrix = new int[rows][cols];
    fillRandomly(matrix);
    return matrix;
  }

  static void fillRandomly(int[][] matrix) {
    for (int row = 0; row < matrix.length; row++)
      for (int col = 0; col < matrix[row].length; col++)
        matrix[row][col] = (int) StdRandom.uniform(9);
  }

  static void printMatrix(int[][] matrix) {
    int rows = matrix.length;

    printHeader(matrix);

    for (int row = 0; row < rows; row++)
      printRow(matrix, row);
  }

  static void printHeader(int[][] matrix) {
    int cols = matrix[0].length;
    String header = "   |";
    String divider = "===|";

    for (int col = 0; col < cols; col++) {
      header += " " + col + " ";
      divider += "===";
    }

    StdOut.println(header);
    StdOut.println(divider);
  }

  static void printRow(int[][] matrix, int row) {
    int cols = matrix[row].length;

    StdOut.print(" " + row + " |");

    for (int col = 0; col < cols; col++)
      printCell(matrix, row, col);

    StdOut.print("\n");
  }

  static void printCell(int[][] matrix, int row, int col) {
    StdOut.print(" " + matrix[row][col] + " ");
  }
}