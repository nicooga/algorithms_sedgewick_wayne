import edu.princeton.cs.algs4.StdOut;

public class Exercise3 {
    public static void main(String[] args) {
        int int1 = Integer.parseInt(args[0]);
        int int2 = Integer.parseInt(args[1]);
        int int3 = Integer.parseInt(args[2]);

        if (int1 == int2 && int1 == int3) {
            StdOut.println("equal");
        } else {
            StdOut.println("not equal");
        }
    }
}