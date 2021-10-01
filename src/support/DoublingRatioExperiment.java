package algsex.support;

import java.util.Arrays;
import java.util.DoubleSummaryStatistics;
import edu.princeton.cs.algs4.StdOut;
import algsex.support.Stopwatch;

public abstract class DoublingRatioExperiment {
    protected abstract void doRunExperiment(int N);
    protected abstract String label();

    public void run() {
        run(1);
    }

    public void run(int runsPerN) {
        StdOut.println("Running experiment \"" + label() + "\"");
        StdOut.println("Runs per N: " + runsPerN);
        StdOut.println("N, avg. time (ms), avg. ratio, time std.");

        double prevTime = -1;

        for (int N = 1; iterationCondition(N); N *= 2) {
            double[] times = new double[runsPerN];
            double[] ratios = new double[runsPerN];

            for (int i = 0; i < runsPerN; i++) {
                double time = runTimeTrial(N);
                times[i] = time;
                if (prevTime != -1 && prevTime > 0) ratios[i] = time/prevTime;
            }

            double avgTime = average(times);
            double avgRatio = average(ratios);
            double timeStdDeviation = sampleStandardDevation(times);

            StdOut.printf("%d\t%5.1f\t%5.1f\t%5.1f\n", N, avgTime, avgRatio, timeStdDeviation);

            prevTime = avgTime;
        }
    }

    protected boolean iterationCondition(int N) {
        return N > 0;
    }

    private double runTimeTrial(int N) {
        Stopwatch timer = new Stopwatch();
        doRunExperiment(N);
        return timer.elapsedTime();
    }

    private double sampleStandardDevation(double[] values) {
        int N = values.length;
        double mean = average(values);
        double squaredMeanDeviationsSum = 0;

        for (int i = 0; i < N; i++)
            squaredMeanDeviationsSum += Math.pow(values[i] - mean, 2);

        double sampleVariance = squaredMeanDeviationsSum / (N - 1);

        return Math.sqrt(sampleVariance);
    }

    private double average(double[] values) {
        return Arrays
            .stream(values)
            .summaryStatistics()
            .getAverage();
    }
}