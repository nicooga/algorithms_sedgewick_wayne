package algsex.support.doubling_ratio_testing;

import java.util.*;
import algsex.support.*;

public class DefaultStatsAccumulator implements StatsAccumulator {
    protected double batchSize;
    protected double prevBatchMeanValue;
    protected double n;
    protected Stat N = new Stat("N", 0, 13);
    protected Stat mean = new Stat("mean", 1, 15);
    protected Stat meanRatio = new Stat("mean ratio", 2, 10);
    protected Stat sampleStandardDeviation = new Stat("stddev.", 3, 15);
    protected Stat coefficientOfVariation = new Stat("CV", 4, 10);

    private double squaredDeviationsSum;
    private Stat[] stats = new Stat[] {
        N,
        mean,
        meanRatio,
        sampleStandardDeviation,
        coefficientOfVariation
    };

    public void initialize(
        int N,
        int batchSize,
        double prevBatchMeanValue
    ) {
        this.N.setValue(N);
        this.batchSize = batchSize;
        this.prevBatchMeanValue = prevBatchMeanValue;
    }

    public void add(double value) {
        n++;
        double m = mean.getValue();
        squaredDeviationsSum += (n-1) / n * (value - m) * (value - m);
        mean.setValue(m * ((n-1)/n) + value/n);
        meanRatio.setValue(
            meanRatio.getValue() * ((n-1)/n)
            + value/(n*prevBatchMeanValue)
        );
    }

    public void onBatchFinished() {
        computeSampleStandardDeviation();
        computeCoefficientOfVariation();
    }

    public double mean() { return mean.getValue(); }

    public Stat[] stats() {
        return stats;
    }

    private void computeSampleStandardDeviation() {
        double sampleVariance = squaredDeviationsSum / (batchSize-1);
        double value = Math.sqrt(sampleVariance);
        sampleStandardDeviation.setValue(value);
    }

    private void computeCoefficientOfVariation() {
        double value = sampleStandardDeviation.getValue() / mean.getValue();
        coefficientOfVariation.setValue(value);
    }
}