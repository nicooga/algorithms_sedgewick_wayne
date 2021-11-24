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
        int N = StdIn.readInt();
        UnionFind uf = new UnionFindViaQuickFind(N);

        while (!StdIn.isEmpty()) {
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            if (uf.connected(p, q)) continue;
            uf.union(p, q);
            StdOut.println(p + " " + q);
        }

        StdOut.println(uf.count() + " components");
    }
}