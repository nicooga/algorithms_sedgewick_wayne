package algsex.support;

import edu.princeton.cs.algs4.StdOut;

public class Stack<T> {
    private Node first;
    private int N;

    public static void main(String[] args) {
        Stack<Integer> s = new Stack<>();

        s.push(1);
        s.push(2);
        s.push(3);

        Test.assertEqual((int) s.pop(), 3);
        Test.assertEqual((int) s.pop(), 2);
        Test.assertEqual((int) s.pop(), 1);

        StdOut.println("Tests passed");
    }

    public boolean isEmpty() { return first == null; }
    public int size() { return N; }
    public void push(T item) {
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
        N++;
    }

    public T pop() {
        T item = first.item;
        first = first.next;
        N--;
        return item;
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        Node node = first;

        if (node == null) return "Stack(empty)";

        s.append("Stack(");
        s.append(node.item);

        node = node.next;

        while (node != null) {
            s.append(", ");
            s.append(node.item);
            node = node.next;
        }

        s.append(")");

        return s.toString();
    }

    private class Node {
        T item;
        Node next;
    }
}