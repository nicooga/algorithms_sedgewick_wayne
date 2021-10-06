package algsex.chapter1.section4;

import java.util.*;
import edu.princeton.cs.algs4.StdOut;
import algsex.support.DoublingRatioTest;

// 1.4.37 Autoboxing performance penalty. Run experiments to determine the perfor-
// mance penalty on your machine for using autoboxing and auto-unboxing. Develop an
// implementation FixedCapacityStackOfInts and use a client such as DoublingRatio
// to compare its performance with the generic FixedCapacityStack<Integer>, for a
// large number of push() and pop() operations.

// Tested with:
// openjdk version "17" 2021-09-14
// OpenJDK Runtime Environment (build 17+35-2724)
// OpenJDK 64-Bit Server VM (build 17+35-2724, mixed mode, sharing)
//
// Conclusions: usage of generics incurs in an small -sometimes unnoticeable- but consistent overhead.
public class Exercise37 {
    public static void main(String[] args) {
        while (true) {
            new NormalStackDoublingRatioTest().run();
            new GenericStackDoublingRatioTest().run();
        }
    }

    private static class NormalStackDoublingRatioTest extends StackDoublingRatioTest {
        protected String label() {
            return "ratios for normal stack (N, time, ratio)";
        }

        protected Stack<String> instantiateStack(int N) {
            return new FixedCapacityStackOfStrings(N);
        }
    }

    private static class GenericStackDoublingRatioTest extends StackDoublingRatioTest {
        protected String label() {
            return "ratios for generic stack (N, time, ratio)";
        }

        protected Stack<String> instantiateStack(int N) {
            return new FixedCapacityStack<String>(N);
        }
    }

    private static abstract class StackDoublingRatioTest extends DoublingRatioTest {
        protected void runExperiment(int N) {
            Stack<String> s = instantiateStack(N);
            for (int i = 0; i < N; i++) s.push("some string");
        }

        abstract protected Stack<String> instantiateStack(int N);
    }

    private static class FixedCapacityStack<T> implements Stack<T> {
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

    private static class FixedCapacityStackOfStrings implements Stack<String> {
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

    private interface Stack<T> {
        public void push(T item); 
    }
}
