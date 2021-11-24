package algsex.chapter1.section5;

import edu.princeton.cs.algs4.*;
import algsex.support.union_find.*;
import algsex.chapter1.section5.Exercise12;
import algsex.chapter1.section5.Exercise12.InstrumentedUnionFind;

// 1.5.13 Weighted quick-union with path compression. Modify weighted quick-union
// (Algorithm 1.5) to implement path compression, as described in Exercise 1.5.12.
// Give a sequence of input pairs that causes this method to produce a tree of height 4.
// Note: The amortized cost per operation for this algorithm is known to be bounded by a
// function known as the inverse Ackermann function and is less than 5 for any conceivable
// practical value of N.
public class Exercise13 {
    public static void main(String[] args) {
        runBasicTests();
        runDepthExperiment();

        StdOut.println("Tests passed");
    }

    private static void runBasicTests() {
        UnionFindTest.runTest(new UnionFindViaWeightedQuickUnionWithPathCompression(8));
    }

    private static void runDepthExperiment() {
        Exercise12.runDepthExperiment(new UnionFindViaWeightedQuickUnionWithPathCompression(16));
    }

    public static class UnionFindViaWeightedQuickUnionWithPathCompression implements InstrumentedUnionFind {
        private int[] id;
        private int[] sz;
        private int count;
        private int maxDepth;

        public UnionFindViaWeightedQuickUnionWithPathCompression(int N) {
            count = N;

            id = new int[N];
            for (int i = 0; i < N; i++) id[i] = i;

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
                id[qRoot] = pRoot;
                sz[pRoot] += sz[qRoot];
            } else {
                id[pRoot] = qRoot;
                sz[qRoot] += sz[pRoot];
            }

            count--;
        }

        public int find(int p) {
            int root = p;
            int partialDepth = 0;

            while (root != id[root]) {
                partialDepth++;
                root = id[root];
            }

            if (partialDepth > maxDepth)
                maxDepth = partialDepth;

            int cur = p;
            int next = id[cur];

            while (id[cur] != root) {
                id[cur] = root;
                sz[root]--;
                cur = next;
                next = id[cur];
            }

            return root;
        }

        public int maxDepth() { return maxDepth; }
    }
}
