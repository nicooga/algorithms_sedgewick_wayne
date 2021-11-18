package algsex.support;

public class StdOut implements Out {
    public void print(String s) {
        edu.princeton.cs.algs4.StdOut.print(s);
    }

    public void print(Object o) {
        edu.princeton.cs.algs4.StdOut.print(o);
    }

    public void println(Object o) {
        edu.princeton.cs.algs4.StdOut.println(o);
    }

    public void println(String s) {
        edu.princeton.cs.algs4.StdOut.println(s);
    }

    public void printf(String s, Object... args) {
        edu.princeton.cs.algs4.StdOut.printf(s, args);
    }

    public static void main(String[] args) {
        Out out = new StdOut();

        out.println("test");
        out.printf("test\n");
        out.printf("test with some number x: %d\n", 10);
    }
}