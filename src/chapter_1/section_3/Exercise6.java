package algsex.chapter1.section3;

import edu.princeton.cs.algs4.*;

// 1.3.6 What does the following code fragment do to the queue q?
// Stack<String> stack = new Stack<String>();
// while (!q.isEmpty())
// stack.push(q.dequeue());
// while (!stack.isEmpty())
// q.enqueue(stack.pop());
//
// Answer: it reverses the queue item's order
public class Exercise6 {
  public static void main(String[] args) {
    Queue<String> q = new Queue<>();


    q.enqueue("1");
    q.enqueue("2");
    q.enqueue("3");
    q.enqueue("4");
    q.enqueue("5");

    StdOut.println("Before: " + q);

    mystery(q);

    StdOut.println("After: " + q);
  }

  private static void mystery(Queue<String> q) {
    Stack<String> stack = new Stack<String>();

    while (!q.isEmpty())
      stack.push(q.dequeue());

    while (!stack.isEmpty())
      q.enqueue(stack.pop());
  }
}