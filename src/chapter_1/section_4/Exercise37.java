package algsex.chapter1.section4;

import java.util.*;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;

// 1.4.37 Autoboxing performance penalty. Run experiments to determine the perfor-
// mance penalty on your machine for using autoboxing and auto-unboxing. Develop an
// implementation FixedCapacityStackOfInts and use a client such as DoublingRatio
// to compare its performance with the generic FixedCapacityStack<Integer>, for a
// large number of push() and pop() operations.
public class Exercise37 {
    public static void main(String[] args) {
        runDoublingRatioTestForNormalStack();
        runDoublingRatioTestForGenericStack();
    }

    private static void runDoublingRatioTestForNormalStack() {
        StdOut.println("ratios for normal stack: ");

        double prevTime = runTimeTrialForNormalStack(1);

        for (int N = 2; N > 0; N *= 2) {
            double time = runTimeTrialForNormalStack(N);
            double ratio = time/prevTime;
            StdOut.printf("doubling ratio: %5.1f\n", ratio);
            prevTime = time;
        }
    }

    private static double runTimeTrialForNormalStack(int N) {
        Stopwatch timer = new Stopwatch();
        FixedCapacityStackOfStrings s = new FixedCapacityStackOfStrings(N);
        for (int i = 0; i < N; i++) s.push("some string");
        return timer.elapsedTime();
    }

    private static void runDoublingRatioTestForGenericStack() {
        StdOut.println("ratios for generic stack: ");

        double prevTime = runTimeTrialForGenericStack(1);

        for (int N = 2; N > 0; N *= 2) {
            double time = runTimeTrialForGenericStack(N);
            double ratio = time/prevTime;
            StdOut.printf("%5.1f\n", ratio);
            prevTime = time;
        }
    }

    private static double runTimeTrialForGenericStack(int N) {
        Stopwatch timer = new Stopwatch();
        FixedCapacityStack<String> s = new FixedCapacityStack<>(N);
        for (int i = 0; i < N; i++) s.push("some string");
        return timer.elapsedTime();
    }

    private static class FixedCapacityStack<T> {
        private final T[] a;
        private int N;

        public FixedCapacityStack(int cap) {
            a = (T[]) new Object[cap];
        }

        public boolean isEmpty() {
            return N == 0;
        }

        public int size() {
            return N;
        }

        public void push(T item) {
            a[N++] = item;
        }

        public T pop() {
            return a[--N];
        }
    }

    private static class FixedCapacityStackOfStrings {
        private final String[] a;
        private int N;

        public FixedCapacityStackOfStrings(int cap) {
            a = new String[cap];
        }

        public boolean isEmpty() {
            return N == 0;
        }

        public int size() {
            return N;
        }

        public void push(String item) {
            a[N++] = item;
        }

        public String pop() {
            return a[--N];
        }
    }
}
