package algsex.support;

public interface ArrayFactory<T> {
    public Array<T> create(int length);
}