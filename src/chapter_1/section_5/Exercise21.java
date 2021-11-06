package algsex.chapter1.section5;

import java.util.*;
import edu.princeton.cs.algs4.*;
import algsex.support.Test;
import algsex.chapter1.section5.Exercise17.ErdosRenyi;

// 1.5.21 Erdös-Renyi model. Use your client from Exercise 1.5.17 to test the hypothesis
// that the number of pairs generated to get one component is ~ ½N ln N.
public class Exercise21 {
    private static final int MAX_TRIALS_PER_N = 10000;

    public static void main(String[] args) {
        for (int N = 16; N > 0; N*=2) {
            double expectedCount = N/2.0 * Math.log(N);
            double averageCount = ErdosRenyi.count(N);

            for (double i = 2; i <= MAX_TRIALS_PER_N+1; i++) {
                int count = ErdosRenyi.count(N);
                averageCount = averageCount * ((i-1)/i) + count / i;
                double deviation = Math.abs(1 - averageCount/expectedCount);

                if (deviation < 0.1) {
                    StdOut.printf("When N = %s, the results converged after %s trials.\n", N, i);
                    break;
                }
            }
        }
    }
}
