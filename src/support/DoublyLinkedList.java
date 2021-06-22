package algsex.support;

public class DoublyLinkedList<T> {
  private Node first;
  private Node last;
  private int N;

  public void push(T item)  {
    Node oldFirst = first;

    first = new Node();
    first.item = item;
    first.next = oldFirst;

    N++;

    if (N == 1) {
      last = first;
    } else {
      oldFirst.prev = first;
    }
  }

  public T pop() {
    T item = last.item;

    last = last.prev;

    if (last != null) {
      last.next = null;
    }

    N--;

    if (N == 0) {
      first = null;
    }

    return item;
  }

  public void delete(int k) {
    Node node;

    do {
      node = first.next;
      k--;
    } while (k > 0);

    node.next = node.next.next;

    if (node.next != null) {
      node.next.prev = node;
    }

    N--;
  }

  public boolean isEmpty() {
    return N == 0;
  }

  private class Node {
    T item;
    Node next;
    Node prev;
  }
}