package algsex.chapter1.section5;

import java.util.*;
import edu.princeton.cs.algs4.*;
import algsex.chapter1.section5.Exercise23.Connection;
import algsex.support.union_find.*;
import algsex.support.Plot;

// 1.5.26 Amortized plot for Erdös-Renyi. Develop a client that takes an int value N from
// the command line and does an amortized plot of the cost of all operations in the style
// of the plots in the text for the process of generating random pairs of integers between 0
// and N-1, calling connected() to determine if they are connected and then union() if
// not (as in our development client), looping until all sites are connected.
public class Exercise26 {
    private static final Plot plot = new Plot();

    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        // InstrumentedUnionFind unionFind = new UnionFindViaWeightedQuickUnion(N);
        // Connection[] connections = ErdosRenyi.generate(N, unionFind);
        // int index = 0;

        // for (Connection c : connections)
        //     Plot.add(index++, unionFind.arrayAccesses());
    }

    private static class ErdosRenyi {
        public static Connection[] generate(int N, UnionFind unionFind) {
            ArrayList<Connection> connections = new ArrayList<>();

            while (unionFind.count() != 1) {
                int p = StdRandom.uniform(N);
                int q = StdRandom.uniform(N);
                connections.add(new Connection(p, q));
                if (!unionFind.connected(p, q)) unionFind.union(p, q);
            }

            return connections.toArray(new Connection[connections.size()]);
        }
    }
}
