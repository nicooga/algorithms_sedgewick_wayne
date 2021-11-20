package algsex.support.doubling_ratio_testing;

import algsex.support.*;

public interface StatsAccumulator {
    public void initialize(
        int N,
        int batchSize,
        double prevBatchMeanTime
    );

    public void add(double value);
    public void onBatchFinished();
    public double mean();
    public Stat[] stats();
}