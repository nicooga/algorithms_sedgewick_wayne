package algsex.chapter1.section5;

import java.util.*;
import edu.princeton.cs.algs4.*;
import algsex.support.InstrumentedArray;
import algsex.support.Plot;
import algsex.misc.UnionFind;

// 1.5.16 Amortized costs plots. Instrument your implementations from Exercise 1.5.7
// to make amortized costs plots like those in the text.
public class Exercise16 {
    private static final int SITES = 1000;
    private static final int PAIRS = 10000;
    private static final int QUERIES = 1000;

    private static final Plot plot = new Plot();

    public static void main(String[] args) {
        if (args.length > 0 && args[0] == "quick-find")
            runExperiment(new InstrumentedUnionFindViaQuickFind(SITES));
        else
            runExperiment(new InstrumentedUnionFindViaQuickFind(SITES));
    }

    public static void runExperiment(UnionFind uf) {
        for (int i = 0; i < PAIRS; i++) {
            int q = StdRandom.uniform(SITES);
            int p = StdRandom.uniform(SITES);
            uf.union(p, q);
        }

        for (int i = 0; i < QUERIES; i++) {
            int q = StdRandom.uniform(SITES);
            int p = StdRandom.uniform(SITES);
            uf.connected(p, q);
        }
    }

    private static class InstrumentedUnionFindViaQuickFind implements UnionFind {
        private InstrumentedArray<Integer> id;
        private int count;
        private int totalCost; 
        private int i = 1;

        public InstrumentedUnionFindViaQuickFind(int N) {
            count = N;
            id = new InstrumentedArray(N);
            for (int i = 0; i < N; i++) id.set(i, i);
        }

        public int count() { return count; }

        public boolean connected(int p, int q) {
            boolean result = find(p) == find(q);
            plotCosts();
            return result;
        }

         public void union(int p, int q) {
            int pRoot = find(p);
            int qRoot = find(q);

            if (pRoot == qRoot) return;

            for (int i = 0; i < id.length(); i++)
                if (id.get(i) == pRoot) id.set(i, qRoot);

            count--;

            plotCosts();
        }

        public int find(int p) {
            return id.get(p);
        }

        private void plotCosts() {
            int partialCost = id.accesses();
            totalCost += partialCost;
            plotPartialCost(partialCost);
            plotTotalCost(totalCost / 1.0 / i);
            id.resetCounters();
            i++;
        }

        private void plotPartialCost(double c) {
            plot.add(i, c, java.awt.Color.BLACK);
        }

        private void plotTotalCost(double c) {
            plot.add(i, c, java.awt.Color.RED);
        }
    }
}
