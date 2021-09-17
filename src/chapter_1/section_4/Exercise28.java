package algsex.chapter1.section4;

import java.util.*;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;
import algsex.support.Test;

// 1.4.28 Stack with a queue. Implement a stack with a single queue so that each stack
// operations takes a linear number of queue operations. Hint: To delete an item, get all
// of the elements on the queue one at a time, and put them at the end, except for the last
// one which you should delete and return. (This solution is admittedly very inefficient.)
public class Exercise28 {
    public static void main(String[] args) {
        Stack<Integer> s = new Stack<>();

        for (int i = 0; i < 10; i++) s.push(i);
        for (int i = 9; i >= 0; i--) Test.assertEqual((int) s.pop(), i);

        StdOut.println("Tests passed");
    }

    private static class Stack<T> {
        private final Queue<T> q = new Queue<>();
        private int reversedItems = 0;

        public void push(T item)  {
            q.enqueue(item);
        }

        // The cost of popping N items is ~N amortized across N pushes.
        public T pop() {
            if (reversedItems == 0) reverseItems();
            T item = q.dequeue();
            reversedItems--;
            return item;
        }

        private void reverseItems() {
            T[] temp = (T[]) new Object[q.size()];
            int index = 0;

            while (!q.isEmpty()) {
                temp[index] = q.dequeue();
                index++;
            }

            for (int i = temp.length-1; i >= 0; i--) q.enqueue(temp[i]);

            reversedItems = temp.length;
        }
    }
}
