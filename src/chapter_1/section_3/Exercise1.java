package algsex.chapter1.section3;

import java.util.*;
import edu.princeton.cs.algs4.*;
import algsex.support.Test;

// 1.3.1 Add a method isFull() to FixedCapacityStackOfStrings.
public class Exercise1 {
    public static void main(String[] _args) {
        int N = 3;
        FixedCapacityStackOfStrings stack = new FixedCapacityStackOfStrings(N);

        for (int i = 0; i < N; i++) {
            Test.assertFalse(stack.isFull());
            stack.push(Integer.toString(i));
        }

        Test.assertTrue(stack.isFull());
        Test.assertRaises(ArrayIndexOutOfBoundsException.class, () -> stack.push(""));
        Test.assertTrue(stack.isFull());
    }

    private static class FixedCapacityStackOfStrings {
        private String[] a; // stack entries
        private int N; // size
        private int cap;

        public FixedCapacityStackOfStrings(int cap) {
            this.cap = cap;
            this.a = new String[cap];
        }

        public boolean isEmpty() { return N == 0; }
        public int size() { return N; }
        public void push(String item) { a[N++] = item; }
        public String pop() { return a[--N]; }
        public boolean isFull() { return N >= cap; }
    }
}
