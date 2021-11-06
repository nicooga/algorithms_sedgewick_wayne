package algsex.support;

public class SystemStopwatch implements Stopwatch {
    private long start;

    public SystemStopwatch() {
        start = System.currentTimeMillis();
    }

    public long elapsedTime() {
        long now = System.currentTimeMillis();
        return now - start;
    }

    public void reset() {
        start = System.currentTimeMillis();
    }
}