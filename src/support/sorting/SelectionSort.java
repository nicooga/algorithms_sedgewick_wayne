package algsex.support.sorting;

public class SelectionSort implements Sort {
    private final SortHelper sortHelper = new SortHelper();
    private final InstrumentationHelper instrumentationHelper;

    public SelectionSort() {
        this(new NoopInstrumentationHelper());
    }

    public SelectionSort(InstrumentationHelper instrumentationHelper) {
        this.instrumentationHelper = instrumentationHelper;
    }

    public void sort(Comparable[] a) {
        int N = a.length;

        instrumentationHelper.initialize(a);

        for (int i = 0; i < N; i++) {
            int min = i;

            instrumentationHelper.onCursorChange("i", i);
            instrumentationHelper.onCursorChange("m", min);

            for (int j = i+1; j < N; j++) {
                instrumentationHelper.onCursorChange("j", j);

                if (less(a, j, min)) {
                    min = j;
                    instrumentationHelper.onCursorChange("m", min);
                }

                instrumentationHelper.draw();
            }

            exchange(a, i, min);
            instrumentationHelper.draw();
        }
    }

    private boolean less(Comparable[] a, int i, int j) {
        instrumentationHelper.onComparison(i, j);
        return sortHelper.less(a[i], a[j]);
    }
 
    private void exchange(Comparable[] a, int i, int j) {
        instrumentationHelper.onExchange(i, j);
        sortHelper.exchange(a, i, j);
    }

    public static void main(String[] _args) {
        new SortingTest().run(new SelectionSort());
    }
}