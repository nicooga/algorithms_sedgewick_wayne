package algsex.chapter1.section5;

import java.util.ArrayList;
import edu.princeton.cs.algs4.*;
import algsex.support.union_find.*;
import algsex.support.Test;

// 1.5.20 Dynamic growth. Using linked lists or a resizing array, develop a weighted
// quick-union implementation that removes the restriction on needing the number of
// objects ahead of time. Add a method newSite() to the API, which returns an int
// identifier.
public class Exercise20 {
    public static void main(String[] args) {
        UnionFindViaWeightedQuickUnion uf = new UnionFindViaWeightedQuickUnion();
        for (int i = 0; i < 8; i++) uf.newSite();
        UnionFindTest.runTest(uf);

        uf = new UnionFindViaWeightedQuickUnion();

        Test.assertEqual(uf.newSite(), 0);
        Test.assertEqual(uf.count(), 1);

        Test.assertEqual(uf.newSite(), 1);
        Test.assertEqual(uf.count(), 2);

        Test.assertEqual(uf.newSite(), 2);
        Test.assertEqual(uf.count(), 3);

        uf.union(0, 9);
        Test.assertEqual(uf.count(), 9);

        Test.assertEqual(uf.newSite(), 10);
    }

    private static class UnionFindViaWeightedQuickUnion implements UnionFind {
        private ArrayList<Integer> id = new ArrayList<>();
        private ArrayList<Integer> sz = new ArrayList<>();
        private int count = 0;
        private int N = 0;

        public int count() { return count; }
        public boolean connected(int p, int q) { return find(p) == find(q); }

         public void union(int p, int q) {
            if (p > N-1) upsize(p);
            if (q > N-1) upsize(q);

            int pRoot = find(p);
            int qRoot = find(q);

            if (pRoot == qRoot) return;

            if (sz.get(pRoot) > sz.get(qRoot)) point(pRoot, qRoot);
            else point(pRoot, qRoot);

            count--;
        }

        public int find(int p) {
            while (p != id.get(p)) p = id.get(p);
            return p;
        }

        public int newSite() {
            upsize(N);
            return N-1;
        }

        private void point(int source, int target) {
            id.set(source, target);
            sz.set(target, sz.get(source) + sz.get(target));
        }

        // upsize so `x` is indexable
        private void upsize(int x) {
            for (int i = N; i <= x; i++) {
                id.add(i);
                sz.add(1);
                count++;
                N++;
            }
        }
    }
}
