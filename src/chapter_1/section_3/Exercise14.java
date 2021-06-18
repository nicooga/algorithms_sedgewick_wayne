package algsex.chapter1.section3;

import java.util.*;
import edu.princeton.cs.algs4.*;

// 1.3.14 Develop a class ResizingArrayQueueOfStrings that implements the queue
// abstraction with a fixed-size array, and then extend your implementation to use array
// resizing to remove the size restriction.
public class Exercise14 {
  public static void main(String[] args) {
    QueueOfStrings queue = new QueueOfStrings(3);

    queue.enqueue("A");
    queue.enqueue("B");
    queue.enqueue("C");

    assert queue.dequeue() == "A";
    assert queue.dequeue() == "B";
    assert queue.dequeue() == "C";

    queue.enqueue("D");
    queue.enqueue("E");
    queue.enqueue("F");

    assert queue.dequeue() == "D";
    assert queue.dequeue() == "E";
    assert queue.dequeue() == "F";

    StdOut.println("Tests passed");
  }

  private static class QueueOfStrings {
    private String[] array;
    private int enqueueIndex;
    private int dequeueIndex;

    public QueueOfStrings(int size) {
      this.array = new String[size];
    }

    public void enqueue(String item) {
      if (enqueueIndex > array.length - 1) resize(array.length * 2);
      array[enqueueIndex] = item;
      enqueueIndex++;
    }

    public String dequeue() {
      String item = array[dequeueIndex];
      array[dequeueIndex] = null;
      dequeueIndex++;
      return item;
    }

    private void resize(int length) {
      String[] newArray = new String[length];

      for (int i = 0; i < array.length ; i++)
        newArray[i] = array[i];

      this.array = newArray;
    }
  }
}
