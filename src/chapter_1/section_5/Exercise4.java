package algsex.chapter1.section5;

import java.util.*;
import edu.princeton.cs.algs4.*;
import algsex.support.InstrumentedArray;
import algsex.support.Test;
import algsex.support.union_find.*;

// 1.5.4 Show the contents of the sz[] and id[] arrays and the number of array accesses
// for each input pair corresponding to the weighted quick-union examples in the text
// (both the reference input and the worst-case input).
public class Exercise4 {
    public static void main(String[] args) {
        InstrumentedUnionFind uf = new InstrumentedUnionFindViaWeightedQuickUnion(10);

        StdOut.println("Running reference case input");
        StdOut.println("======================");
        union(uf, 4, 3);
        union(uf, 3, 8);
        union(uf, 6, 5);
        union(uf, 9, 4);
        union(uf, 2, 1);
        union(uf, 8, 9);
        union(uf, 5, 0);
        union(uf, 7, 2);
        union(uf, 6, 1);
        union(uf, 1, 0);
        union(uf, 6, 7);

        assertConnected(uf, new int[] { 3, 4, 8, 9 });
        assertConnected(uf, new int[] { 0, 1, 2, 5, 6, 7 });

        StdOut.println("");
        StdOut.println("Running worst-case input");
        StdOut.println("========================");

        uf = new InstrumentedUnionFindViaWeightedQuickUnion(8);

        union(uf, 0, 1);
        union(uf, 2, 3);
        union(uf, 4, 5);
        union(uf, 6, 7);
        union(uf, 0, 2);
        union(uf, 4, 6);
        union(uf, 0, 4);

        assertConnected(uf, new int[] { 0, 1, 2, 3, 4, 5, 6, 7 });
    }

    private static void union(InstrumentedUnionFind uf, int p, int q) {
        StdOut.printf("union for pair %s-%s\n", p, q);
        uf.resetCounters();
        uf.union(p, q);
        printStatistics(uf);
        StdOut.println("===");
    }

    private static void printStatistics(InstrumentedUnionFind uf) {
        StdOut.println("arrayAccesses: " + uf.arrayAccesses());
        StdOut.println("id[]: " + uf.id());
        StdOut.println("sz[]: " + uf.sz());
    }

    private static void assertConnected(InstrumentedUnionFind uf, int[] sites) {
        int component = uf.find(sites[0]);

        for (int i = 1; i < sites.length; i++)
            Test.assertEqual(
                uf.find(sites[i]),
                component,
                String.format("site %s is not connected", sites[0])
            );
    }

    private static class InstrumentedUnionFindViaWeightedQuickUnion implements InstrumentedUnionFind {
        private InstrumentedArray<Integer> id;
        private InstrumentedArray<Integer> sz;
        private int count;
    
        public InstrumentedUnionFindViaWeightedQuickUnion(int N) {
            count = N;
            id = new InstrumentedArray(N);
            sz = new InstrumentedArray(N);

            for (int i = 0; i < N; i++) id.set(i, i);
            for (int i = 0; i < N; i++) sz.set(i, 1);
        }
    
        public int count() { return count; }
        public boolean connected(int p, int q) { return find(p) == find(q); }
    
         public void union(int p, int q) {
            int pRoot = find(p);
            int qRoot = find(q);
    
            if (pRoot == qRoot) return;
    
            if (sz.get(pRoot) < sz.get(qRoot)) {
                id.set(pRoot, qRoot);
                sz.set(qRoot, sz.get(qRoot) + sz.get(pRoot));
            } else {
                id.set(qRoot, pRoot);
                sz.set(pRoot, sz.get(pRoot) + sz.get(qRoot));
            }
    
            count--;
        }
    
        public int find(int p) {
            while (p != id.get(p)) p = id.get(p);
            return p;
        }

        public int arrayAccesses() { return id.accesses() + sz.accesses(); }

        public InstrumentedArray<Integer> id() { return id; }
        public InstrumentedArray<Integer> sz() { return sz; }

        public void resetCounters() {
            id.resetCounters();
            sz.resetCounters();
        }
    }

    public interface InstrumentedUnionFind extends UnionFind {
        InstrumentedArray<Integer> id();
        InstrumentedArray<Integer> sz();
        int arrayAccesses();
        void resetCounters();
    }
}
