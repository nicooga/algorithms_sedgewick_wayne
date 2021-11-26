package algsex.chapter1.section4;

import java.util.*;
import edu.princeton.cs.algs4.StdOut;
import algsex.support.Test;
import algsex.support.Stack;
import algsex.support.Steque;

// 1.4.30 Deque with a stack and a steque. Implement a deque with a stack and a steque
// (see Exercise 1.3.32) so that each deque operation takes a constant amortized number
// of stack and steque operations.
public class Exercise30 {
    public static void main(String[] args) {
        try {
            Deque<Integer> d = buildTestDeque();
            for (int i = 1; i <= 12; i++) Test.assertEqual((int) d.popLeft(), i);
            d = buildTestDeque();
            for (int i = 12; i >= 1; i--) Test.assertEqual((int) d.popRight(), i);
        } catch (EmptyDequeError e) {
            assert false : e;
        }
        StdOut.println("Tests passed");
    }

    private static Deque<Integer> buildTestDeque() {
        Deque<Integer> d = new Deque();
        for (int i = 6; i >= 1; i--) d.pushLeft(i);
        for (int i = 7; i <= 12; i++) d.pushRight(i);
        return d;
    }

    private static class Deque<T> {
        private final Stack<T> left = new Stack<>();
        private final Steque<T> right = new Steque<>();

        public int size() {
            return left.size() + right.size();
        }

        public boolean isEmpty() {
            return left.isEmpty() && right.isEmpty();
        }

        public void pushLeft(T item) {
            left.push(item);
        }

        public T popLeft() throws EmptyDequeError {
            if (left.isEmpty()) transferHalfOfItemsFromRightToLeft();
            if (left.isEmpty()) throw new EmptyDequeError();
            return left.pop();
        }

        public void pushRight(T item) {
            right.push(item);
        }

        public T popRight() throws EmptyDequeError {
            if (right.isEmpty()) transferHalfOfItemsFromLeftToRight();
            if (right.isEmpty()) throw new EmptyDequeError();
            return right.pop();
        }

        private void transferHalfOfItemsFromRightToLeft() {
            if (right.size() == 1) {
                left.push(right.pop());
                return;
            }

            int itemsToTransfer = right.size() / 2;
            int itemsThatRemain = right.size() - itemsToTransfer;
            for (int i = 0; i < itemsThatRemain; i++) right.enqueue(right.pop());
            for (int i = 0; i < itemsToTransfer; i++) left.push(right.pop());
        }

        private void transferHalfOfItemsFromLeftToRight() {
            T[] temp = (T[]) new Object[left.size()];
            int mid = (left.size() / 2);
            int index = 0;

            while (!left.isEmpty()) {
                temp[index] = left.pop();
                index++;
            }

            for (int i = mid-1; i >= 0; i--) left.push(temp[i]);
            for (int i = mid; i < temp.length; i++) right.push(temp[i]);
        }
    }

    public static class EmptyDequeError extends Exception {}
}
