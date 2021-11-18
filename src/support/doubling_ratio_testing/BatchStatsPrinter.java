package algsex.support.doubling_ratio_testing;

import java.util.*;
import algsex.support.*;

class BatchStatsPrinter {
    private final Map<String, StatsAccumulator> statsAccumulators;
    private final Out out;

    public BatchStatsPrinter(
        Map<String, StatsAccumulator> statsAccumulators,
        Out out
    ) {
        this.statsAccumulators = statsAccumulators;
        this.out = out;
    }

    public void printHeader() {
        for (Map.Entry<String, StatsAccumulator> e : statsAccumulators.entrySet()) {
            String statId = e.getKey();
            StatsAccumulator acc = e.getValue();
            printHeader(acc);
        }

        out.print("\n");
    }

    private void printHeader(StatsAccumulator acc) {
        for (Stat stat : acc.stats()) {
            String paddedLabel = padRight(stat.label(), stat.minLength());
            out.print(paddedLabel);
            out.print("\t");
        }
    }

    public void print() {
        for (Map.Entry<String, StatsAccumulator> e : statsAccumulators.entrySet()) {
            String statId = e.getKey();
            StatsAccumulator acc = e.getValue();
            printAccumulatedStats(statId, acc);
        }

        out.print("\n");
    }

    private void printAccumulatedStats(String statId, StatsAccumulator acc) {
        for (Stat stat : acc.stats()) {
            String value = String.format("%.2f", stat.getValue());
            String paddedValue = padRight(value, stat.minLength());
            out.print(paddedValue);
            out.print("\t");
        }
    }

    private String padRight(String string, int length) {
        StringBuilder s = new StringBuilder(string);

        for (int i = 0; i < length - string.length(); i++)
            s.append(" ");

        return s.toString();
    }
}