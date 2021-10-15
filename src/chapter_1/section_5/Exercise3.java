package algsex.chapter1.section5;

import java.util.*;
import edu.princeton.cs.algs4.*;
import algsex.chapter1.section5.Exercise1.InstrumentedUnionFind;
import algsex.support.InstrumentedArray;

// 1.5.3 Do Exercise 1.5.1, but use weighted quick-union (page 228).

// Results:
// id[]: [2, 2, 2, 4, 2, 4, 6, 2, 8, 0]
// array accesses: 43
public class Exercise3 {
    public static void main(String[] args) {
        Exercise1.runExperiment(new InstrumentedUnionFindViaWeightedQuickUnion(10));
    }

    private static class InstrumentedUnionFindViaWeightedQuickUnion implements InstrumentedUnionFind {
        private InstrumentedArray<Integer> id;
        private int[] sz;
        private int count;
    
        public InstrumentedUnionFindViaWeightedQuickUnion(int N) {
            count = N;
    
            id = new InstrumentedArray(N);
            for (int i = 0; i < N; i++) id.set(i, i);
    
            sz = new int[N];
            for (int i = 0; i < N; i++) sz[i] = 1;
        }
    
        public int count() { return count; }
        public boolean connected(int p, int q) { return find(p) == find(q); }
    
         public void union(int p, int q) {
            int pRoot = find(p);
            int qRoot = find(q);
    
            if (pRoot == qRoot) return;
    
            if (sz[pRoot] > sz[qRoot]) {
                id.set(qRoot, pRoot);
                sz[pRoot] += sz[qRoot];
            } else {
                id.set(pRoot, qRoot);
                sz[qRoot] += sz[pRoot];
            }
    
            count--;
        }
    
        public int find(int p) {
            while (p != id.get(p)) p = id.get(p);
            return p;
        }

        public int arrayAccesses() { return id.accesses(); }
        public InstrumentedArray<Integer> id() { return id; }
    }
}