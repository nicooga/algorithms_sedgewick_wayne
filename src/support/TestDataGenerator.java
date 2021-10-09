package algsex.support;

import java.util.Arrays;
import java.util.Set;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class TestDataGenerator {
    private final int min;
    private final int max;
    private boolean verbose = false;
    private final Map<Integer, Integer[]> cache = new HashMap<>();

    public TestDataGenerator(int min, int max) {
        this.min = min;
        this.max = max;
    }

    public TestDataGenerator(int min, int max, boolean verbose) {
        this.min = min;
        this.max = max;
        this.verbose = verbose;
    }

    public void prime(int minN, int maxN) {
        assert minN <= maxN;

        println("Priming test data ...");

        for (int N = minN; N <= maxN && N > 0; N *= 2)
            getOrCreateTestData(N);

        println("Finished priming test data");
    }

    public int[] getOrCreateTestData(int N) {
        if (!cache.containsKey(N)) cache.put(N, createTestData(N));
        return toIntArray(cache.get(N));
    }

    private Integer[] createTestData(int N) {
        assert intervalSize() >= N :
            String.format("Interval size %d is too small. We can't generate a unique integer set of size %d.", intervalSize(), N);

        println("Generating integer set of size " + N);

        Set<Integer> s = new HashSet<>();

        for (int i = 0; i < N; i++) {
            int x;
            do x = StdRandom.uniform(min, max);
            while (s.contains(x));
            s.add(x);
        }

        assert s.size() == N;

        Integer[] a = new Integer[N];
        int index = 0;
        for (Integer x : s) a[index++] = x;

        return a;
    }

    private int intervalSize() {
        return max - min + 1;
    }

    private void println(String s) {
        if (!verbose) return;
        StdOut.println(s);
    }

    private int[] toIntArray(Integer[] a) {
        return Arrays.stream(a).mapToInt(Integer::intValue).toArray();
    }
}