package algsex.chapter1.section5;

import java.util.*;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import algsex.chapter1.section5.Exercise17.ErdosRenyi;

import algsex.support.*;
import algsex.misc.*;
import algsex.support.doubling_ratio_testing.*;

// 1.5.22 Doubling test for Erdös-Renyi model. Develop a performance-testing client that
// takes an int value T from the command line and performs T trials of the following ex-
// periment: Use your client from Exercise 1.5.17 to generate random connections, using
// UnionFind to determine connectivity as in our development client, looping until all
// sites are connected. For each N, print the value of N, the average number of connections
// processed, and the ratio of the running time to the previous. Use your program to vali-
// date the hypotheses in the text that the running times for quick-find and quick-union
// are quadratic and weighted quick-union is near-linear.
public class Exercise22 {
    private static final int BATCH_SIZE = 20;

    public static void main(String[] args) {
        int T = Integer.parseInt(args[0]);
        int maxN = (int) Math.pow(2, T-1);

        runExperiment(
            new UnionFindViaQuickUnionFactory(),
            "quick union",
            maxN
        );

        runExperiment(
            new UnionFindViaQuickFindFactory(),
            "quick find",
            maxN
        );

        runExperiment(
            new UnionFindViaWeightedQuickUnionFactory(),
            "weighted quick union",
            maxN
        );
    }

    private static void runExperiment(
        UnionFindFactory unionFindFactory,
        String label,
        int maxN
    ) {
        Config config = new Config();

        config.maxN = maxN;
        config.batchSize = BATCH_SIZE;
        config.extraAttributesToAccumulateAndDisplay.add("timePerConnection");

        Experiment experiment = new BaseExperiment(unionFindFactory, label);
        DoublingRatioTestV2 test = new DoublingRatioTestV2(config, experiment);

        test.run();
    }

    private static class UnionFindViaQuickUnionFactory implements UnionFindFactory {
        public UnionFind build(int N) {
            return new UnionFindViaQuickUnion(N);
        }
    }

    private static class UnionFindViaQuickFindFactory implements UnionFindFactory {
        public UnionFind build(int N) {
            return new UnionFindViaQuickFind(N);
        }
    }

    private static class UnionFindViaWeightedQuickUnionFactory implements UnionFindFactory {
        public UnionFind build(int N) {
            return new UnionFindViaWeightedQuickUnion(N);
        }
    }

    private static class BaseExperiment extends Experiment {
        private final UnionFindFactory unionFindFactory;
        private final String label;

        public BaseExperiment(UnionFindFactory unionFindFactory, String label) {
            this.unionFindFactory = unionFindFactory;
            this.label = label;
        }

        @Override
        protected String label() { return label; }

        @Override
        protected void afterExperiment(int N, int i, int batchSize, RunDetails d) {
            double time = (double) d.get("time");
            int generatedConnections = (int) d.get("generatedConnections");
            d.put("timePerConnection", time/generatedConnections);
        }

        @Override
        protected RunDetails run(int i, int N, int batchSize, RunDetails d) {
            UnionFind unionFind = unionFindFactory.build(N);
            int connectionCount = ErdosRenyi.count(N, unionFind);

            d.put("generatedConnections", connectionCount);

            return d;
        }
    }

    private interface UnionFindFactory {
        public UnionFind build(int N);
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
