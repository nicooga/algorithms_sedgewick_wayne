package algsex.chapter1.section3;

import java.util.*;
import edu.princeton.cs.algs4.*;
import algsex.support.DoublyLinkedList;

// 1.3.19 Give a code fragment that removes the last node in a linked list whose first node is first.
public class Exercise19 {
  public static void main(String[] args) {
    DoublyLinkedList<Integer> list = new DoublyLinkedList<>();

    list.push(1);
    list.push(2);
    list.push(3);

    assert list.pop() == 1;
    assert list.pop() == 2;
    assert list.pop() == 3;

    list.push(4);
    list.push(5);
    list.push(6);

    assert list.pop() == 4;
    assert list.pop() == 5;
    assert list.pop() == 6;

    StdOut.println("Tests passed");
  }
}