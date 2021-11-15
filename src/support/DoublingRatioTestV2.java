package algsex.support;

import java.util.Arrays;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.math.BigDecimal;
import java.math.RoundingMode;
import edu.princeton.cs.algs4.StdRandom;

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
            StatsAccumulator sa = initializeStatsAccumulator(runsPerBatch, prevBatchMeanTime);
            if (N == initialN()) sa.printHeader();
            beforeBatch(N, runsPerBatch);
            sa = runBatch(N, runsPerBatch, sa);
            sa.onBatchFinished();
            sa.printLastBatchStats();

            prevBatchMeanTime = sa.mean.getValue();
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
        return new StatsAccumulator(runsPerBatch, prevBatchMeanTime);
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
            accumulateRunStats(acc, d);
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

    protected void accumulateRunStats(StatsAccumulator acc, RunDetails d) {
        acc.add(d.getDouble("time"));
    }

    protected class StatsAccumulator {
        protected double batchSize;
        protected double prevBatchMeanTime;
        protected double n;
        private double squaredDeviationsSum;
        private Stat mean = new Stat("mean");
        private Stat meanRatio = new Stat("mean ratio");
        private Stat sampleStandardDeviation = new Stat("stddev.");
        private Stat coefficientOfVariation = new Stat("CV");

        public StatsAccumulator(int batchSize, double prevBatchMeanTime) {
            this.batchSize = batchSize;
            this.prevBatchMeanTime = prevBatchMeanTime;
        }

        public void add(double time) {
            n++;
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

    protected class RunDetails {
        HashMap<String, Integer> integers = new HashMap<>();
        HashMap<String, Double> doubles = new HashMap<>();

        public void setInt(String key, int value) { integers.put(key, value); }
        public int getInt(String key) { return integers.get(key); }
        public void setDouble(String key, double value) { doubles.put(key, value); }
        public double getDouble(String key) { return doubles.get(key); }
    }

    public static void main(String[] args) {
        DoublingRatioTestV2Test.main(args);
    }

    private static class DoublingRatioTestV2Test {
        private static final int RUNS_PER_BATCH = 5;
        private static final double BASE_TIME = 10;
        private final static OutputInterceptor outputInterceptor = new OutputInterceptor();

        public static void main(String[] args) {
            TestTest test = setup();
            test.run();
            performAssertions(test);
            System.out.println("Tests passed");
        }

        private static TestTest setup()  {
            TestTest test = new TestTest();
            test.out = outputInterceptor;
            test.stopwatch = new TestStopwatch();
            return test; 
        }

        private static void performAssertions(TestTest test) {
            assertBeforeBatchCalledCorrectly(test);
            assertDoRunExperimentCalledCorrectly(test);
            assertOutputIsCorrect();
        }

        private static void assertBeforeBatchCalledCorrectly(TestTest test) {
            int index = 0;
            int N = 4;

            while (N > 0) {
                Test.assertArrayEquals(
                    test.beforeBatchCalls.get(index),
                    new int[] { N, RUNS_PER_BATCH }
                );
                index++;
                N *= 2;
            }
        }

        private static void assertDoRunExperimentCalledCorrectly(TestTest test) {
            int batchNumber = 0;
            int N = 4;

            while (N > 0) {
                int offset = batchNumber * test.defaultRunsPerBatch();

                for (int i = 0; i < test.defaultRunsPerBatch(); i++) {
                    Object[] args = test.doRunExperimentCalls.get(offset + i);

                    Test.assertEqual((int) args[0], i);
                    Test.assertEqual((int) args[1], N);
                    Test.assertEqual((int) args[2], RUNS_PER_BATCH);

                    RunDetails d = (RunDetails) args[3];

                    Test.assertEqual(d.getDouble("time"), expectedTime(batchNumber, i));
                }

                batchNumber++;
                N *= 2;
            }
        }

        private static void assertOutputIsCorrect() {
            String[] outputLines = outputInterceptor.contents().split("\n");

            assertPrintedHeaderCorrectly(outputLines);
            assertPrintedStatsCorrectly(outputLines);
        }

        private static void assertPrintedHeaderCorrectly(String[] outputLines) {
            Test.assertEqual(
                outputLines[0],
                "mean\tmean ratio\tstddev.\tCV"
            );
        }

        private static void assertPrintedStatsCorrectly(String[] outputLines) {
            assert outputLines.length > 1;

            for (
                int batchNumber = 0;
                batchNumber < outputLines.length-1;
                batchNumber++
            )
                assertPrintedStatsCorrectly(
                    outputLines[batchNumber+1],
                    batchNumber
                );
        }

        private static void assertPrintedStatsCorrectly(String line, int batchNumber) {
            String[] parts = line.split("\\s+");

            double mean = Double.parseDouble(parts[0]);
            double meanRatio = Double.parseDouble(parts[1]);
            double sampleStandardDeviation = Double.parseDouble(parts[2]);
            double coefficientOfVariation = Double.parseDouble(parts[3]);

            Test.assertEqual(mean, expectedMean(batchNumber));

            if (batchNumber > 0) Test.assertEqual(meanRatio, 2);

            Test.assertEqual(
                sampleStandardDeviation,
                expectedSampleStandardDeviation(batchNumber)
            );

            Test.assertEqual(coefficientOfVariation, 0.53);

            System.out.println(Arrays.toString(parts));
        }

        private static double expectedTime(int batchNumber, int iteration) {
            return BASE_TIME * (iteration+1) * Math.pow(2, batchNumber);
        }

        private static double expectedMean(int batchNumber) {
            return BASE_TIME * Math.pow(2, batchNumber-1) * (RUNS_PER_BATCH + 1);
        }

        private static double expectedSampleStandardDeviation(int batchNumber) {
            int b = batchNumber;
            int B = RUNS_PER_BATCH;
            double Ts = BASE_TIME;

            return round(Ts * Math.pow(2, b-1) * Math.sqrt((1/3.0) * B * (B+1)), 2);
        }

        public static double round(double value, int places) {
            if (places < 0) throw new IllegalArgumentException();

            BigDecimal bd = BigDecimal.valueOf(value);
            bd = bd.setScale(places, RoundingMode.HALF_UP);
            return bd.doubleValue();
        }

        private static class TestTest extends DoublingRatioTestV2 {
            public List<Object[]> doRunExperimentCalls = new ArrayList();
            public List<int[]> beforeBatchCalls = new ArrayList();

            @Override
            protected String label() { return "test"; }

            @Override
            protected void doRunExperiment(int i, int N, int runsPerBatch, RunDetails d) {
                doRunExperimentCalls.add(new Object[] { i, N, runsPerBatch, d });
            }

            @Override
            protected void beforeBatch(int N, int runsPerBatch) {
                beforeBatchCalls.add(new int[] { N, runsPerBatch });
            }

            @Override
            protected int defaultRunsPerBatch() { return RUNS_PER_BATCH; }
        }

        private static class OutputInterceptor implements Out {
            public StringBuffer contents = new StringBuffer();

            public void print(String s) { contents.append(s); }
            public void println(Object o) { println(o.toString()); }
            public void println(String s) {
                contents.append(s);
                contents.append("\n");
            }

            public void printf(String s, Object... args) {
                contents.append(String.format(s, args));
            }

            public String contents() { return contents.toString(); }
        }

        private static class TestStopwatch implements Stopwatch {
            int batchNumber = 0; // zero-indexed
            int i = 0;

            double x = 0;

            // Simulate quadratic complexity
            public long elapsedTime() {
                long result = (long) (BASE_TIME * (i+1) * Math.pow(2, batchNumber));

                if (i == RUNS_PER_BATCH-1) { i = 0; batchNumber++; x = 0; }
                else i++;

                return result;
            }

            public void reset() {}
        }
    }
}