package algsex.chapter1.section5;

import java.util.*;
import edu.princeton.cs.algs4.*;
import algsex.misc.*;
import algsex.support.doubling_ratio_testing.*;
import algsex.chapter1.section5.Exercise22.UnionFindFactory;
import algsex.chapter1.section5.Exercise22.UnionFindViaQuickFindFactory;
import algsex.chapter1.section5.Exercise22.UnionFindViaQuickUnionFactory;

// 1.5.23 Compare quick-find with quick-union for Erdös-Renyi model. Develop a perfor-
// mance-testing client that takes an int value T from the command line and performs
// T trials of the following experiment: Use your client from Exercise 1.5.17 to generate
// random connections. Save the connections, so that you can use both quick-union and
// quick-find to determine connectivity as in our development client, looping until all
// sites are connected. For each N, print the value of N and the ratio of the two running
// times.
public class Exercise23 {
    private static final int INITIAL_N = 2;
    private static final int BATCH_SIZE = 20;

    public static void main(String[] args) {
        int T = Integer.parseInt(args[0]);
        Config config = experimentConfig(T);
        ArrayList<Connection>[] connections = generateConections(T);

        runExperiment(
            "quick-find",
            new UnionFindViaQuickFindFactory(),
            config,
            connections
        );

        runExperiment(
            "quick-union",
            new UnionFindViaQuickUnionFactory(),
            config,
            connections
        );
    }

    private static void runExperiment(
        String label,
        UnionFindFactory ufFactory,
        Config config,
        ArrayList<Connection>[] connections
    ) {
        Experiment experiment = new BaseExperiment(label, ufFactory, connections);
        DoublingRatioTestV2 test = new DoublingRatioTestV2(config, experiment);
        test.run();
    }

    private static ArrayList<Connection>[] generateConections(int T) {
        ArrayList<Connection>[] connectionLists =
            (ArrayList<Connection>[]) new ArrayList<?>[T];

        for (int i = 0; i < T; i++) {
            int N = (int) (INITIAL_N * Math.pow(2, i));
            connectionLists[i] = new ArrayList<>(Arrays.asList(ErdosRenyi.generate(N)));
        }

        return connectionLists;
    }

    private static Config experimentConfig(int T) {
        int maxN = (int) Math.pow(2, T-1);

        Config config = new Config();

        config.maxN = maxN;
        config.initialN = INITIAL_N;
        config.batchSize = BATCH_SIZE;

        return config;
    }

    private static class BaseExperiment extends Experiment {
        private final String label;
        private final ArrayList<Connection>[] connections;
        private final UnionFindFactory unionFindFactory;

        public BaseExperiment(
            String label,
            UnionFindFactory unionFindFactory,
            ArrayList<Connection>[] connections
        ) {
            this.label = label;
            this.connections = connections;
            this.unionFindFactory = unionFindFactory;
        }

        @Override
        protected String label() { return label; }

        @Override
        protected RunDetails run(int i, int N, int batchSize, RunDetails d) {
            UnionFind unionFind = unionFindFactory.build(N);
            int lgN = (int) (Math.log(N) / Math.log(2));
            ArrayList<Connection> batchConnections = connections[lgN-1];

            for (Connection c : batchConnections)
                if (!unionFind.connected(c.q, c.p))
                    unionFind.union(c.q, c.p);

            assert unionFind.count() == 1;

            return d;
        }
    }

    private static class ErdosRenyi {
        public static Connection[] generate(int N) {
            UnionFind unionFind = new UnionFindViaWeightedQuickUnion(N);
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

    private static class Connection {
        int p;
        int q;

        public Connection(int p, int q) {
            this.p = p;
            this.q = q;
        }
    }
}
