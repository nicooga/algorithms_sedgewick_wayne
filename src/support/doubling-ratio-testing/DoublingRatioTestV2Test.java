package algsex.support.doubling_ratio_testing;

import edu.princeton.cs.algs4.StdRandom;
import java.math.*;
import java.util.*;
import algsex.support.*;

class DoublingRatioTestV2Test {
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

                Test.assertEqual(
                    d.getDouble("time"),
                    expectedTime(batchNumber, i)
                );
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