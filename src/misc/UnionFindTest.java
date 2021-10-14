package algsex.misc;

import edu.princeton.cs.algs4.StdOut;
import algsex.support.Test;
import algsex.misc.UnionFind;

public class UnionFindTest {
    private static final int SITES = 8;

    public static void main(String[] args) {
        runTest(new UnionFindViaQuickFind(SITES));
        runTest(new UnionFindViaQuickUnion(SITES));
        runTest(new UnionFindViaWeightedQuickUnion(SITES));

        StdOut.println("Tests passed");
    }

    private static void runTest(UnionFind uf) {
        uf.union(1, 3);
        uf.union(4, 3);

        Test.assertTrue(uf.connected(4, 1));

        uf.union(4, 6);

        Test.assertTrue(uf.connected(6, 1));
        Test.assertFalse(uf.connected(0, 1));
        Test.assertFalse(uf.connected(5, 1));
        Test.assertFalse(uf.connected(7, 1));
    }
}