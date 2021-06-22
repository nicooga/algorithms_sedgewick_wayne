package algsex.chapter1.section3;

import java.util.*;
import edu.princeton.cs.algs4.*;
import algsex.support.DoublyLinkedList;

// 1.3.20 Write a method delete() that takes an int argument k and deletes the kth ele-ment in a linked list, if it exists.
public class Exercise20 {
  public static void main(String[] args) {
    DoublyLinkedList<Integer> list = new DoublyLinkedList<>();

    list.push(1);
    list.push(2);
    list.push(3);
    list.push(4);

    list.delete(1);
    list.delete(3);

    assert list.pop() == 1;
    assert list.pop() == 3;
    assert list.isEmpty();

    StdOut.println("All tests passed");
  }
}
