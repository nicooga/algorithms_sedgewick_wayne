package algsex.support.union_find;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import algsex.support.union_find.*;

public class UnionFindViaQuickFind implements UnionFind {
    private int[] id;
    private int count;

    public UnionFindViaQuickFind(int N) {
        count = N;
        id = new int[N];
        for (int i = 0; i < N; i++) id[i] = i;
    }

    public int count() { return count; }
    public boolean connected(int p, int q) { return find(p) == find(q); }

     public void union(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);

        if (pRoot == qRoot) return;

        for (int i = 0; i < id.length; i++)
            if (id[i] == pRoot) id[i] = qRoot;

        count--;
    }

    public int find(int p) { return id[p]; }

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
            return new UnionFindViaQuickFind(N);
        }
    }
}