package algsex.support.doubling_ratio_testing;

import java.util.*;
import algsex.support.*;

public class DefaultStatsAccumulator implements StatsAccumulator {
    protected double batchSize;
    protected double prevBatchMeanValue;
    protected double n;
    protected Stat mean = new Stat("mean", 0, 12);
    protected Stat meanRatio = new Stat("mean ratio", 1, 10);
    protected Stat sampleStandardDeviation = new Stat("stddev.", 2, 10);
    protected Stat coefficientOfVariation = new Stat("CV", 3, 10);

    private double squaredDeviationsSum;
    private Stat[] stats = new Stat[] {
        mean,
        meanRatio,
        sampleStandardDeviation,
        coefficientOfVariation
    };

    public void initialize(
        int batchSize,
        double prevBatchMeanValue
    ) {
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