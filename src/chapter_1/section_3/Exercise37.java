package algsex.chapter1.section3;

import java.util.*;
import edu.princeton.cs.algs4.*;

// 1.3.37 Josephus problem. In the Josephus problem from antiquity, N people are in dire
// straits and agree to the following strategy to reduce the population. They arrange themselves
// in a circle (at positions numbered from 0 to N–1) and proceed around the circle,
// eliminating every Mth person until only one person is left. Legend has it that Josephus
// figured out where to sit to avoid being eliminated. Write a Queue client Josephus that
// takes M and N from the command line and prints out the order in which people are
// eliminated (and thus would show Josephus where to sit in the circle).
public class Exercise37 {
  public static void main(String[] args) {
    if (args.length >= 2)
      runWithArgs(args);
    else
      runTests();
  }

  private static void runWithArgs(String[] args) {
    int N = Integer.parseInt(args[0]);
    int M = Integer.parseInt(args[1]);
    int s = solution(N, M);

    StdOut.printf("You should sit in %dth position to save your life.\n", s);
  }

  private static void runTests() {
    runTest(5, 2, 3);
    runTest(5, 3, 4);
    runTest(10, 3, 4);

    StdOut.println("All tests passed");
  }

  private static void runTest(int N, int M, int expected) {
    int S = solution(N, M);
    assert S == expected : String.format("Expected solution for N = %d M = %d to equal %d, but was %d.", N, M, expected, S);
  }

  private static int solution(int N, int M) {
    CyclicQueue queue = initializeCircle(N);
    CyclicQueue.Node node = queue.first;

    // debug(queue);

    while (queue.first.next != queue.first) {
      for (int i = 1; i <= M - 2; i++) {
        node = node.next;
      }

      if (node.next == queue.first) {
        queue.first = node.next.next;
      }

      node.next = node.next.next;

      // debug(queue);

      node = node.next;
    }

    assert queue.first.next == queue.first : "Expected queue to have only one item left";

    return queue.first.item;
  }

  private static CyclicQueue initializeCircle(int N) {
    CyclicQueue queue = new CyclicQueue();
    for (int i = 1; i <= N; i++) queue.enqueue(i);
    return queue;
  }

  public static void debug(CyclicQueue queue) {
    CyclicQueue.Node node = queue.first;

    do {
      StdOut.printf("%d ", node.item);
      node = node.next;
    } while (node != queue.first);

    StdOut.println("");
  }

  public static class CyclicQueue {
    public Node first;
    public Node last;

    public void enqueue(int item) {
      Node oldLast = last;

      last = new Node();
      last.item = item;

      if (oldLast == null) {
        last.next = last;
        first = last;
      } else {
        last.next = first;
        oldLast.next = last;
      }
    }

    public class Node {
      int item;
      Node next;
    }
  }
}
