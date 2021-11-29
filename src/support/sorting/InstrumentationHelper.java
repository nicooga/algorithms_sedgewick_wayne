package algsex.support.sorting;

public interface InstrumentationHelper {
    public void initialize(Comparable[] a);
    public void onCursorChange(String cursorId, int value);
    public void onExchange(int i, int j);
    public void onComparison(int i, int j);
    public void draw();
}