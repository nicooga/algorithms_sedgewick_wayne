package algsex.support.union_find;

import edu.princeton.cs.algs4.*;

public class UnionFindCLI {
    public static void main(UnionFindFactory unionFindFactory) {
        int N = StdIn.readInt();
        UnionFind uf = unionFindFactory.generate(N);

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