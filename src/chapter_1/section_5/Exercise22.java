package algsex.chapter1.section5;

import java.util.*;
import edu.princeton.cs.algs4.*;
import algsex.chapter1.section5.Exercise17.ErdosRenyi;

import algsex.misc.UnionFind;
import algsex.misc.UnionFindViaQuickFind;
import algsex.misc.UnionFindViaQuickUnion;
import algsex.misc.UnionFindViaWeightedQuickUnion;

import algsex.support.doubling_ratio_testing.DoublingRatioTestV2;
import algsex.support.doubling_ratio_testing.RunDetails;

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

        new QuickFindTest().run(T);
        new QuickUnionTest().run(T);
        new WeightedQuickUnionTest().run(T);
    }

    private static class QuickFindTest extends BaseTest {
        @Override
        protected String label() { return "quick-find"; }

        @Override
        protected UnionFind initializeUnionFind(int N) {
            return new UnionFindViaQuickFind(N);
        }
    }

    private static class QuickUnionTest extends BaseTest {
        @Override
        protected String label() { return "quick-union"; }

        @Override
        protected UnionFind initializeUnionFind(int N) {
            return new UnionFindViaQuickUnion(N);
        }
    }

    private static class WeightedQuickUnionTest extends BaseTest {
        @Override
        protected String label() { return "weighted-quick-union"; }

        @Override
        protected UnionFind initializeUnionFind(int N) {
            return new UnionFindViaWeightedQuickUnion(N);
        }
    }

    private static abstract class BaseTest extends DoublingRatioTestV2 {
        abstract protected UnionFind initializeUnionFind(int N);

        @Override
        protected void doRunExperiment(int i, int N, int runsPerBatch, RunDetails d) {
            UnionFind uf = initializeUnionFind(N);
            // int connectionCount = ErdosRenyi.count(N, uf);
            // StdOut.printf("N: %d, connections: %d\n", N, connectionCount);
        }

        // protected DoublingRatioTestV2.StatsAccumulator initializeStatsAccumulator(
        //     int runsPerBatch,
        //     double prevBatchMeanTime
        // ) {
        //     return new CustomStatsAccumulator(runsPerBatch, prevBatchMeanTime);
        // }

        // private class CustomStatsAccumulator extends DoublingRatioTestV2.StatsAccumulator {
        //     DoublingRatioTestV2.Stat averageConnections = new Stat("average connections");

        //     public CustomStatsAccumulator(int batchSize, double prevBatchMeanTime) {
        //         super(batchSize, prevBatchMeanTime);
        //     }

        //     @Override
        //     protected Stat[] statsToDisplay() {
        //         Stat[] stats = new Stat[super.statsToDisplay().length+1];
        //         int index = 0;
        //         for (Stat s : super.statsToDisplay()) stats[index++] = s;
        //         stats[index++] = averageConnections;
        //         return stats;
        //     }

        //     public void add(double time, int generatedConnections) {
        //         super.add(time);

        //         averageConnections.setValue(
        //             averageConnections.getValue() * ((n-1)/n)
        //             + generatedConnections/n
        //         );
        //     }
        // }
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
