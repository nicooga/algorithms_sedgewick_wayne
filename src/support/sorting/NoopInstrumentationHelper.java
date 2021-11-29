package algsex.support.sorting;

public class NoopInstrumentationHelper implements InstrumentationHelper {
    @Override
    public void initialize(Comparable[] a) {}

    @Override
    public void onCursorChange(String cursorId, int value) {}

    @Override
    public void onExchange(int i, int j) {}

    @Override
    public void onComparison(int i, int j) {}

    @Override
    public void draw() {}
}