package algsex.support.union_find;

import algsex.support.*;

public interface InstrumentedUnionFind extends UnionFind {
    InstrumentedArray<Integer> id();
    int arrayAccesses();
    void resetCounters();
}