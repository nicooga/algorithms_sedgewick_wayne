package algsex.support.union_find;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import algsex.support.union_find.*;

public class UnionFindViaQuickUnion implements UnionFind {
    private int[] id;
    private int count;

    public UnionFindViaQuickUnion(int N) {
        count = N;
        id = new int[N];
        for (int i = 0; i < N; i++) id[i] = i;
    }

    public int count() { return count; }
    public boolean connected(int p, int q) { return find(p) == find(q); }

     public void union(int p, int q) {
        int pRootId = find(p);
        int qRootId = find(q);

        if (pRootId == qRootId) return;

        id[pRootId] = qRootId;

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
            return new UnionFindViaQuickUnion(N);
        }
    }
}