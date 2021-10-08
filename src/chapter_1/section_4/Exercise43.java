package algsex.chapter1.section4;

import java.util.*;
import edu.princeton.cs.algs4.*;
import algsex.support.Test;
import algsex.support.DoublingRatioTest;

// 1.4.43 Resizing arrays versus linked lists. Run experiments to validate the hypothesis
// that resizing arrays are faster than linked lists for stacks (see Exercise 1.4.35 and Exer-
// cise 1.4.36). Do so by developing a version of DoublingRatio that computes the ratio
public class Exercise43 {
    public static void main(String[] args) {
        runBasicTests();
        // runDoublingRatioTests();
    }

    private static void runBasicTests() {
        runBasicTests(new ResizingArrayStack<Integer>());
        runBasicTests(new Stack<Integer>());

        StdOut.println("Tests passed");
    }

    private static void runBasicTests(IStack<Integer> s) {
        Test.assertTrue(s.isEmpty());
        Test.assertEqual(s.size(), 0);

        for (int i = 1; i <= 3; i++)  {
            s.push(i);
            Test.assertEqual(s.size(), i);
        }

        Test.assertFalse(s.isEmpty());

        int[] iteratorResults = new int[3];
        int index = 0;

        for (Integer i : s) iteratorResults[index++] = i;

        Test.assertArrayEquals(iteratorResults, new int[] { 3, 2, 1});

        for (int i = 3; i >= 1; i--) {
            Test.assertEqual(s.size(), i);
            Test.assertEqual((int) s.pop(), i);
        }

        Test.assertEqual(s.size(), 0);
        Test.assertTrue(s.isEmpty());
    }

    // private static class BaseDoublingRatioTest extends DoublingRatioTest {
    //     @Override
    //     protected void runExperiment(int N) {
    //         doRunExperiment();
    //     }
    // }

    private static class ResizingArrayStack<Item> implements IStack<Item> {
        private Item[] a = (Item[]) new Object[1];
        private int N = 0;
        public boolean isEmpty() { return N == 0; }
        public int size() { return N; }

        private void resize(int max) {
            Item[] temp = (Item[]) new Object[max];
            for (int i = 0; i < N; i++)
            temp[i] = a[i];
            a = temp;
        }

        public void push(Item item) {
            if (N == a.length) resize(2*a.length);
                a[N++] = item;
        }

        public Item pop() {
            Item item = a[--N];
            a[N] = null; // Avoid loitering (see text).
            if (N > 0 && N == a.length/4) resize(a.length/2);
                return item;
        }

        public Iterator<Item> iterator() { return new ReverseArrayIterator(); }

        private class ReverseArrayIterator implements Iterator<Item> {
            private int i = N;
            public boolean hasNext() { return i > 0; }
            public Item next() { return a[--i]; }
            public void remove() { }
        }
    }

    private static class Stack<Item> implements IStack<Item> {
        private Node first;
        private int N;

        private class Node {
            Item item;
            Node next;
        }

        public boolean isEmpty() { return first == null; }
        public int size() { return N; }
        public void push(Item item) {
            Node oldfirst = first;
            first = new Node();
            first.item = item;
            first.next = oldfirst;
            N++;
        }

        public Item pop() {
            Item item = first.item;
            first = first.next;
            N--;
            return item;
        }

        public Iterator<Item> iterator() { return new ReverseArrayIterator(); }

        private class ReverseArrayIterator implements Iterator<Item> {
            private Node node = first;

            public boolean hasNext() { return node != null; }

            public Item next() {
                Item item = node.item;
                node = node.next;
                return item;
            }

            public void remove() { }
        }
    }

    private interface IStack<Item> extends Iterable<Item> {
        public boolean isEmpty();
        public int size();
        public void push(Item i);
        public Item pop();
    }
}
