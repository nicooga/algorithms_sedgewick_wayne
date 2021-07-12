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

    Queue<Integer> q3 = buildQueue(1);
    Queue<Integer> q4 = buildQueue(4);
    Queue<Integer> q5 = q3.concat(q4);

    assert q5.dequeue() == 1;
    assert q5.dequeue() == 2;
    assert q5.dequeue() == 3;
    assert q5.dequeue() == 4;
    assert q5.dequeue() == 5;
    assert q5.dequeue() == 6;

    Stack<Integer> s1 = buildStack(1);

    assert s1.pop() == 3;
    assert s1.pop() == 2;
    assert s1.pop() == 1;

    Stack<Integer> s2 = buildStack(4);

    assert s2.pop() == 6;
    assert s2.pop() == 5;
    assert s2.pop() == 4;

    Stack<Integer> s3 = buildStack(1);
    Stack<Integer> s4 = buildStack(4);
    Stack<Integer> s5 = s3.concat(s4);

    assert s4.pop() == 3;
    assert s4.pop() == 2;
    assert s4.pop() == 1;
    assert s4.pop() == 6;
    assert s4.pop() == 5;
    assert s4.pop() == 4;

    StdOut.println("Tests passed");
  }

  private static Queue<Integer> buildQueue(int firstItem) {
    Queue<Integer> q = new Queue();

    q.enqueue(firstItem);
    q.enqueue(firstItem + 1);
    q.enqueue(firstItem + 2);

    return q;
  }

  private static Stack<Integer> buildStack(int firstItem) {
    Stack<Integer> s = new Stack();

    s.push(firstItem);
    s.push(firstItem + 1);
    s.push(firstItem + 2);

    return s;
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

  private static class Stack<T> {
    private Node last;

    public void push(T item) {
      Node first = new Node();
      first.item = item;

      if (last == null) {
        first.next = first;
        last = first;
      } else {
        first.next = last.next;
        last.next = first;
      }
    }

    public T pop() {
      T item = last.next.item;

      if (last.next == last) last = null;
      else last.next = last.next.next;

      return item;
    }

    public Stack<T> concat(Stack<T> anotherStack) {
      Node thisFirst = last.next;
      Node anotherFirst = anotherStack.last.next;

      last.next = anotherFirst;
      anotherStack.last.next = thisFirst;

      last = anotherStack.last;

      return this;
    }

    private class Node {
      T item;
      Node next;
    }
  }
}
