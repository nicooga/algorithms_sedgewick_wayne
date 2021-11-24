package algsex.support.union_find;

import algsex.support.*;

public class InstrumentedUnionFindViaWeightedQuickUnion implements InstrumentedUnionFind {
    private InstrumentedArray<Integer> id;
    private InstrumentedArray<Integer> sz;
    private UnionFind unionFind;

    public InstrumentedUnionFindViaWeightedQuickUnion(int N) {
        this.id = new InstrumentedArray(N);
        this.sz = new InstrumentedArray(N);
        this.unionFind = new UnionFindViaWeightedQuickUnion(N, id, sz);
    }

    public int count() { return unionFind.count(); }
    public boolean connected(int p, int q) { return unionFind.connected(p, q); }
    public int find(int p) { return unionFind.find(p); }
    public void union(int p, int q) { unionFind.union(p, q); }

    public int arrayAccesses() {
        return id.accesses() + sz.accesses();
    }

    public void resetCounters() {
        id.resetCounters();
        sz.resetCounters();
    }

    public InstrumentedArray<Integer> id() {
        return id;
    }

    public static void main(String[] args) {
        UnionFindTest.runTest(new Factory());
    }

    public static class Factory implements InstrumentedUnionFindFactory {
        public UnionFind generate(int N) {
            return new InstrumentedUnionFindViaWeightedQuickUnion(N);
        }
    }
}