package algsex.chapter1.section4;

import edu.princeton.cs.algs4.StdOut;
import algsex.support.Test;
import algsex.support.Stack;

// 1.4.31 deque with three stacks. implement a deque with three stacks so that each
// deque operation takes a constant amortized number of stack operations.
public class Exercise31 {
    public static void main(String[] args) throws EmptyDequeError {
        Deque<Integer> d = buildTestDeque();

        for (int i = 1; i <= 12; i++) {
            Test.assertEqual((int) d.size(), 13-i);
            Test.assertEqual((int) d.popLeft(), i);
        }

        d = buildTestDeque();

        for (int i = 12; i >= 1; i--) {
            Test.assertEqual((int) d.size(), i);
            Test.assertEqual((int) d.popRight(), i);
        }

        StdOut.println("Tests passed");
    }

    private static Deque<Integer> buildTestDeque() {
        Deque<Integer> d = new Deque<>();
        for (int i = 6; i >= 1; i--) d.pushLeft(i);
        for (int i = 7; i <= 12; i++) d.pushRight(i);
        return d;
    }
    private static class Deque<T> {
        private final Stack<T> left = new Stack<>();
        private final Stack<T> right = new Stack<>();
        private final Stack<T> temp = new Stack<>();

        public int size() {
            assert temp.isEmpty();
            return left.size() + right.size();
        }

        public void pushLeft(T item) {
            left.push(item);
        }

        public void pushRight(T item) {
            right.push(item);
        }

        // Whenever either left or right stacks become empty, we distribute the items on the other stack evenly.
        // The cost of this redistribution is ~c*X given we have X items in the other stack and some constant c.
        // Since we distribute them evenly, we can't be forced to distribute the items again unless at least X/2 pops happen on either side.
        // This means that the cost of distributing items is amortized across at least X/2 pops, up to X pops.
        // Finally, the max cost of this operation is an amortized ~(c*X)/(X/2), which simplifies to ~2c, and the min cost is just c.
        public T popLeft() throws EmptyDequeError {
            if (left.isEmpty()) distributeItems(right, left);
            if (left.isEmpty()) throw new EmptyDequeError();
            return left.pop();
        }

        // Same cost as method above.
        public T popRight() throws EmptyDequeError {
            if (right.isEmpty()) distributeItems(left, right);
            if (right.isEmpty()) throw new EmptyDequeError();
            return right.pop();
        }

        // Given we have to distribute X items, the total cost for this operation is ~3X
        private void distributeItems(Stack<T> source, Stack<T> destination) {
            assert temp.isEmpty();
            int itemsThatRemain = source.size() / 2;
            transfer(source, temp, itemsThatRemain); // cost: 2 * floor(X/2)
            transfer(source, destination); // cost: 2 * (X - floor(X/2))
            transfer(temp, source); // cost: 2 * floor(X/2)
        }

        // Given we have to transfer X items, the cost is X pops and X pushes, for a total of 2X operations.
        private void transfer(Stack<T> source, Stack<T> destination, int itemsToTransfer) {
            for (int i = 0; i < itemsToTransfer; i++) destination.push(source.pop());
        }

        // Same cost as above method
        private void transfer(Stack<T> source, Stack<T> destination) {
            while (!source.isEmpty()) destination.push(source.pop());
        }
    }

    private static class EmptyDequeError extends Exception {}
}
