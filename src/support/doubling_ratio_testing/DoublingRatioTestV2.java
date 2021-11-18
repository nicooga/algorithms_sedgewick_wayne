package algsex.support.doubling_ratio_testing;

import java.util.*;
import algsex.support.*;

// For each `N = initialN() * 2^b`, run an experiment `batchSize` times, passing `N` as a parameter for the experiment. 
public class DoublingRatioTestV2 {
    private Out out;
    private Stopwatch stopwatch;
    private Config config;
    private Experiment experiment;

    public DoublingRatioTestV2(Config config, Experiment experiment) {
        this.config = config;
        this.experiment = experiment;
        this.out = new StdOut();
        this.stopwatch = new SystemStopwatch();
    }

    public DoublingRatioTestV2(
        Config config,
        Experiment experiment,
        Out out,
        Stopwatch stopwatch
    ) {
        this.config = config;
        this.experiment = experiment;
        this.out = out;
        this.stopwatch = stopwatch;
    }

    public void run() {
        // TODO: replace with something
        // printExperimentConfig(batchSize);

        BatchContext prevBatchContext = new BatchContext(out);

        for (int N = config.initialN; N <= config.maxN && N > 0; N *= 2)
            prevBatchContext = runBatch(N, prevBatchContext);
    }

    // private void printExperimentConfig(int batchSize) {
    //     out.println("===");

    //     // TODO: replace with experiment.label()
    //     // out.printf("Running experiment \"%s\"\n", config.label);

    //     out.println("Runs per batch: " + batchSize);
    //     out.println("===");
    // }

    private BatchContext runBatch(
        int N,
        BatchContext prevBatchContext
    ) {
        BatchContext context = new BatchContext(N, config, prevBatchContext, out);

        if (N == config.initialN) printBatchStatsHeader(context);
        experiment.beforeBatch(N, config.batchSize);
        doRunBatch(context);
        onBatchFinished(context);
        printBatchStats(context);

        return context;
    }

    private void doRunBatch(BatchContext context) {
        for (int i = 0; i < context.batchSize(); i++) {
            RunDetails d = runExperiment(context, i);
            accumulateStats(context, d);
        }
    }

    private RunDetails runExperiment(BatchContext context, int i) {
        RunDetails d = new RunDetails();
        stopwatch.reset();
        d = experiment.run(i, context.N(), context.batchSize(), d);
        double time = stopwatch.elapsedTime();
        d.put("time", time);
        return d;
    }

    private void accumulateStats(BatchContext context, RunDetails d) {
        for (
            Map.Entry<String, StatsAccumulator> e
            : context.getStatsAccumulators().entrySet()
        ) {
            String statId = e.getKey();
            StatsAccumulator acc = e.getValue();
            double value = (double) d.get(statId);

            acc.add(value);
        }
    }

    private void onBatchFinished(BatchContext context) {
        for (
            Map.Entry<String, StatsAccumulator> e
            : context.getStatsAccumulators().entrySet()
        ) {
            StatsAccumulator acc = e.getValue();
            acc.onBatchFinished();
        }
    }

    private void printBatchStatsHeader(BatchContext context) {
        context.printer().printHeader();
    }

    private void printBatchStats(BatchContext context) {
        context.printer().print();
    }

    public static void main(String[] args) {
        DoublingRatioTestV2Test.main(args);
    }
}