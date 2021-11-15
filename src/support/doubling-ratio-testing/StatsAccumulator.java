package algsex.support.doubling_ratio_testing;

public interface StatsAccumulator {
    public void add(RunDetails d);
    public double mean();
    public void printHeader();
    public void printLastBatchStats();
    public void onBatchFinished();
}