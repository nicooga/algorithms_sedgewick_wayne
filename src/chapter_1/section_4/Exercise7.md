1.4.7 Analyze ThreeSum under a cost model that counts arithmetic operations (and
comparisons) involving the input numbers.

~~~java
public class ThreeSum {
    public static int count(int[] a) {
        int N = a.length;
        int cnt = 0;

        for (int i = 0; i < N; i++) // i is incremented N-1 times
            for (int j = i+1; j < N; j++) // for each sdasdi, j is incremented (N-1) - (i + 1) times
                for (int k = j+1; k < N; k++) // for each j, k is incremented (N - 1) - (j + 1) times
                    if (a[i] + a[j] + a[k] == 0) // for each k, 3 sums and and one comparison ocur
                        cnt++; // depends on the number of triples found

        return cnt;
    }

    public static void main(String[] args) {
        int[] a = In.readInts(args[0]);
        StdOut.println(count(a));
    }
}
~~~