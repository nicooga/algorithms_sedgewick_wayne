package algsex.chapter1.section3;

import java.util.*;
import edu.princeton.cs.algs4.*;

// 1.3.50 Fail-fast iterator.Modify the iterator code in Stack to immediately
// throw a java.util.ConcurrentModificationException if the client modifies the
// collection (via push() or pop()) during iteration?
public class Exercise50 {
  public static void main(String[] args) {
    Stack<Integer> s = new Stack<>();

    for (int i = 1; i <= 4; i++) s.push(i);

    for (int i : s) {
      // After getting the last item (1) where are no longer getting new items,
      // so it's technically safe to operate on the stack.
      if (i > 1) {
        try {
          s.push(1);
          assert false : "An exception should have been raised";
        } catch (ConcurrentModificationException e) {
          assert true;
        }

        try {
          s.pop();
          assert false : "An exception should have been raised";
        } catch (ConcurrentModificationException e) {
          assert true;
        }
      }
    }

    StdOut.println("Tests passed");
  }

  private static class Stack<T> implements Iterable<T> {
    private Node first;
    private boolean iterating;

    public void push(T item) throws ConcurrentModificationException {
      if (iterating) throw new ConcurrentModificationException();

      Node oldFirst = first;

      first = new Node();
      first.item = item;
      first.next = oldFirst;
    }

    public T pop() throws ConcurrentModificationException {
      if (iterating) throw new ConcurrentModificationException();
      T item = first.item;
      first = first.next;
      return item;
    }

    public Iterator<T> iterator() {
      return new ThreadSafeIterator();
    }

    private class Node {
      T item;
      Node next;
    }

    private class ThreadSafeIterator implements Iterator<T> {
      private Node node = first;

      public boolean hasNext() { return node != null; }

      public T next() {
        T item = node.item;
        node = node.next;
        iterating = node != null;
        return item;
      }
    }
  }
}
