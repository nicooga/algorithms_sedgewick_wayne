package algsex.chapter1.section5;

import java.util.*;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import algsex.chapter1.section5.Exercise17.ErdosRenyi;

import algsex.support.Out;

import algsex.misc.UnionFind;
import algsex.misc.UnionFindViaQuickFind;
import algsex.misc.UnionFindViaQuickUnion;
import algsex.misc.UnionFindViaWeightedQuickUnion;

import algsex.support.doubling_ratio_testing.DoublingRatioTestV2;
import algsex.support.doubling_ratio_testing.DefaultStatsAccumulator;
import algsex.support.doubling_ratio_testing.RunDetails;
import algsex.support.doubling_ratio_testing.StatsAccumulator;

// 1.5.22 Doubling test for Erdös-Renyi model. Develop a performance-testing client that
// takes an int value T from the command line and performs T trials of the following ex-
// periment: Use your client from Exercise 1.5.17 to generate random connections, using
// UnionFind to determine connectivity as in our development client, looping until all
// sites are connected. For each N, print the value of N, the average number of connections
// processed, and the ratio of the running time to the previous. Use your program to vali-
// date the hypotheses in the text that the running times for quick-find and quick-union
// are quadratic and weighted quick-union is near-linear.
public class Exercise22 {
    private static final int RUNS_PER_BATCH = 20;
    public static void main(String[] args) {
        int T = Integer.parseInt(args[0]);

        int maxN = (int) Math.pow(2, T-1);

        new QuickFindTest().run(RUNS_PER_BATCH, maxN);
        new QuickUnionTest().run(RUNS_PER_BATCH, maxN);
        new WeightedQuickUnionTest().run(RUNS_PER_BATCH, maxN);
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
            int connectionCount = ErdosRenyi.count(N, uf);
            d.setInt("connectionCount", connectionCount);
            System.gc();
        }

        @Override
        protected StatsAccumulator initializeStatsAccumulator(
            int runsPerBatch,
            StatsAccumulator prevBatchStatsAcc
        ) {
            return new CustomStatsAccumulator(runsPerBatch, prevBatchStatsAcc, out);
        }

        private class CustomStatsAccumulator extends DefaultStatsAccumulator {
            private CustomStatsAccumulator prevBatchStatsAcc;
            private Stat meanConnections = new Stat("average connections");
            private Stat meanTimePerConn = new Stat("mean time per conn.");
            private Stat meanTimePerConnRatio = new Stat("time per conn. ratio");


            public CustomStatsAccumulator(
                int batchSize,
                StatsAccumulator prevBatchStatsAcc,
                Out out
            ) {
                super(batchSize, prevBatchStatsAcc, out);
                this.prevBatchStatsAcc = (CustomStatsAccumulator) prevBatchStatsAcc;
            }

            @Override
            protected Stat[] statsToDisplay() {
                Stat[] stats = new Stat[super.statsToDisplay().length+3];
                int index = 0;
                for (Stat s : super.statsToDisplay()) stats[index++] = s;

                stats[index++] = meanConnections;
                stats[index++] = meanTimePerConn;
                stats[index++] = meanTimePerConnRatio;

                return stats;
            }

            @Override
            public void add(RunDetails d) {
                super.add(d);

                int connectionCount = d.getInt("connectionCount");

                meanConnections.setValue(
                    meanConnections.getValue() * ((n-1)/n)
                    + connectionCount/n
                );
            }

            @Override
            public void onBatchFinished() {
                super.onBatchFinished();

                meanTimePerConn.setValue(
                    meanConnections.getValue() /
                    mean.getValue()
                );

                if (prevBatchStatsAcc != null)
                    meanTimePerConnRatio.setValue(
                        meanTimePerConn.getValue() /
                        prevBatchStatsAcc.meanTimePerConn()
                    );
            }

            protected double meanTimePerConn() { return meanTimePerConn.getValue(); }
        }
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
