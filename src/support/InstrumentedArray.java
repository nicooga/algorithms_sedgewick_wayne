package algsex.support;

import java.util.Arrays;

public class InstrumentedArray<T> implements Array<T> {
    private final T[] a;
    private int accesses;

    public InstrumentedArray(int N) {
        a = (T[]) new Object[N];
    }

    public T get(int i) {
        T item = a[i];
        accesses++;
        return item;
    }

    public void set(int i, T value) {
        a[i] = value;
        accesses++;
    }

    public int length() { return a.length; }
    public int accesses() { return accesses; }
    public String toString() { return Arrays.toString(a); }
    public void resetCounters() { accesses = 0; }

    public class Factory<T> implements ArrayFactory<T> {
        public Array<T> create(int N) {
            return new InstrumentedArray<T>(N);
        }
    }
}