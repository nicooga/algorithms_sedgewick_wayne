package algsex.support.doubling_ratio_testing;

import java.util.DoubleSummaryStatistics;

import algsex.support.*;

// For each `N = initialN() * 2^b`, run an experiment `runsPerBatch` times, passing `N` as a parameter for the experiment. 
public abstract class DoublingRatioTestV2{
    protected abstract String label();

    protected Out out = new StdOut();
    protected Stopwatch stopwatch = new SystemStopwatch();

    public void run() {
        run(defaultRunsPerBatch());
    }

    public void run(int runsPerBatch) {
        doRun(runsPerBatch);
    }

    private void doRun(int runsPerBatch) {
        double prevBatchMeanTime = -1;

        printExperimentConfig(runsPerBatch);

        for (int N = initialN(); N > 0; N *= 2) {
            StatsAccumulator acc = initializeStatsAccumulator(runsPerBatch, prevBatchMeanTime);
            if (N == initialN()) acc.printHeader();
            beforeBatch(N, runsPerBatch);
            acc = runBatch(N, runsPerBatch, acc);
            acc.onBatchFinished();
            acc.printLastBatchStats();

            prevBatchMeanTime = acc.mean();
        }
    }

    private void printExperimentConfig(int runsPerBatch) {
        // TODO: uncomment this and fix corresponding tests
        // out.printf("Running experiment \"%s\"\n", label());
        // out.println("Runs per batch: " + runsPerBatch);
        // out.println("===");
    }

    protected StatsAccumulator initializeStatsAccumulator(
        int runsPerBatch,
        double prevBatchMeanTime
    ) {
        return new DefaultStatsAccumulator(runsPerBatch, prevBatchMeanTime, out);
    }

    protected int initialN() { return 4; }
    protected int defaultRunsPerBatch() { return 4; }
    protected void beforeBatch(int N, int runsPerBatch) {}

    protected abstract void doRunExperiment(int i, int N, int runsPerBatch, RunDetails d);

    protected StatsAccumulator runBatch(
        int N,
        int runsPerBatch,
        StatsAccumulator acc
    ) {
        for (int i = 0; i < runsPerBatch; i++) {
            RunDetails d = runExperiment(i, N, runsPerBatch);
            acc.add(d);
        }

        return acc;
    }

    private RunDetails runExperiment(int i, int N, int runsPerBatch) {
        RunDetails d = new RunDetails();
        stopwatch.reset();
        doRunExperiment(i, N, runsPerBatch, d);
        double time = stopwatch.elapsedTime();
        d.setDouble("time", time);
        return d;
    }

    public static void main(String[] args) {
        DoublingRatioTestV2Test.main(args);
    }

}