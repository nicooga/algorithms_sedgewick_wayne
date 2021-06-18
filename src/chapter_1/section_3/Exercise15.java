package algsex.chapter1.section3;

import edu.princeton.cs.algs4.*;

// 1.3.15 Write a Queue client that takes a command-line argument k and prints the kth
// from the last string found on standard input (assuming that standard input has k or
// more strings).
public class Exercise15 {
  private static Queue<String> queue = new Queue<>();

  public static void main(String[] args) {
    int k = Integer.parseInt(args[0]);

    while(!StdIn.isEmpty()) {
      if (!queue.isEmpty() && queue.size() == k + 1) queue.dequeue();
      queue.enqueue(StdIn.readLine());
    }

    StdOut.println(queue.dequeue());
  }
}
