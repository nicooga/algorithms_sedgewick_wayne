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

    public static void main(String[] args) {
        if (args.length > 0 && args[0] == "quick-find")
            runExperiment(new InstrumentedUnionFindViaQuickFind(SITES));
        else
            runExperiment(new InstrumentedUnionFindViaQuickUnion(SITES));
    }

    public static void runExperiment(UnionFind uf) {
        for (int i = 0; i < PAIRS; i++) {
            int q = StdRandom.uniform(SITES);
            int p = StdRandom.uniform(SITES);
            uf.union(p, q);
        }

        for (int i = 0; i < QUERIES; i++) {
            int p = StdRandom.uniform(SITES);
            int q = StdRandom.uniform(SITES);
            uf.connected(p, q);
        }
    }

    private static class InstrumentedUnionFindViaQuickFind implements UnionFind {
        private InstrumentedArray<Integer> id;
        private InstrumentationHelper instrumentationHelper;
        private int count;

        public InstrumentedUnionFindViaQuickFind(int N) {
            count = N;
            id = new InstrumentedArray(N);
            for (int i = 0; i < N; i++) id.set(i, i);

            this.instrumentationHelper = new InstrumentationHelper(id);
        }

        public int count() { return count; }

        public boolean connected(int p, int q) {
            boolean result = find(p) == find(q);
            instrumentationHelper.plotCosts();
            return result;
        }

         public void union(int p, int q) {
            int pRoot = find(p);
            int qRoot = find(q);

            if (pRoot == qRoot) return;

            for (int i = 0; i < id.length(); i++)
                if (id.get(i) == pRoot) id.set(i, qRoot);

            count--;

            instrumentationHelper.plotCosts();
        }

        public int find(int p) {
            return id.get(p);
        }
    }

    private static class InstrumentedUnionFindViaQuickUnion implements UnionFind {
        private InstrumentedArray<Integer> id;
        private InstrumentationHelper instrumentationHelper;
        private int count;

        public InstrumentedUnionFindViaQuickUnion(int N) {
            count = N;
            id = new InstrumentedArray<>(N);
            for (int i = 0; i < N; i++) id.set(i, i);
            this.instrumentationHelper = new InstrumentationHelper(id);
        }

        public int count() { return count; }
        public boolean connected(int p, int q) {
            boolean result = find(p) == find(q);
            instrumentationHelper.plotCosts();
            return result;
        }

         public void union(int p, int q) {
            int pRootId = find(p);
            int qRootId = find(q);

            if (pRootId == qRootId) return;

            id.set(pRootId, qRootId);

            count--;

            instrumentationHelper.plotCosts();
        }

        public int find(int p) {
            while (p != id.get(p)) p = id.get(p);
            return p;
        }
    }

    private static class InstrumentationHelper {
        private final InstrumentedArray[] instrumentedArrays;
        private final Plot plot = new Plot();
        private int totalCost; 
        private int i = 1;

        public InstrumentationHelper(InstrumentedArray a) {
            this(new InstrumentedArray[] { a });
        }

        public InstrumentationHelper(InstrumentedArray[] a) {
            this.instrumentedArrays = a;
        }

        private void plotCosts() {
            int iterationCost = iterationCost();
            totalCost += iterationCost;
            plotIterationCost(iterationCost);
            plotAmortizedCost(totalCost);
            resetCounters();
            i++;
        }

        private int iterationCost() {
            int sum = 0;

            for (InstrumentedArray a : instrumentedArrays)
                sum += a.accesses();

            return sum;
        }

        private void resetCounters() {
            for (InstrumentedArray a : instrumentedArrays)
                a.resetCounters();
        }

        private void plotIterationCost(double c) {
            plot.add(i, c, java.awt.Color.BLACK);
        }

        private void plotAmortizedCost(double totalCost) {
            plot.add(i, totalCost / 1.0 / i, java.awt.Color.RED);
        }
    } 
}
