package algsex.support.doubling_ratio_testing;

import java.util.*;
import algsex.support.*;

class BatchContext {
    private final Map<String, StatsAccumulator> accumulators = new HashMap<>();
    private final int N;
    private final int batchSize;
    private final Out out;
    private BatchStatsPrinter printer;

    public BatchContext(Out out) {
        this.N = -1;
        this.batchSize = -1;
        this.out = out;
    }

    public BatchContext(
        int N,
        Config config,
        BatchContext prevBatchContext,
        Out out
    ) {
        this.N = N;
        this.batchSize = config.batchSize;
        this.out = out;

        addStatsAccumulatorFor("time", prevBatchContext);

        for (String statId : config.extraAttributesToAccumulateAndDisplay)
            addStatsAccumulatorFor(statId, prevBatchContext);
    }

    public int N() { return N; }
    public int batchSize() { return batchSize; }

    public StatsAccumulator getStatsAccumulatorFor(String statId) {
        return accumulators.get(statId);
    }

    public Map<String, StatsAccumulator> getStatsAccumulators() {
        return accumulators;
    }

    public BatchStatsPrinter printer() {
        if (printer == null) 
            printer = new BatchStatsPrinter(accumulators, out);

        return printer;
    }

    private void addStatsAccumulatorFor(String statId, BatchContext prevBatchContext) {
        StatsAccumulator acc = new DefaultStatsAccumulator();
        StatsAccumulator prevBatchAcc = prevBatchContext.getStatsAccumulatorFor(statId);

        double prevBatchMean = prevBatchAcc == null ? 0 : prevBatchAcc.mean();

        acc.initialize(batchSize, prevBatchMean);

        accumulators.put(statId, acc);
    }
}