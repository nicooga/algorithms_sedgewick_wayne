package algsex.support.union_find;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import algsex.support.*;
import algsex.support.union_find.*;

public class UnionFindViaWeightedQuickUnion implements UnionFind {
    private final Array<Integer> id;
    private final Array<Integer> sz;
    private int count;

    public UnionFindViaWeightedQuickUnion(int N) {
        this(
            N,
            new WrappedArray<Integer>(N),
            new WrappedArray<Integer>(N)
        );
    }

    public UnionFindViaWeightedQuickUnion(int N, Array<Integer> id, Array<Integer> sz) {
        this.count = N;
        this.id = id;
        this.sz = sz;
        for (int i = 0; i < N; i++) id.set(i, i);
        for (int i = 0; i < N; i++) sz.set(i, 1);
    }

    public int count() { return count; }
    public boolean connected(int p, int q) { return find(p) == find(q); }

     public void union(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);

        if (pRoot == qRoot) return;

        if (sz.get(pRoot) > sz.get(qRoot)) point(qRoot, pRoot);
        else point(pRoot, qRoot);

        count--;
    }

    private void point(int source, int target) {
        id.set(source, target);
        sz.set(target, sz.get(target) + sz.get(source));
    }

    public int find(int p) {
        while (p != id.get(p)) p = id.get(p);
        return p;
    }

    public static void main(String[] args) {
        UnionFindFactory factory = new Factory();

        if (args.length > 0) {
            UnionFindCLI.main(factory);
            return;
        }

        UnionFindTest.runTest(factory);
    }

    public static class Factory implements UnionFindFactory {
        public UnionFind generate(int N) {
            return new UnionFindViaWeightedQuickUnion(N);
        }
    }
}