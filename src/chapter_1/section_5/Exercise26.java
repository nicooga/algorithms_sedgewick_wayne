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
    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);

        InstrumentedUnionFind uf = new InstrumentedUnionFindViaWeightedQuickUnion(N);
        Connection[] connections = ErdosRenyi.generate(N, uf);

        int i = 0;
        int totalCost = 0;

        for (Connection c : connections) {
            if (uf.count() == 1) break;

            int p = c.p;
            int q = c.q;

            if (!uf.connected(p, q)) uf.union(p, q);

            i++;
        }
    }

    private static class ErdosRenyi {
        public static Connection[] generate(int N, InstrumentedUnionFind uf) {
            ArrayList<Connection> connections = new ArrayList<>();

            Plot plot = new Plot();
            int i = 0;
            int totalCost = 0;

            while (uf.count() != 1) {
                int p = StdRandom.uniform(N);
                int q = StdRandom.uniform(N);
                connections.add(new Connection(p, q));
                if (!uf.connected(p, q)) uf.union(p, q);
                totalCost = plotCosts(plot, uf, ++i, totalCost);
            }

            return connections.toArray(new Connection[N]);
        }

        private static int plotCosts(
            Plot plot,
            InstrumentedUnionFind uf,
            double i,
            int totalCost
        ) {
            int partialCost = uf.arrayAccesses();
            totalCost += partialCost;
            double amortizedCost = totalCost/i;

            plot.add(i, partialCost, java.awt.Color.BLACK);
            plot.add(i, amortizedCost, java.awt.Color.RED);

            uf.resetCounters();

            return totalCost;
        }
    }
}
