package algsex.chapter1.section3;

import java.util.*;
import edu.princeton.cs.algs4.*;

// 1.3.39 Ring buffer. A ring buffer, or circular queue, is a FIFO data structure of a fixed
// size N. It is useful for transferring data between asynchronous processes or for storing
// log files. When the buffer is empty, the consumer waits until data is deposited; when the
// buffer is full, the producer waits to deposit data. Develop an API for a RingBuffer and
// an implementation that uses an array representation (with circular wrap-around).
public class Exercise39 {
  public static void main(String[] args) {
    RingBuffer<Integer> buffer = new RingBuffer<>(5);

    buffer.enqueue(1);
    buffer.enqueue(2);
    buffer.enqueue(3);
    buffer.enqueue(4);
    buffer.enqueue(5);

    try {
      buffer.enqueue(5);
      assert false : "An exception should have been raised";
    } catch (RuntimeException e) {
      assert e.getMessage().equals("Queue is full") : "An exception was raised but its message is wrong";
    }

    assert buffer.dequeue() == 1;

    buffer.enqueue(6);

    assert buffer.dequeue() == 2;
    assert buffer.dequeue() == 3;
    assert buffer.dequeue() == 4;
    assert buffer.dequeue() == 5;
    assert buffer.dequeue() == 6;

    try {
      buffer.dequeue();
      assert false : "An exception should have been raised";
    } catch (RuntimeException e) {
      assert e.getMessage().equals("Queue is empty");
    }

    StdOut.println("Tests passed");
  }

  private static class RingBuffer<T> {
    private T[] items;
    private int maxSize;
    private int size;
    private int first = 0;
    private int last = -1;

    RingBuffer(int maxSize) {
      this.maxSize = maxSize;
      items = (T[]) new Object[maxSize];
    }

    public void enqueue(T item) {
      if (size == maxSize) throw new RuntimeException("Queue is full");

      last = last == maxSize - 1 ? 0 : last + 1;
      items[last] = item;
      size++;
    }

    public T dequeue() {
      if (size == 0) throw new RuntimeException("Queue is empty");

      T item = items[first];

      first = first == maxSize - 1 ? 0 : first + 1;

      size--;

      return item;
    }
  }
}
