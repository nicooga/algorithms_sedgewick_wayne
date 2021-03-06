package algsex.chapter1.section5;

import java.util.*;
import edu.princeton.cs.algs4.*;
import algsex.support.union_find.*;
import algsex.support.doubling_ratio_testing.*;
import algsex.chapter1.section5.Exercise13.UnionFindViaWeightedQuickUnionWithPathCompression;
import algsex.chapter1.section5.Exercise22.UnionFindFactory;
import algsex.chapter1.section5.Exercise23;
import algsex.chapter1.section5.Exercise23.Connection;
import algsex.chapter1.section5.Exercise23.ErdosRenyi;

// 1.5.24 Fast algorithms for Erdös-Renyi model. Add weighted quick-union and weight-
// ed quick-union with path compression to your tests from Exercise 1.5.23 . Can you
// discern a difference between these two algorithms?

// Conclusion: results show with-compression version is consistently faster.
public class Exercise24 {
    public static void main(String[] args) {
        int T = Integer.parseInt(args[0]);

        runExperiment(
            T,
            "weighted quick-union",
            new UnionFindViaWeightedQuickUnionFactory()
        );

        runExperiment(
            T,
            "weighted quick-union with path compression",
            new UnionFindViaWeightedQuickUnionWithPathCompressionFactory()
        );
    }

    public static void runExperiment(
        int T,
        String label,
        UnionFindFactory ufFactory
    ) {
        runExperiment(T, label, ufFactory, new ErdosRenyiConnectionGenerator());
    }

    public static void runExperiment(
        int T,
        String label,
        UnionFindFactory ufFactory,
        ConnectionGenerator connectionGenerator
    ) {
        Config config = Exercise23.experimentConfig(T);

        config.extraAttributesToAccumulateAndDisplay.add("generated connections");

        Experiment experiment =
            new BaseExperiment(
            label,
            ufFactory,
            connectionGenerator
        );

        DoublingRatioTestV2 test = new DoublingRatioTestV2(config, experiment);

        test.run();
    }

    public static class BaseExperiment extends Experiment {
        private final String label;
        private final UnionFindFactory unionFindFactory;
        private final ConnectionGenerator connectionGenerator;

        public BaseExperiment(
            String label,
            UnionFindFactory unionFindFactory,
            ConnectionGenerator connectionGenerator
        ) {
            this.label = label;
            this.unionFindFactory = unionFindFactory;
            this.connectionGenerator = connectionGenerator;
        }

        @Override
        protected String label() { return label; }

        @Override
        protected RunDetails run(int i, int N, int batchSize, RunDetails d) {
            UnionFind unionFind = unionFindFactory.build(N);
            int lgN = (int) (Math.log(N) / Math.log(2));
            Connection[] batchConnections = connectionGenerator.generate(N);

            for (Connection c : batchConnections)
                if (!unionFind.connected(c.q, c.p))
                    unionFind.union(c.q, c.p);

            assert unionFind.count() == 1;

            return d;
        }
    }

    public static class UnionFindViaWeightedQuickUnionFactory implements UnionFindFactory {
        public UnionFind build(int N) {
            return new UnionFindViaWeightedQuickUnion(N);
        }
    }

    private static class UnionFindViaWeightedQuickUnionWithPathCompressionFactory implements UnionFindFactory {
        public UnionFind build(int N) {
            return new UnionFindViaWeightedQuickUnionWithPathCompression(N);
        }
    }

    private static class ErdosRenyiConnectionGenerator implements ConnectionGenerator {
        private static int lastN = -1;
        private static Connection[] cachedConnections;

        public Connection[] generate(int N) {
            if (N != lastN) {
                cachedConnections = ErdosRenyi.generate(N);
                lastN = N;
            }

            return cachedConnections;
        }
    }

    public interface ConnectionGenerator {
        public Connection[] generate(int N);
    }
}
