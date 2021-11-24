package algsex.chapter1.section5;

import java.util.*;
import edu.princeton.cs.algs4.*;

import algsex.support.*;
import algsex.support.doubling_ratio_testing.*;
import algsex.support.union_find.*;
import algsex.chapter1.section5.Exercise22;
import algsex.chapter1.section5.Exercise22.UnionFindFactory;
import algsex.chapter1.section5.Exercise23.Connection;
import algsex.chapter1.section5.Exercise24;

// 1.5.25 Doubling test for random grids. Develop a performance-testing client that takes
// an int value T from the command line and performs T trials of the following experie-
// ment: Use your client from Exercise 1.5.18 to generate the connections in an N-by-N
// square grid, randomly oriented and in random order, then use UnionFind to determine
// connectivity as in our development client, looping until all sites are connected. For each
// N, print the value of N, the average number of connections processed, and the ratio of
// the running time to the previous. Use your program to validate the hypotheses in the
// text that the running times for quick-find and quick-union are quadratic and weighted
// quick-union is near-linear. Note: As N doubles, the number of sites in the grid increases
// by a factor of 4, so expect a doubling factor of 16 for quadratic and 4 for linear.

// Conclusion: the results do not validate the hypotheses.
public class Exercise25 {
    public static void main(String[] args) {
        int T = Integer.parseInt(args[0]);

        runExperiment(
            T,
            "quick-find",
            new Exercise22.UnionFindViaQuickFindFactory()
        );

        runExperiment(
            T,
            "quick-union",
            new Exercise22.UnionFindViaQuickUnionFactory()
        );

        runExperiment(
            T,
            "weighted quick-union",
            new Exercise24.UnionFindViaWeightedQuickUnionFactory()
        );
    }

    private static void runExperiment(
        int T,
        String label,
        UnionFindFactory ufFactory
    ) {
        Config config = Exercise23.experimentConfig(T);

        config.extraAttributesToAccumulateAndDisplay.add("processed connections");

        Experiment experiment = new BaseExperiment(label, ufFactory);
        DoublingRatioTestV2 test = new DoublingRatioTestV2(config, experiment);

        test.run();
    }

    public static class BaseExperiment extends Experiment {
        private final String label;
        private final UnionFindFactory unionFindFactory;

        public BaseExperiment(
            String label,
            UnionFindFactory unionFindFactory
        ) {
            this.label = label;
            this.unionFindFactory = unionFindFactory;
        }

        @Override
        protected String label() { return label; }

        @Override
        protected RunDetails run(int i, int N, int batchSize, RunDetails d) {
            UnionFind unionFind = unionFindFactory.build(N);
            RandomBag<Connection> batchConnections = GridConnectionGenerator.generate(N);
            int processedConnections = 0; 

            for (Connection c : batchConnections) {
                if (unionFind.count() == 1) break;

                processedConnections++;

                int p = c.p;
                int q = c.q;

                System.out.println("p: " + p + " , q: " + q);

                if (!unionFind.connected(p, q)) unionFind.union(p, q);
            }

            d.put("processed connections", (double) processedConnections);

            return d;
        }
    }

    private static class GridConnectionGenerator {
        public static RandomBag<Connection> generate(int N) {
            RandomBag<Connection> bag = new RandomBag<>();

            for (int i = 0; i < N; i++)
                for (int j = 0; j < N; j++)
                    if (StdRandom.bernoulli())
                        bag.add(new Connection(i, j));
                    else
                        bag.add(new Connection(j, i));

            return bag;
        }
    }
}
