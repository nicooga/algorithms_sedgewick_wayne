package algsex.support;

public class WrappedArray<T> implements Array<T> {
    private final T[] a;

    public WrappedArray(int N) {
        this.a = (T[]) new Object[N];
    }

    public void set(int i, T value) {
        a[i] = value;
    }

    public T get(int i) {
        return a[i];
    }

    public static class Factory<T> implements ArrayFactory<T> {
        public Array<T> create(int N) {
            return new WrappedArray<T>(N);
        }
    }
}