package algsex.chapter1.section4;

import java.util.Arrays;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;
import algsex.support.Test;

// 1.4.29 Steque with two stacks. Implement a steque with two stacks so that each steque
// operation (see Exercise 1.3.32) takes a constant amortized number of stack operations.
public class Exercise29 {
    public static void main(String[] args) {
        Steque<Integer> s = new Steque<>();

        s.push(1);
        s.push(2);
        s.push(3);
        s.enqueue(4);
        s.enqueue(5);
        s.enqueue(6);

        Test.assertEqual((int) s.pop(), 3);
        Test.assertEqual((int) s.pop(), 2);
        Test.assertEqual((int) s.pop(), 1);
        Test.assertEqual((int) s.pop(), 4);
        Test.assertEqual((int) s.pop(), 5);
        Test.assertEqual((int) s.pop(), 6);

        StdOut.println("Tests passed");
    }

    private static class Steque<T> {
        private final Stack<T> head = new Stack<>();
        private final Stack<T> tail = new Stack<>();

        public void push(T item) { head.push(item); }

        public T pop() {
            if (head.isEmpty()) transferItemsFromTailToHead();
            return head.pop();
        }

        private void enqueue(T item) { tail.push(item); }

        private void transferItemsFromTailToHead() {
            assert head.isEmpty();
            while (!tail.isEmpty()) head.push(tail.pop());
        }
    }
}
