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
        printAccumulatorHeaders();
        printSeparator();
        printAccumulatorSubHeaders();
        printSeparator();
    }

    private void printAccumulatorHeaders() {
        out.print("| ");

        for (Map.Entry<String, StatsAccumulator> e : statsAccumulators.entrySet()) {
            String statId = e.getKey();
            StatsAccumulator acc = e.getValue();
            printAccumulatorHeaders(statId, acc);
        }

        out.print("\n");
    }

    private void printAccumulatorHeaders(String statId, StatsAccumulator acc) {
        int spaceLeft = -statId.length() - 3;

        for (Stat stat : acc.stats())
            spaceLeft += stat.minLength() + 3;

        out.print(statId);

        for (int i = 0; i < spaceLeft; i++)
            out.print(" ");

        out.print(" | ");
    }

    private void printAccumulatorSubHeaders() {
        out.print("| ");

        for (Map.Entry<String, StatsAccumulator> e : statsAccumulators.entrySet()) {
            StatsAccumulator acc = e.getValue();
            printHeader(acc);
        }

        out.print("\n");
    }

    private void printHeader(StatsAccumulator acc) {
        for (Stat stat : acc.stats()) {
            String paddedLabel = padRight(stat.label(), stat.minLength());
            out.print(paddedLabel);
            out.print(" | ");
        }
    }

    public void print() {
        printAccumulatedStats();
    }

    private void printAccumulatedStats() {
        out.print("| ");

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
            out.print(" | ");
        }
    }

    private String padRight(String string, int length) {
        StringBuilder s = new StringBuilder(string);

        for (int i = 0; i < length - string.length(); i++)
            s.append(" ");

        return s.toString();
    }

    private void printSeparator() {
        out.print("|");

        StatsAccumulator[] accs =
            statsAccumulators.values().toArray(new StatsAccumulator[statsAccumulators.size()]);

        for (StatsAccumulator acc : accs) {
            out.print("=");

            for (Stat stat : acc.stats()) {
                for (int i = 0; i < stat.minLength() - 1; i++) out.print("=");
                out.print("===");
            }

            out.print("==|");
        }

        out.print("\n");
    }
}