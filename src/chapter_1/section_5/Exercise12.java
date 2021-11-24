package algsex.chapter1.section5;

import java.util.Arrays;
import edu.princeton.cs.algs4.*;
import algsex.support.Test;
import algsex.support.union_find.*;

// 1.5.12 Quick-union with path compression. Modify quick-union (page 224) to in-
// clude path compression, by adding a loop to find() that links every site on the path
// from p to the root. Give a sequence of input pairs that causes this method to produce a
// path of length 4. Note: The amortized cost per operation for this algorithm is known to
// be logarithmic.
public class Exercise12 {
    public static void main(String[] args) {
        runBasicTests();
        runDepthExperiment();

        StdOut.println("Tests passed");
    }

    private static void runBasicTests() {
        UnionFindTest.runTest(new UnionFindViaQuickUnionWithPathCompression(8));
    }

    private static void runDepthExperiment() {
        runDepthExperiment(new UnionFindViaQuickUnionWithPathCompression(16));
    }

    public static void runDepthExperiment(InstrumentedUnionFind uf) {
        // Union each consecutive node (0-1, 2-3, and so on)
        for (int i = 0; i <= 14; i += 2) uf.union(i, i+1);
        
        uf.union(1, 3);
        uf.union(5, 7);
        uf.union(9, 11);
        uf.union(13, 15);

        uf.union(3, 7);
        uf.union(11, 15);

        uf.union(7, 15);

        uf.find(0);

        Test.assertEqual(uf.maxDepth(), 4);
    }

    private static class UnionFindViaQuickUnionWithPathCompression implements InstrumentedUnionFind {
        private int maxDepth;
        private int[] id;
        private int count;
    
        public UnionFindViaQuickUnionWithPathCompression(int N) {
            count = N;
            id = new int[N];
            for (int i = 0; i < N; i++) id[i] = i;
        }
    
        public int count() { return count; }
        public boolean connected(int p, int q) { return find(p) == find(q); }
    
         public void union(int p, int q) {
            int pRootId = find(p);
            int qRootId = find(q);
    
            if (pRootId == qRootId) return;
    
            id[pRootId] = qRootId;
    
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
                cur = next;
                next = id[cur];
            }

            return root;
        }

        public int maxDepth() { return maxDepth; }
    }

    public interface InstrumentedUnionFind extends UnionFind {
        int maxDepth();
    }
}
