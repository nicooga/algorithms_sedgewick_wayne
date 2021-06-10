package algsex.chapter1.section3;

import edu.princeton.cs.algs4.*;

// 1.3.5 What does the following code fragment print when N is 50? Give a high-level
// description of what it does when presented with a positive integer N.
// Stack<Integer> stack = new Stack<Integer>();
// while (N > 0)
// {
// stack.push(N % 2);
// N = N / 2;
// }
// for (int d : stack) StdOut.print(d);
// StdOut.println();
//
// Answer: it prints the binary represenation of int N
public class Exercise5 {
  public static void main(String[] args) {
    mystery(Integer.parseInt(args[0]));
  }

  public static void mystery(int N) {
    Stack<Integer> stack = new Stack<Integer>();

    while (N > 0) {
      stack.push(N % 2);
      N = N / 2;
    }

    for (int d : stack) StdOut.print(d);
    StdOut.println();
  }
}
