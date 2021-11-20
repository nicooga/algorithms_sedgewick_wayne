package algsex.support.doubling_ratio_testing;

public abstract class Experiment {
    protected abstract String label();

    protected abstract RunDetails run(
        int i,
        int N,
        int batchSize,
        RunDetails d
    );

    protected int initialN() { return 1; }
    protected int defaultbatchSize() { return 4; }
    protected void beforeBatch(int N, int batchSize) {}
    protected void afterExperiment(int i, int N, int batchSize, RunDetails d) {}
}