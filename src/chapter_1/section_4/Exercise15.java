package algsex.chapter1.section4;

import java.util.*;
import edu.princeton.cs.algs4.*;
import algsex.support.Test;

// 1.4.15 Faster 3-sum. As a warmup, develop an implementation TwoSumFaster that
// uses a linear algorithm to count the pairs that sum to zero after the array is sorted (in-
// stead of the binary-search-based linearithmic algorithm). Then apply a similar idea to
// develop a quadratic algorithm for the 3-sum problem.
public class Exercise15 {
    public static void main(String[] args) {
        Test.assertEqual(
            TwoSumFaster.count(new int[] { 3, 3, 2, -2, 0, -3 }),
            3
        );

        Test.assertEqual(
            TwoSumFaster.count(new int[] { 3, 3, -3, 2, -2, 0, -3 }),
            5
        );

        Test.assertEqual(
            ThreeSumFaster.count(new int[] { 1, 3, 1, 3, 3, 3, 5, 4, -6}),
            8
        );

        Test.assertEqual(
            ThreeSumFaster.count(new int[] { 1, 3, 1, 3, 3, 3, 5, -6}),
            8
        );
    }

    private static class TwoSumFaster {
        public static int count(int[] a) {
            int count = 0;
            int i = 0;
            int j = a.length-1;

            Arrays.sort(a);

            while (i != j) {
                int matchMultiplier = 1;

                while (a[i+1] == a[i]) {
                    i++;
                    matchMultiplier++;
                }

                while (a[j] == -a[i]) {
                    count += matchMultiplier;
                    j--;
                }

                i++;
            }

            return count;
        }
    }

    private static class ThreeSumFaster {
        public static int count(int [] a) {
            int N = a.length;
            int count = 0;

            Arrays.sort(a);

            for (int i = 0; i < N - 2; i++) {
                int j = i+1;
                int k = a.length-1;

                while (j < k) {
                    int matchMultiplier = 1;

                    while (j+1 < a.length && j+1 != k && a[j+1] == a[j]) {
                        j++;
                        matchMultiplier++;
                    }

                    if (a[j] + a[k] == -a[i]) {
                        if (a[j] == a[k]) {
                            count += (int) ((Math.pow(matchMultiplier, 2) + matchMultiplier) / 2);
                            break;
                        } else {
                            while (j != k && a[j] + a[k] == -a[i]) {
                                count += matchMultiplier;
                                k--;
                            }
                        }
                    } else if (matchMultiplier > 1 && a[j] * 2 == -a[i]) {
                        count += (int) ((Math.pow(matchMultiplier-1, 2) + matchMultiplier-1) / 2);
                    }

                    j++;
                }
            }

            return count;
        }
    }
}
