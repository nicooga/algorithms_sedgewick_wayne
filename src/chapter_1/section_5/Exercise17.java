package algsex.chapter1.section5;

import edu.princeton.cs.algs4.*;
import algsex.misc.UnionFind;
import algsex.misc.UnionFindViaWeightedQuickUnion;

// 1.5.17 Random connections. Develop a UF client ErdosRenyi that takes an integer
// value N from the command line, generates random pairs of integers between 0 and N-1,
// calling connected() to determine if they are connected and then union() if not (as in
// our development client), looping until all sites are connected, and printing the number
// of connections generated. Package your program as a static method count() that takes
// N as argument and returns the number of connections and a main() that takes N from
// the command line, calls count(), and prints the returned value.
public class Exercise17 {
    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        StdOut.println(ErdosRenyi.count(N));
    }

    private static class ErdosRenyi {
        public static int count(int N) {
            UnionFind uf = new UnionFindViaWeightedQuickUnion(N);
            int connections = 0;

            while (uf.count() != 1) {
                connections++;

                int p = StdRandom.uniform(N);
                int q = StdRandom.uniform(N);

                if (!uf.connected(p, q)) uf.union(p, q);
            }

            return connections;
        }
    }
}
