package algsex.support.doubling_ratio_testing;
import algsex.support.*;

public class DefaultStatsAccumulator implements StatsAccumulator {
    protected double batchSize;
    protected double prevBatchMeanTime;
    protected double n;
    private double squaredDeviationsSum;
    private Stat mean = new Stat("mean");
    private Stat meanRatio = new Stat("mean ratio");
    private Stat sampleStandardDeviation = new Stat("stddev.");
    private Stat coefficientOfVariation = new Stat("CV");
    private Out out = new StdOut();

    public DefaultStatsAccumulator(int batchSize, double prevBatchMeanTime, Out out) {
        this.batchSize = batchSize;
        this.prevBatchMeanTime = prevBatchMeanTime;
        this.out = out;
    }

    public void add(RunDetails d) {
        n++;
        double time = d.getDouble("time");
        double m = mean.getValue();
        squaredDeviationsSum += (n-1) / n * (time - m) * (time - m);
        mean.setValue(m * ((n-1)/n) + time/n);
        meanRatio.setValue(meanRatio.getValue() * ((n-1)/n) + time/(n*prevBatchMeanTime));
    }

    public void printHeader() {
        Stat[] statsToDisplay = statsToDisplay();

        out.print(statsToDisplay[0].label());

        for (int i = 1; i < statsToDisplay.length; i++) {
            Stat stat = statsToDisplay[i];
            out.print("\t");
            out.print(stat.label());
        }

        out.println("");
    }

    public void printLastBatchStats() {
        Stat[] statsToDisplay = statsToDisplay();

        out.printf("%.2f", statsToDisplay[0].getValue());

        for (int i = 1; i < statsToDisplay.length; i++) {
            Stat s = statsToDisplay[i];
            out.printf("\t%.2f", s.getValue());
        }

        out.println("");
    }

    public void onBatchFinished() {
        computeSampleStandardDeviation();
        computeCoefficientOfVariation();
    }

    public double mean() { return mean.getValue(); }

    protected Stat[] statsToDisplay() {
        return new Stat[] {
            mean,
            meanRatio,
            sampleStandardDeviation,
            coefficientOfVariation
        };
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

    private static class Stat {
        private String label;
        private double value = -1;

        public Stat(String label) {
            this.label = label;
        }

        public Stat(String label, double value){
            this.label = label;
            this.value = value;
        }

        public void setValue(double value) {
            this.value = value;
        }

        public double getValue() { return this.value; }
        public String label() { return label; }
    }
}