package algsex.support.union_find;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import algsex.support.Test;
import algsex.support.union_find.*;

public class UnionFindTest {
    private static final int SITES = 8;

    public static void main(String[] args) {
        runTest(new UnionFindViaQuickFind(SITES));
        runTest(new UnionFindViaQuickUnion(SITES));
        runTest(new UnionFindViaWeightedQuickUnion(SITES));
    }

    public static void runTest(UnionFindFactory factory) {
        UnionFind unionFind = factory.generate(SITES);
        runTest(unionFind);
    }

    public static void runTest(UnionFind uf) {
        runTest(uf, SITES);
        StdOut.println("Union-find tests passed for " + uf.getClass());
    }

    private static void runTest(UnionFind uf, int sites) {
        Test.assertEqual(uf.count(), sites);

        for (int i = 0; i < SITES*3; i++) {
            int x = StdRandom.uniform(1, sites);
            int y = StdRandom.uniform(1, sites);
            int w = StdRandom.uniform(1, sites);
            int z = StdRandom.uniform(1, sites);

            uf.union(x, y);
            uf.union(y, w);
            uf.union(z, w);

            Test.assertFalse(uf.connected(x, 0));
            Test.assertTrue(uf.connected(x, x));
            Test.assertTrue(uf.connected(x, y));
            Test.assertTrue(uf.connected(x, w));
            Test.assertTrue(uf.connected(x, z));
            Test.assertFalse(uf.connected(y, 0));
            Test.assertTrue(uf.connected(y, y));
            Test.assertTrue(uf.connected(y, w));
            Test.assertTrue(uf.connected(y, z));
            Test.assertFalse(uf.connected(y, 0));
            Test.assertTrue(uf.connected(w, w));
            Test.assertTrue(uf.connected(w, z));
            Test.assertFalse(uf.connected(z, 0));
            Test.assertTrue(uf.connected(z, z));

            int firstComponent = uf.find(x);

            Test.assertEqual(firstComponent, uf.find(y));
            Test.assertEqual(firstComponent, uf.find(w));
            Test.assertEqual(firstComponent, uf.find(z));
        }

        Test.assertEqual(uf.count(), 2);

        uf.union(0, 1);

        Test.assertEqual(uf.count(), 1);
    }
}