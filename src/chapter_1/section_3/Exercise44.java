package algsex.chapter1.section3;

import java.util.*;
import edu.princeton.cs.algs4.*;

public class Exercise44 {
  public static void main(String[] args) {
    Buffer buffer = new Buffer();

    buffer.insert('a'); // a<
    assert buffer.size() == 1;
    buffer.insert('b'); // ab<
    assert buffer.size() == 2;
    buffer.insert('c'); // abc<
    assert buffer.size() == 3;
    buffer.insert('d'); // abcd<
    assert buffer.size() == 4;
    assert buffer.get() == 'd';
    buffer.delete(); // abc<
    assert buffer.size() == 3;
    assert buffer.get() == 'c';
    buffer.left(1); // ab<c
    assert buffer.get() == 'b';
    buffer.insert('e'); // abe<c
    assert buffer.size() == 4;
    assert buffer.get() == 'e';
    buffer.left(2); // a<bec
    buffer.insert('f'); // af<bec
    assert buffer.size() == 5;
    assert buffer.get() == 'f';
    buffer.right(1); // afb<ec
    buffer.insert('g'); // afbg<ec
    assert buffer.size() == 6;
    assert buffer.get() == 'g';
    buffer.right(2); // afbgec<
    buffer.insert('h'); // afbgech<
    assert buffer.size() == 7;
    assert buffer.get() == 'h';
    buffer.left(6); // a>fbgech
    assert buffer.get() == 'a';
    buffer.right(1);  // af<bgech
    assert buffer.get() == 'f';
    buffer.right(1);  // afb<gech
    assert buffer.get() == 'b';
    buffer.right(1);  // afbg<ech
    assert buffer.get() == 'g';
    buffer.right(1);  // afbge<ch
    assert buffer.get() == 'e';
    buffer.right(1);  // afbgec<h
    assert buffer.get() == 'c';
    buffer.right(1);  // afbgech<
    assert buffer.get() == 'h';

    StdOut.println("Tests passed");
  }

  private static class Buffer {
    private final Stack<Character> left = new Stack<>();
    private final Stack<Character> right = new Stack<>();
    private int size; 

    public void insert(char c) {
      left.push(c);
      size++;
    }

    public char get() {
      return left.peek();
    }

    public void left(int k) {
      for (int i = 0; i < k; i++) right.push(left.pop());
    }

    public void right(int k) {
      for (int i = 0; i < k; i++) left.push(right.pop());
    }

    public void delete() {
      left.pop();
      size--;
    }

    public int size() { return size; }
  }

  private static class Stack<T> {
    private Node first;

    public void push(T item) {
      Node oldFirst = first;

      first = new Node();
      first.item = item;
      first.next = oldFirst;
    }

    public T pop() {
      T item = first.item;
      first = first.next;
      return item;
    }

    public T peek() {
      return first.item;
    }

    private class Node {
      T item;
      Node next;
    }
  }
}
