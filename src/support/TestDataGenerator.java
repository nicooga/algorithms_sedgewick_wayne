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
    private final Map<Integer, Integer[]> cache = new HashMap<>();

    public TestDataGenerator(int min, int max) {
        this.min = min;
        this.max = max;
    }

    public void prime(int minN, int maxN) {
        assert minN <= maxN;

        StdOut.println("Priming test data ...");

        for (int N = minN; N <= maxN && N > 0; N *= 2)
            getOrCreateTestData(N);

        StdOut.println("Finished priming test data");
    }

    public int[] getOrCreateTestData(int N) {
        if (!cache.containsKey(N)) cache.put(N, createTestData(N));

        Integer[] a = cache.get(N);

        // Trick to cast Integer[] to int[]
        return Arrays.stream(a).mapToInt(Integer::intValue).toArray();
    }

    private Integer[] createTestData(int N) {
        assert intervalSize() >= N;

        StdOut.println("Generating integer set of size " + N);

        Set<Integer> s = new HashSet<>();

        for (int i = 0; i < N; i++) {
            int x;
            do x = StdRandom.uniform(min, max);
            while (s.contains(x));
            s.add(x);
            // StdOut.printf("(%d/%d)\r", i, N);
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
}