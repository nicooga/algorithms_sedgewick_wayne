package algsex.chapter1.section5;

import java.util.*;
import edu.princeton.cs.algs4.*;
import algsex.misc.UnionFind;
import algsex.misc.UnionFindTest;

// 1.5.14 Weighted quick-union by height. Develop a UF implementation that uses the
// same basic strategy as weighted quick-union but keeps track of tree height and always
// links the shorter tree to the taller one. Prove a logarithmic upper bound on the height
// of the trees for N sites with your algorithm.
public class Exercise14 {
    public static void main(String[] args) {
        UnionFindTest.runTest(new UnionFindViaWeightedQuickUnionByHeight(8));
        StdOut.println("Tests passed");
    }

    private static class UnionFindViaWeightedQuickUnionByHeight implements UnionFind {
        private int[] id;
        private int[] h;
        private int count;
    
        public UnionFindViaWeightedQuickUnionByHeight(int N) {
            count = N;
    
            id = new int[N];
            for (int i = 0; i < N; i++) id[i] = i;
    
            h = new int[N];
            for (int i = 0; i < N; i++) h[i] = 0;
        }
    
        public int count() { return count; }
        public boolean connected(int p, int q) { return find(p) == find(q); }
    
         public void union(int p, int q) {
            int pRoot = find(p);
            int qRoot = find(q);
    
            if (pRoot == qRoot) return;
    
            if (h[pRoot] < h[qRoot]) point(pRoot, qRoot);
            else point (qRoot, pRoot);
    
            count--;
        }
    
        public int find(int p) {
            while (p != id[p]) p = id[p];
            return p;
        }

        private void point(int source, int target) {
            id[source] = target;
            h[target] = Math.max(h[source] + 1, h[target]);
        }
    }
}
