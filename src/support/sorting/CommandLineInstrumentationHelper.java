package algsex.support.sorting;

import java.util.Arrays;
import java.util.Set;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;
import algsex.support.*;

public class CommandLineInstrumentationHelper implements InstrumentationHelper {
    private static final char BLOCK_CHARACTER = (char) 0X2588;

    private int[] a;
    private Set<Integer> comparedIndexes = new HashSet<>();
    private Set<Integer> exchangedIndexes = new HashSet<>();
    private Map<String, Integer> cursors = new HashMap<>();
    private Out out = new StdOut();

    @Override
    public void initialize(Comparable[] a) {
        this.a = flattenedOrderedIndexes(a);
        this.comparedIndexes.clear();
        this.exchangedIndexes.clear();
    };

    private static int[] flattenedOrderedIndexes(Comparable[] a) {
        int N = a.length;
        Comparable[] temp = Arrays.copyOf(a, N);
        Map<Comparable, Integer> elToOrder = new HashMap<>(N);

        Arrays.sort(temp);

        for (int i = 0; i < N; i++)
            elToOrder.put(temp[i], i+1);

        int[] b = new int[N];

        for (int i = 0; i < N; i++)
            b[i] = elToOrder.get(a[i]);

        return b;
    }

    @Override
    public void onCursorChange(String cursorId, int value) {
        cursors.put(cursorId, value);
    };

    @Override
    public void onExchange(int i, int j) {
        exchange(i, j);
        exchangedIndexes.clear();
        exchangedIndexes.add(i);
        exchangedIndexes.add(j);
    };

    private void exchange(int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    @Override
    public void onComparison(int i, int j) {
        comparedIndexes.clear();
        comparedIndexes.add(i);
        comparedIndexes.add(j);
    };

    public void draw() {
        doDraw();
        comparedIndexes.clear();
        exchangedIndexes.clear();
    }

    private void doDraw() {
        printBars();
        printCursors();
        printComparisons();
        printExchanges();
        printSeparator();
    }

    private void printBars() {
        for (int i = a.length; i >= 1; i--) {
            for (int el : a) {
                if (el >= i) out.print(BLOCK_CHARACTER);
                else out.print(" ");
                out.print(" ");
            }

            out.print("\n");
        }
    }

    private void printCursors() {
        for (Map.Entry<String, Integer> e : cursors.entrySet()) {
            String cursorId = e.getKey();
            int value = e.getValue();
            printCursor(cursorId, value);
        }
    }

    private void printCursor(String label, int value) {
        for (int i = 0; i < a.length; i++) {
            if (i == value) out.print(label);
            else out.print(" ");
            out.print(" ");
        }

        out.print("\n");
    }

    private void printComparisons() {
        printOperationOverIndexes(comparedIndexes, "c");
    }

    private void printExchanges() {
        printOperationOverIndexes(exchangedIndexes, "e");
    }

    private void printOperationOverIndexes(Set indexes, String label) {
        if (indexes.size() == 0) return;

        for (int i = 0; i < a.length; i++) {
            if (indexes.contains(i)) out.print(label);
            else out.print(" ");
            out.print(" ");
        }

        out.print("\n");
    }

    private void printSeparator() {
        for (int i = 1; i < a.length; i++) out.print("--");
        out.print("-\n");
    }

    public static void main(String[] args) {
        InstrumentationHelper instrumentationHelper = new CommandLineInstrumentationHelper();
        instrumentationHelper.initialize(new Integer[] { 0, 2, 4, 10, 3, 5, 1  });
        instrumentationHelper.onComparison(0, 1);
        instrumentationHelper.draw();
    }
}