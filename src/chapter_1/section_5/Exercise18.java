package algsex.chapter1.section5;

import edu.princeton.cs.algs4.*;
import algsex.chapter1.section3.Exercise34.RandomBag;

// 1.5.18 Random grid generator. Write a program RandomGrid that takes an int value
// N from the command line, generates all the connections in an N-by-N grid, puts them
// in random order, randomly orients them (so that p q and q p are equally likely to oc-
// cur), and prints the result to standard output. To randomly order the connections, use
// a RandomBag (see Exercise 1.3.34 on page 167). To encapsulate p and q in a single object,
// use the Connection nested class shown below. Package your program as two static
// methods: generate(), which takes N as argument and returns an array of connec-
// tions, and main(), which takes N from the command line, calls generate(), and iterates
// through the returned array to print the connections.
public class Exercise18 {
    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        Connection[] connections = RandomGrid.generate(N);

        for (Connection c : connections)
            StdOut.println(c.p + " - " + c.q);
    }

    private static class RandomGrid {
        public static Connection[] generate(int N) {
            RandomBag<Connection> bag = new RandomBag<>();
            Connection[] connections = new Connection[N*N];

            for (int p = 0; p < N; p++)
                for (int q = 0; q < N; q++)
                    bag.add(new Connection(p, q));

            int index = 0;

            for (Connection c : bag) connections[index++] = c;

            return connections;
        }
    }

    private static class Connection {
        int p;
        int q;

        public Connection(int p, int q) {
            this.p = p;
            this.q = q;
        }
    }
}
