package algsex.support.sorting;

import edu.princeton.cs.algs4.StdRandom;
import algsex.support.Test;

public class SortingTest {
    private final int MAX_N = 100;

    public void run(Sort sort) {
        int N = StdRandom.uniform(MAX_N);
        Comparable[] a = new Double[N];

        for (int i = 0; i < N; i++)
            a[i] = StdRandom.uniform(0.0, 1.0);

        sort.sort(a);

        for (int i = 1; i < N; i++)
            Test.assertTrue(
                a[i].compareTo(a[i-1]) > 0,
                "a[i] is not greater than a[i-1]. Sorting algorithm does not pass tests."
            );
    }
}