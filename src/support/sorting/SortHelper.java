package algsex.support.sorting;

public class SortHelper {
    public boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    public void exchange(Comparable[] a, int i, int j) {
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}