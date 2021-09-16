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
        // for (int i = 8; i > 0; i *= 2) {
        //     Queue<Integer> q = new Queue<>();

        //     for (int j = 0; j < i; j++) {
        //         q.enqueue(j);
        //         Test.assertEqual(q.operations(), i-3);
        //     }
        // }

        Queue<Integer> q = new Queue<>();


        // StdOut.printf("in\tout\tin\tout\toperations\n");
        // StdOut.printf("T?\ti\tin\tout\toperations\n");
        // StdOut.println("=========================================");

        for (int i = 0; i < 32; i++) {
            // int inBefore = q.in.size();
            // int outBefore = q.out.size();
            // boolean transferred = q.nextPushToTransferOn == q.push;

            q.enqueue(i);

            // int inAfter = q.in.size();
            // int outAfter = q.out.size();

            // StdOut.printf((transferred ? "T" : "") + "\t" + i + "\t" + inBefore + "\t" + outBefore + "\t" + q.operations() + "\n");
            // StdOut.printf(inBefore + "\t" + outBefore + "\t" + inAfter + "\t" + outAfter + "\t" + q.operations() + "\n");
            // StdOut.println("-----------------------------------------");
        }

        StdOut.println(q.operations());

        StdOut.println("Tests passed");
    }

    private static class Queue<T> {
        private final InstrumentedStack<T> in = new InstrumentedStack<>("In");
        private final InstrumentedStack<T> out = new InstrumentedStack<>("Out");
        private final InstrumentedStack<T> temp = new InstrumentedStack<>("Temp");
        private final int bufferSize = 20;
        private int push = 1;
        private int nextPushToTransferOn = 1;

        public int operations() {
            return in.operations() + out.operations() + temp.operations();
        }

        public void resetOperationsCounter() {
            in.resetOperationsCounter();
            out.resetOperationsCounter();
            temp.resetOperationsCounter();
        }

        public void enqueue(T item) {
            in.push(item);

            if (push == nextPushToTransferOn) {
                transferFromInToOut();
                nextPushToTransferOn *= 2;
            }

            push++;
        }

        public T dequeue() {
            return out.pop();
        }

        private void transferFromInToOut() {
            transfer(out, temp);
            transfer(in, out);
            transfer(temp, out);
        }

        private void transfer(InstrumentedStack<T> lhs, InstrumentedStack<T> rhs) {
            // StdOut.printf("== Transferring items from %s to %s ==\n", lhs.label, rhs.label);
            while (!lhs.isEmpty()) rhs.push(lhs.pop());
        }
    }

    private static class InstrumentedStack<T> extends Stack {
        private int operations = 0;
        private String label;   

        public InstrumentedStack(String label) {
            this.label = label;
        }

        @Override
        public void push(Object item) {
            // StdOut.printf("(%s) <<- %s\n", label, item);
            super.push(item);
            operations++;
        }

        @Override
        public T pop() {
            T item = (T) super.pop();
            // StdOut.printf("(%s) ->> %s\n", label, item);
            operations++;
            return item;
        }

        public int operations() {
            return operations;
        }

        public void resetOperationsCounter() {
            this.operations = 0; 
        }
    }
}
