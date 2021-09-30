package algsex.support;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;

public abstract class DoublingRatioExperiment {
    public void run() {
        StdOut.println("Running experiment for " + label());

        double prevTime = runTimeTrial(1);

        for (int N = 2; iterationCondition(N); N *= 2) {
            double time = runTimeTrial(N);
            double ratio = time/prevTime;
            StdOut.printf("%d %5.1f %5.1f\n", N, time, ratio);
            prevTime = time;
        }
    }

    private double runTimeTrial(int N) {
        Stopwatch timer = new Stopwatch();
        doRunExperiment(N);
        return timer.elapsedTime();
    }

    protected abstract void doRunExperiment(int N);
    protected abstract String label();

    protected boolean iterationCondition(int N) {
        return N > 0;
    }
}