package algsex.support;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;

public abstract class DoublingRatioExperiment {
    public void run() {
        double prevTime = runTimeTrial(1);

        for (int N = 2; N > 0; N *= 2) {
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
}