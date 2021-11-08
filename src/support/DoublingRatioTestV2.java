package algsex.support;

import java.util.Arrays;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.ArrayList;
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

        for (int N = initialN(); N > 0; N *= 2) {
            StatsAccumulator sa = new StatsAccumulator(N, prevBatchMeanTime);
            if (N == initialN()) sa.printHeader();
            beforeBatch(N, runsPerBatch);
            sa = runBatch(N, runsPerBatch, sa);
            sa.computeStats();
            sa.printLastBatchStats();
            prevBatchMeanTime = sa.meanTime.getValue();
        }
    }

    private StatsAccumulator runBatch(
        int N,
        int runsPerBatch,
        StatsAccumulator StatsAccumulator
    ) {
        for (int i = 0; i < runsPerBatch; i++) {
            double time = runExperiment(i, N, runsPerBatch);
            StatsAccumulator.add(time);
        }

        return StatsAccumulator;
    }

    private double runExperiment(int i, int N, int runsPerBatch) {
        stopwatch.reset();
        doRunExperiment(i, N, runsPerBatch);
        return stopwatch.elapsedTime();
    }

    protected int initialN() { return 4; }
    protected int defaultRunsPerBatch() { return 4; }
    protected void beforeBatch(int N, int runsPerBatch) {}

    protected abstract void doRunExperiment(int i, int N, int runsPerBatch);

    protected class StatsAccumulator {
        private final double batchSize;
        private final double prevBatchMeanTime;
        private double timeSquaredDeviationsSum;
        private Stat meanTime = new Stat("mean");
        private Stat meanRatio = new Stat("mean ratio");
        private Stat standardDeviation = new Stat("stddev.");
        private Stat coefficientOfVariation = new Stat("CV");
        private double n;

        public StatsAccumulator(int batchSize, double prevBatchMeanTime) {
            this.batchSize = batchSize;
            this.prevBatchMeanTime = prevBatchMeanTime;
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

            out.printf("%.5f", statsToDisplay[1].value);

            for (int i = 1; i < statsToDisplay.length; i++) {
                Stat s = statsToDisplay[i];
                out.printf("\t%.5f", s.value);
            }

            out.println("");
        }

        public void add(double time) {
            n++;
            double m = meanTime.getValue();
            timeSquaredDeviationsSum += (n-1) / n * (time - m) * (time - m);
            meanTime.setValue(m * ((n-1)/n) + time/n);
            meanRatio.setValue(meanRatio.getValue() * ((n-1)/n) + time/(n*prevBatchMeanTime));
        }

        public void computeStats() {
            computestandardDeviation();
            computeCoefficientOfVariation();
        }

        protected Stat[] statsToDisplay() {
            return new Stat[] {
                meanTime,
                meanRatio,
                standardDeviation,
                coefficientOfVariation
            };
        }

        private void computestandardDeviation() {
            double timeVariance = timeSquaredDeviationsSum / (batchSize-1);
            double value = timeVariance / (batchSize-1);
            standardDeviation.setValue(value);
        }

        private void computeCoefficientOfVariation() {
            double value = standardDeviation.getValue() / meanRatio.getValue();
            coefficientOfVariation.setValue(value);
        }
    }

    protected static class Stat {
        private String label;
        private double value = -1;
        private boolean isSet = false;

        public Stat(String label) {
            this.label = label;
        }

        public Stat(String label, double value){
            this.label = label;
            this.value = value;
            this.isSet = true;
        }

        public void setValue(double value) {
            this.value = value;
            this.isSet = true;
        }

        public double getValue() { return this.value; }
        public String label() { return label; }
        public boolean isSet() { return isSet; }
    }

    public static void main(String[] args) {
        DoublingRatioTestV2Test.main(args);
    }

    private static class DoublingRatioTestV2Test {
        private static final int RUNS_PER_BATCH = 5;
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
            int testedBatches = 0;
            int N = 4;

            while (N > 0) {
                int offset = testedBatches * test.defaultRunsPerBatch();

                for (int i = 0; i < test.defaultRunsPerBatch(); i++) {
                    Test.assertArrayEquals(
                        test.doRunExperimentCalls.get(offset + i),
                        new int[] { i, N, RUNS_PER_BATCH }
                    );
                }

                testedBatches++;
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

            for (int batchNumber = 1; batchNumber < outputLines.length; batchNumber++)
                assertPrintedStatsCorrectly(outputLines[batchNumber], batchNumber);
        }

        private static void assertPrintedStatsCorrectly(String line, int batchNumber) {
            String[] parts = line.split("\\s+");

            System.out.println("===");
            System.out.println(Arrays.toString(parts));
        }

        private static class TestTest extends DoublingRatioTestV2 {
            public List<int[]> doRunExperimentCalls = new ArrayList();
            public List<int[]> beforeBatchCalls = new ArrayList();

            @Override
            protected String label() { return "test"; }

            @Override
            protected void doRunExperiment(int i, int N, int runsPerBatch) {
                doRunExperimentCalls.add(new int[] { i, N, runsPerBatch });
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
            long baseTime = 10;

            double x = 0;

            // Simulate quadratic complexity
            public long elapsedTime() {
                long result = (long) (baseTime * (i+1) * Math.pow(2, batchNumber));

                if (i == RUNS_PER_BATCH-1) { i = 0; batchNumber++; x = 0; }
                else i++;

                return result;
            }

            public void reset() {}
        }
    }
}