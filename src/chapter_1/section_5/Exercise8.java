package algsex.chapter1.section5;

import edu.princeton.cs.algs4.*;
import algsex.support.Test;
import algsex.misc.UnionFind;

// 1.5.8 Give a counterexample that shows why this intuitive implementation of union()
// for quick-find is not correct:
//
// 
//     public void union(int p, int q)
//     {
//         if (connected(p, q)) return;
//         // Rename p’s component to q’s name.
//         for (int i = 0; i < id.length; i++)
//             if (id[i] == id[p]) id[i] = id[q];
//         count--
//     }
//
public class Exercise8 {
    public static void main(String[] args) {
        UnionFind bad = new BadImplementation(3);

        bad.union(0, 2);
        bad.union(0, 1);

        Test.assertFalse(connected(bad, new int[] { 0, 1, 2 }));

        UnionFind fixed = new FixedImplementation(3);

        fixed.union(0, 2);
        fixed.union(0, 1);

        Test.assertTrue(connected(fixed, new int[] { 0, 1, 2 }));

        StdOut.println("Tests passed");
    }

    private static boolean connected(UnionFind uf, int[] sites) {
        int firstComponent = uf.find(sites[0]);

        for (int i = 1; i < sites.length; i++)
            if (uf.find(sites[i]) != firstComponent) return false;

        return true;
    }

    private static class BadImplementation implements UnionFind {
        private int[] id;
        private int count;
    
        public BadImplementation(int N) {
            count = N;
            id = new int[N];
            for (int i = 0; i < N; i++) id[i] = i;
        }
    
        public int count() { return count; }
        public boolean connected(int p, int q) { return find(p) == find(q); }
    
        public void union(int p, int q) {
            if (connected(p, q)) return;

            for (int i = 0; i < id.length; i++)
                if (id[i] == id[p]) id[i] = id[q];

            count--;
        }
    
        public int find(int p) {
            while (p != id[p]) p = id[p];
            return p;
        }
    }

    private static class FixedImplementation implements UnionFind {
        private int[] id;
        private int count;
    
        public FixedImplementation(int N) {
            count = N;
            id = new int[N];
            for (int i = 0; i < N; i++) id[i] = i;
        }
    
        public int count() { return count; }
        public boolean connected(int p, int q) { return find(p) == find(q); }
    
        public void union(int p, int q) {
            if (connected(p, q)) return;

            int pId = id[p];

            for (int i = 0; i < id.length; i++)
                if (id[i] == pId) id[i] = id[q];

            count--;
        }
    
        public int find(int p) {
            while (p != id[p]) p = id[p];
            return p;
        }
    }
}
