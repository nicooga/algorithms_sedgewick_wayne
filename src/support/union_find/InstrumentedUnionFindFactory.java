package algsex.support.union_find;

interface InstrumentedUnionFindFactory extends UnionFindFactory {
    public UnionFind generate(int N);
}