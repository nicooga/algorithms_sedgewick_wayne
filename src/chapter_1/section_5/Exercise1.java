package algsex.chapter1.section5;

import java.util.*;
import edu.princeton.cs.algs4.*;
import algsex.support.InstrumentedArray;
import algsex.support.union_find.*;

// 1.5.1 Show the contents of the id[] array and the number of times the ar-
// ray is accessed for each input pair when you use quick-find for the sequence
// 9-0 3-4 5-8 7-2 2-1 5-7 0-3 4-2.

// Results:
// id[]: [1, 1, 1, 1, 1, 1, 6, 1, 8, 1]
// array accesses: 107
public class Exercise1 {
    public static void main(String[] args) {
        runExperiment(new InstrumentedUnionFindViaQuickFind(10));
    }

    public static void runExperiment(InstrumentedUnionFind uf) {
        uf.union(9, 0);
        uf.union(3, 4);
        uf.union(5, 4);
        uf.union(7, 2);
        uf.union(2, 1);
        uf.union(5, 7);
        uf.union(0, 3);
        uf.union(4, 2);

        StdOut.println("id[]: " + uf.id());
        StdOut.println("array accesses: " + uf.arrayAccesses());
    }

    private static class InstrumentedUnionFindViaQuickFind implements InstrumentedUnionFind {
        private InstrumentedArray<Integer> id;
        private int count;

        public InstrumentedUnionFindViaQuickFind(int N) {
            count = N;
            id = new InstrumentedArray<>(N);
            for (int i = 0; i < N; i++) id.set(i, i);
        }

        public int count() { return count; }
        public boolean connected(int p, int q) { return find(p) == find(q); }

         public void union(int p, int q) {
            int pID = find(p);
            int qID = find(q);

            if (pID == qID) return;

            for (int i = 0; i < id.length(); i++)
                if (id.get(i) == pID) id.set(i, qID);

            count--;
        }

        public int find(int p) { return id.get(p); }

        public int arrayAccesses() { return id.accesses(); }
        public InstrumentedArray<Integer> id() { return id; }
    }

    public interface InstrumentedUnionFind extends UnionFind {
        InstrumentedArray<Integer> id();
        int arrayAccesses();
    }
}
