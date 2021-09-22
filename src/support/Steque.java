package algsex.support;

import edu.princeton.cs.algs4.StdOut;

public class Steque<T> {
    Node first;
    Node last;
    int size;

    public static void main(String[] args) {
        Steque<Integer> steque = new Steque<>();

        steque.push(1);
        assert steque.size() == 1;
        steque.push(2);
        assert steque.size() == 2;
        steque.enqueue(3);
        assert steque.size() == 3;

        assert steque.pop() == 2;
        assert steque.size() == 2;
        assert steque.pop() == 1;
        assert steque.size() == 1;
        assert steque.pop() == 3;
        assert steque.size() == 0;
        assert steque.size() == 0;
        assert steque.isEmpty();

        StdOut.println("Tests passed");
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void push(T item) {
        Node oldFirst = first;

        first = new Node();
        first.item = item;
        first.next = oldFirst;

        if (last == null) last = first;

        size++;
    }

    public T pop() {
        T item = first.item;
        first = first.next;
        size--;
        return item;
    }

    public void enqueue(T item) {
        Node oldLast = last;

        last = new Node();
        last.item = item;

        if (oldLast == null) first = last;
        else oldLast.next = last;

        size++;
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        Node node = first;

        if (node == null) return "Steque(empty)";

        s.append("Steque(");
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