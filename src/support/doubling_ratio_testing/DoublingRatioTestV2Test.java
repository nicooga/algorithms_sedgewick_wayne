package algsex.support.doubling_ratio_testing;

import edu.princeton.cs.algs4.StdRandom;
import java.math.*;
import java.util.*;
import algsex.support.*;

public class DoublingRatioTestV2Test {
    private static final int INITIAL_N = 4;
    private static final int BATCH_SIZE = 5;
    private static final double BASE_TIME = 10;
    private final static OutputInterceptor outputInterceptor = new OutputInterceptor();

    public static void main(String[] args) {
        TestContext context = setup();
        context.test.run();
        performAssertions(context);
    }

    private static TestContext setup() {
        Config config = new Config();

        config.initialN = INITIAL_N;
        config.batchSize = BATCH_SIZE;

        TestExperiment experiment = new TestExperiment();

        DoublingRatioTestV2 test = new DoublingRatioTestV2(
            config,
            experiment,
            outputInterceptor,
            new TestStopwatch()
        );

        TestContext context = new TestContext();

        context.test = test;
        context.experiment = experiment;

        return context;
    }

    private static void performAssertions(TestContext context) {
        assertBeforeBatchCalledCorrectly(context);
        assertDoRunExperimentCalledCorrectly(context);
        assertOutputIsCorrect();
    }

    private static void assertBeforeBatchCalledCorrectly(TestContext context) {
        int index = 0;
        int N = 4;

        while (N > 0) {
            Test.assertArrayEquals(
                context.experiment.beforeBatchCalls.get(index),
                new int[] { N, BATCH_SIZE }
            );
            index++;
            N *= 2;
        }
    }

    private static void assertDoRunExperimentCalledCorrectly(TestContext context) {
        int batchNumber = 0;
        int N = 4;

        while (N > 0) {
            int offset = batchNumber * BATCH_SIZE;

            for (int i = 0; i < BATCH_SIZE; i++) {
                Object[] args = context.experiment.doRunExperimentCalls.get(offset + i);

                Test.assertEqual((int) args[0], i);
                Test.assertEqual((int) args[1], N);
                Test.assertEqual((int) args[2], BATCH_SIZE);

                RunDetails d = (RunDetails) args[3];

                Test.assertEqual(
                    (double) d.get("time"),
                    expectedTime(batchNumber, i)
                );
            }

            batchNumber++;
            N *= 2;
        }
    }

    private static void assertOutputIsCorrect() {
        String[] outputLines = outputInterceptor.contents().split("\n");

        assertPrintedTestConfigCorrectly(outputLines);
        assertPrintedBatchStatsHeaderCorrectly(outputLines);
        assertPrintedStatsCorrectly(outputLines);
    }

    private static void assertPrintedTestConfigCorrectly(String[] outputLines) {
        Test.assertEqual(outputLines[0], "Running experiment \"test experiment\"");
        Test.assertEqual(outputLines[1], "Runs per batch: 5");
        Test.assertEqual(outputLines[2], "===");
    }

    private static void assertPrintedBatchStatsHeaderCorrectly(String[] outputLines) {
        Test.assertEqual(
            outputLines[3],
            "| time                                                                        | "
        );

        Test.assertEqual(
            outputLines[4],
            "|=============================================================================="
        );

        Test.assertEqual(
            outputLines[5],
            "| N             | mean            | mean ratio | stddev.         | CV         | "
        );

        Test.assertEqual(
            outputLines[6],
            "|=============================================================================="
        );
    }

    private static void assertPrintedStatsCorrectly(String[] outputLines) {
        assert outputLines.length > 1;

        int offset = 7;

        for (
            int batchNumber = 0;
            batchNumber + offset <= outputLines.length-1;
            batchNumber++
        )
            assertPrintedStatsCorrectly(
                outputLines[batchNumber+offset],
                batchNumber
            );
    }

    private static void assertPrintedStatsCorrectly(String line, int batchNumber) {
        String[] parts = line.split("\\s*\\|\\s*");

        double mean = Double.parseDouble(parts[2]);
        double meanRatio = Double.parseDouble(parts[3]);
        double sampleStandardDeviation = Double.parseDouble(parts[4]);
        double coefficientOfVariation = Double.parseDouble(parts[5]);

        Test.assertEqual(mean, expectedMean(batchNumber));

        if (batchNumber > 0) Test.assertEqual(meanRatio, 2);

        Test.assertEqual(
            sampleStandardDeviation,
            expectedSampleStandardDeviation(batchNumber)
        );

        Test.assertEqual(coefficientOfVariation, 0.53);
    }

    private static double expectedTime(int batchNumber, int iteration) {
        return BASE_TIME * (iteration+1) * Math.pow(2, batchNumber);
    }

    private static double expectedMean(int batchNumber) {
        return BASE_TIME * Math.pow(2, batchNumber-1) * (BATCH_SIZE + 1);
    }

    private static double expectedSampleStandardDeviation(int batchNumber) {
        int b = batchNumber;
        int B = BATCH_SIZE;
        double Ts = BASE_TIME;

        return round(Ts * Math.pow(2, b-1) * Math.sqrt((1/3.0) * B * (B+1)), 2);
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    private static class TestExperiment extends Experiment {
        public List<Object[]> doRunExperimentCalls = new ArrayList();
        public List<int[]> beforeBatchCalls = new ArrayList();

        @Override
        protected String label() { return "test experiment"; }

        @Override
        protected void beforeBatch(int N, int batchSize) {
            beforeBatchCalls.add(new int[] { N, batchSize });
        }

        @Override
        protected RunDetails run(int i, int N, int batchSize, RunDetails d) {
            doRunExperimentCalls.add(new Object[] { i, N, batchSize, d });

            return d;
        }
    }

    private static class TestContext {
        DoublingRatioTestV2 test;
        TestExperiment experiment;
    }

    private static class TestStopwatch implements Stopwatch {
        int batchNumber = 0; // zero-indexed
        int i = 0;

        double x = 0;

        // Simulate quadratic complexity
        public long elapsedTime() {
            long result = (long) (BASE_TIME * (i+1) * Math.pow(2, batchNumber));

            if (i == BATCH_SIZE-1) { i = 0; batchNumber++; x = 0; }
            else i++;

            return result;
        }

        public void reset() {}
    }
}