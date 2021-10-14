package algsex.chapter1.section5;

import java.util.*;
import edu.princeton.cs.algs4.*;
import algsex.chapter1.section5.Exercise1.InstrumentedUnionFind;
import algsex.support.InstrumentedArray;

// 1.5.2 Do Exercise 1.5.1, but use quick-union (page 224). In addition, draw the forest of
// trees represented by the id[] array after each input pair is processed.
public class Exercise2 {
    public static void main(String[] args) {
        Exercise1.runExperiment(new InstrumentedUnionFindViaQuickUnion(10));
    }

    private static class InstrumentedUnionFindViaQuickUnion implements InstrumentedUnionFind {
        private InstrumentedArray<Integer> id;
        private int count;

        public InstrumentedUnionFindViaQuickUnion(int N) {
            count = N;
            id = new InstrumentedArray(N);
            for (int i = 0; i < N; i++) id.set(i, i);
        }

        public int count() { return count; }
        public boolean connected(int p, int q) { return find(p) == find(q); }

         public void union(int p, int q) {
            int pRootId = find(p);
            int qRootId = find(q);

            if (pRootId == qRootId) return;

            id.set(pRootId, qRootId);

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
