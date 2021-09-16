package algsex.chapter1.section4;

import java.util.*;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;
import algsex.support.Test;

// 1.4.27 Queue with two stacks. Implement a queue with two stacks so that each queue
// operation takes a constant amortized number of stack operations. Hint: If you push
// elements onto a stack and then pop them all, they appear in reverse order. If you repeat
// this process, they’re now back in order.
public class Exercise27 {
    public static void main(String[] args) {
        int N = 100;
        Queue<Integer> q = new Queue<>();
        for (int i = 0; i < N; i++) q.enqueue(i);
        for (int i = 0; i < N; i++) Test.assertEqual((int) q.dequeue(), i);
        StdOut.println("Tests passed");
    }

    private static class Queue<T> {
        private final Stack<T> in = new Stack<>();
        private final Stack<T> out = new Stack<>();

        public void enqueue(T item) {
            in.push(item);
        }

        public T dequeue() {
            if (out.isEmpty()) transferFromInToOut();
            return out.pop();
        }

        private void transferFromInToOut() {
            while (!in.isEmpty()) out.push(in.pop());
        }
    }
}
