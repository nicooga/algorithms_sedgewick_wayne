package algsex.chapter1.section5;

import java.util.*;
import edu.princeton.cs.algs4.*;
import algsex.misc.UnionFind;
import algsex.misc.UnionFindTest;
import algsex.misc.UnionFindViaQuickFind;
import algsex.support.DoublingRatioTest;

// 1.5.11 Implement weighted quick-find, where you always change the id[] entries of
// the smaller component to the identifier of the larger component. How does this change
// affect performance?

// Conclusion:
// We may have to update less ids, but the complexity of the algorithm does not improve.
// The speed up is not guaranteed either, for instance, when we are have to merge trees that are roughly the same size.
// We still have to iterate over all the sites for each call to `union(p, q)`, and we have the extra overhead of having to keep track of tree sizes.
// This means an extra 2 array accesses per updated site.
//
// Doubling ratio tests confirm that the weighted version is just slightly slower for uniform input.
public class Exercise11 {
    public static void main(String[] args) {
        runBasicTests();
        runDoublingRatioTests();
    }

    private static void runBasicTests() {
        UnionFindTest.runTest(new UnionFindViaWeightedQuickFind(8));
        StdOut.println("Tests passed");
    }

    private static void runDoublingRatioTests() {
        new RegularAlgoTest().run();
        new WeightedAlgoTest().run();
    }

    private static class RegularAlgoTest extends BaseTest {
        @Override
        protected String label() { return "Quick find"; }
        @Override
        protected UnionFind createUnionFind(int N) { return new UnionFindViaQuickFind(N); }
    }

    private static class WeightedAlgoTest extends BaseTest {
        @Override
        protected String label() { return "Weighted quick find"; }
        @Override
        protected UnionFind createUnionFind(int N) { return new UnionFindViaWeightedQuickFind(N); }
    }

    private static abstract class BaseTest extends DoublingRatioTest {
        private int[][] pairs;

        abstract protected UnionFind createUnionFind(int N);

        @Override
        protected boolean iterationCondition(int N) {
            return N <= 8388608;
        }

        @Override
        protected void beforeExperiment(int N) {
            pairs = generateTestPairs(N);
        }

        @Override
        protected void runExperiment(int N) {
            UnionFind uf = createUnionFind(N);

            assert pairs.length <= N;

            for (int i = 0; i < pairs.length; i++) {
                int[] pair = pairs[0];
                uf.union(pair[0], pair[1]);
            }
        }

        private int[][] generateTestPairs(int N) {
            int pairCount = (int) (N / 3.0) * 2;
            int[][] pairs = new int[pairCount][2];

            for (int i = 0; i < pairCount; i++) {
                int p = StdRandom.uniform(0, N-1);
                int q = StdRandom.uniform(0, N-1);

                assert PairValidator.validPair(p, q, N);

                pairs[i] = new int[] { p, q };
            }

            return pairs;
        }
    }

    private static class UnionFindViaWeightedQuickFind implements UnionFind {
        private int[] id;
        private int[] sz;
        private int count;

        public UnionFindViaWeightedQuickFind(int N) {
            count = N;
            id = new int[N];
            sz = new int[N];
            for (int i = 0; i < N; i++) id[i] = i;
            for (int i = 0; i < N; i++) sz[i] = 1;
        }

        public int count() { return count; }
        public boolean connected(int p, int q) { return find(p) == find(q); }

         public void union(int p, int q) {
            assert PairValidator.validPair(p, q, id.length);

            int pRoot = find(p);
            int qRoot = find(q);

            if (pRoot == qRoot) return;

            if (sz[pRoot] > sz[qRoot]) point(qRoot, pRoot);
            else point(pRoot, qRoot);

            count--;
        }

        public int find(int p) { return id[p]; }

        private void point(int oldTarget, int newTarget) {
            for (int i = 0; i < id.length; i++)
                if (id[i] == oldTarget) {
                    id[i] = newTarget;
                    sz[i] = 1;
                    sz[newTarget]++;
                }
        }
    }

    private static class PairValidator {
        private static boolean validPair(int[] pair, int N) {
            int p = pair[0];
            int q = pair[1];
            return validPair(p, q, N);
        }

        public static boolean validPair(int p, int q, int N) {
            return validSite(p, N) && validSite(q, N);
        }

        private static boolean validSite(int site, int N)  {
            return within(site, 0, N-1);
        }

        private static boolean within(int x, int min, int max) {
            return x >= min && x <= max;
        }
    }
}
