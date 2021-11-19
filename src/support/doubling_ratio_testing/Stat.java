package algsex.support.doubling_ratio_testing;

public class Stat {
    private final String label;
    private final int minLength;
    private final int priority;

    private double value = -1;

    public Stat(String label, int priority, int minLength) {
        this.label = label;
        this.priority = priority;
        this.minLength = minLength;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String label() { return label; }
    public int minLength() { return minLength; }
    public int priority() { return priority; }
    public double getValue() { return this.value; }
}