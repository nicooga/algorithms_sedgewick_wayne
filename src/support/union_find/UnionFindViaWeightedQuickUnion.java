package algsex.support.union_find;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import algsex.support.union_find.*;

public class UnionFindViaWeightedQuickUnion implements UnionFind {
    private int[] id;
    private int[] sz;
    private int count;

    public UnionFindViaWeightedQuickUnion(int N) {
        count = N;

        id = new int[N];
        for (int i = 0; i < N; i++) id[i] = i;

        sz = new int[N];
        for (int i = 0; i < N; i++) sz[i] = 1;
    }

    public int count() { return count; }
    public boolean connected(int p, int q) { return find(p) == find(q); }

     public void union(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);

        if (pRoot == qRoot) return;

        if (sz[pRoot] > sz[qRoot]) {
            id[qRoot] = pRoot;
            sz[pRoot] += sz[qRoot];
        } else {
            id[pRoot] = qRoot;
            sz[qRoot] += sz[pRoot];
        }

        count--;
    }

    public int find(int p) {
        while (p != id[p]) p = id[p];
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