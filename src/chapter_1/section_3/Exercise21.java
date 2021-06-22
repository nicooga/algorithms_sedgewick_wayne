package algsex.chapter1.section3;

import java.util.Iterator;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

// 1.3.21 Write a method find() that takes a linked list and a string key as arguments 
// and returns true if some node in the list has key as its item field, false otherwise.
public class Exercise21 {
  public static void main(String[] args) {
    Queue<String> list = new Queue<>();

    list.enqueue("asdf");
    list.enqueue("qwer");
    list.enqueue("uiop");

    assert find(list, "asdf");
    assert !find(list, "zxcv");

    StdOut.println("Tests passed");
  }

  private static boolean find(Queue<String> list, String str) {
    Iterator<String> iterator = list.iterator();

    while(iterator.hasNext())
      if (iterator.next().equals(str))
        return true;

    return false;
  }
}
