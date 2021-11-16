package algsex.support.doubling_ratio_testing;
import algsex.support.*;

public class DefaultStatsAccumulator implements StatsAccumulator {
    protected double batchSize;
    protected StatsAccumulator prevBatchStatsAcc;
    protected double n;
    protected Stat mean = new Stat("mean      ");
    protected Stat meanRatio = new Stat("mean ratio");
    protected Stat sampleStandardDeviation = new Stat("stddev.");
    protected Stat coefficientOfVariation = new Stat("CV     ");

    private double squaredDeviationsSum;
    private Out out = new StdOut();

    public DefaultStatsAccumulator(int batchSize, StatsAccumulator prevBatchStatsAcc, Out out) {
        this.batchSize = batchSize;
        this.prevBatchStatsAcc = prevBatchStatsAcc;
        this.out = out;
    }

    public void add(RunDetails d) {
        n++;
        double time = d.getDouble("time");
        double m = mean.getValue();
        squaredDeviationsSum += (n-1) / n * (time - m) * (time - m);
        mean.setValue(m * ((n-1)/n) + time/n);

        if (prevBatchStatsAcc != null)
            meanRatio.setValue(
                meanRatio.getValue() * ((n-1)/n)
                + time/(n*prevBatchStatsAcc.mean())
            );
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

        for (int i = 0; i < statsToDisplay.length; i++) {
            if (i > 0) out.print("\t");

            Stat s = statsToDisplay[i];
            String value = String.format("%.2f", s.getValue());
            String paddedValue = padRight(value, s.label().length());
            out.print(paddedValue);
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

    private String padRight(String string, int length) {
        assert string.length() <= length;

        StringBuilder s = new StringBuilder(string);

        for (int i = 0; i < length - string.length(); i++)
            s.append(" ");

        return s.toString();
    }

    protected static class Stat {
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