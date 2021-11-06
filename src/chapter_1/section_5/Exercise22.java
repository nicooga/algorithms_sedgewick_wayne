package algsex.chapter1.section5;

import java.util.*;
import edu.princeton.cs.algs4.*;
import algsex.chapter1.section5.Exercise17.ErdosRenyi;
import algsex.support.DoublingRatioTestV2;
import algsex.misc.UnionFind;
import algsex.misc.UnionFindViaQuickFind;
import algsex.misc.UnionFindViaQuickUnion;
import algsex.misc.UnionFindViaWeightedQuickUnion;

// 1.5.22 Doubling test for Erdös-Renyi model. Develop a performance-testing client that
// takes an int value T from the command line and performs T trials of the following ex-
// periment: Use your client from Exercise 1.5.17 to generate random connections, using
// UnionFind to determine connectivity as in our development client, looping until all
// sites are connected. For each N, print the value of N, the average number of connections
// processed, and the ratio of the running time to the previous. Use your program to vali-
// date the hypotheses in the text that the running times for quick-find and quick-union
// are quadratic and weighted quick-union is near-linear.
public class Exercise22 {
    public static void main(String[] args) {
        int T = Integer.parseInt(args[0]);
    }

    private static class ErdosRenyi {
        public static int count(int N, UnionFind uf) {
            int connections = 0;

            while (uf.count() != 1) {
                connections++;

                int p = StdRandom.uniform(N);
                int q = StdRandom.uniform(N);

                if (!uf.connected(p, q)) uf.union(p, q);
            }

            return connections;
        }
    }
}
