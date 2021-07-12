package algsex.chapter1.section3;

import java.util.*;
import edu.princeton.cs.algs4.*;

// 1.3.47 Catenable queues, stacks, or steques.
// Add an extra operation catenation that (de-structively) concatenates two queues, stacks, or steques (see Exercise 1.3.32).
// Hint: Use a circular linked list, maintaining a pointer to the last item.
public class Exercise47 {
  public static void main(String[] args) {
    Queue<Integer> q1 = buildQueue(1);

    assert q1.dequeue() == 1;
    assert q1.dequeue() == 2;
    assert q1.dequeue() == 3;

    Queue<Integer> q2 = buildQueue(4);

    assert q2.dequeue() == 4;
    assert q2.dequeue() == 5;
    assert q2.dequeue() == 6;

    Queue<Integer> q4 = buildQueue(1);
    Queue<Integer> q5 = buildQueue(4);

    Queue<Integer> q6 = q4.concat(q5);

    assert q6.dequeue() == 1;
    assert q6.dequeue() == 2;
    assert q6.dequeue() == 3;
    assert q6.dequeue() == 4;
    assert q6.dequeue() == 5;
    assert q6.dequeue() == 6;

    StdOut.println("Tests passed");
  }

  private static Queue<Integer> buildQueue(int firstItem) {
    Queue<Integer> q = new Queue();

    q.enqueue(firstItem);
    q.enqueue(firstItem + 1);
    q.enqueue(firstItem + 2);

    return q;
  }

  private static class Queue<T> {
    private Node last;

    public void enqueue(T item) {
      Node oldLast = last;

      last = new Node();
      last.item = item;

      if (oldLast == null) last.next = last;
      else {
        last.next = oldLast.next;
        oldLast.next = last;
      }
    }

    public T dequeue() {
      T item = last.next.item;

      if (last.next == last) last = null;
      else last.next = last.next.next;

      return item;
    }

    public Queue<T> concat(Queue<T> anotherQueue) {
      Node thisFirst = last.next;
      Node anotherFirst = anotherQueue.last.next;

      last.next = anotherFirst;
      anotherQueue.last.next = thisFirst;

      last = anotherQueue.last;

      return this;
    }

    private class Node {
      T item;
      Node next;
    }
  }
}
