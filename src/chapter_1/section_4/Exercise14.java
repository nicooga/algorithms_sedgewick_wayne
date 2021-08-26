package algsex.chapter1.section4;

import java.util.*;
import edu.princeton.cs.algs4.*;
import algsex.support.Test;

// 1.4.14 4-sum. Develop an algorithm for the 4-sum problem.
public class Exercise14 {
  public static void main(String[] args) {
    // Test.assertEqual(
    //   FourSum.count(new int[] { 1, 2, 3, -6 }),
    //   1
    // );

    // Test.assertEqual(
    //   FourSum.count(new int[] { 1, 2, 3, -6, 4, -10, -8 }),
    //   2
    // );

    // Test.assertEqual(
    //   FourSumV2.count(new int[] { 1, 2, 3, -6 }),
    //   1
    // );

    Test.assertEqual(
      FourSumV2.count(new int[] { 1, 2, 3, -6, 4, -10, -8 }),
      2
    );

    StdOut.println("Tests passed");
  }

  // Brute force, cubic complexity algorithm
  private static class FourSum {
    public static int count(int[] a) {
      int N = a.length;
      int count = 0;

      Arrays.sort(a);

      for (int i = 0; i < N; i++)
        for (int j = i+1; j < N; j++)
          for (int k = j+1; k < N; k++) {
            int lastNumber = -(a[i] + a[j] + a[k]);
            int lastNumberIndex = rank(lastNumber, a, k+1, N-1);
            assert a[i] + a[j] + a[k] + lastNumber == 0;
            if (lastNumberIndex != -1) count++;
          }

      return count;
    }

    private static int rank(int key, int[] a, int lo, int hi) {
      if (lo > hi) return -1;

      int mid = (lo + hi) / 2;

      if (a[mid] > key) return rank(key, a, lo, mid-1);
      else if (a[mid] < key) return rank(key, a, mid+1, hi);
      else return mid;
    }
  }

  // A more performant but much more complex algorithm with quadratic complexity.
  // This is achieved by pre-calculating sums for all pairs, then walking over all pairs again
  // and counting the number of pairs whose sums balance the current pair and are ranked higher (its two indexes are higher than the current pair's).
  private static class FourSumV2 {
    public static int count(int[] a) {
      int N = a.length;
      Pair[] pairs = buildPairs(a);
      int count = 0;

      for (int i = 0; i < N; i++) {
        for (int j = i+1; j < N; j++) {
          Pair currentPair = new Pair(a, i, j);
          int partialCount = countOppositePairsFor(currentPair, pairs);
          count += partialCount;
        }
      }

      return count;
    }

    private static Pair[] buildPairs(int[] a) {
      int N = a.length;
      int numberOfPairs = (int) (Math.pow(N, 2)/2 - N/2.0);
      Pair[] pairs = new Pair[numberOfPairs];

      int index = 0;

      for (int i = 0; i < N; i++)
        for (int j = i+1; j < N; j++) {
          // frequency of execution: (N^2 - N)/2
          pairs[index] = new Pair(a, i, j);
          index++;
        }

      assert pairs[pairs.length-1] != null;

      Arrays.sort(pairs);

      return pairs;
    }

    private static int countOppositePairsFor(Pair p, Pair[] pairs) {
      int matchingSumEnd = findPairWithSumRight(-p.sum(), pairs);

      if (matchingSumEnd == -1) return 0;
      if (pairs[matchingSumEnd].maxIndex() <= p.maxIndex()) return 0;
      if (pairs[matchingSumEnd].minIndex() <= p.maxIndex()) return 0;

      int matchingSumStart = findPairWithSumLeft(-p.sum(), pairs, 0, matchingSumEnd);

      if (pairs[matchingSumStart].minIndex() > p.maxIndex()) {
        return matchingSumEnd - matchingSumStart + 1;
      }

      int matchingMinIndexStart =
        findFirstPairWithMinIndexGreaterThan(
          p.maxIndex(),
          pairs,
          matchingSumStart+1,
          matchingSumEnd
        );

      assert matchingMinIndexStart != -1;

      return matchingSumEnd - matchingMinIndexStart + 1;
    }

    private static int findPairWithSumRight(int sum, Pair[] pairs) {
      return findPairWithSumRight(sum, pairs, 0, pairs.length-1);
    }

    private static int findPairWithSumRight(int sum, Pair[] pairs, int lo, int hi) {
      if (lo > hi) return -1;

      int mid = (lo + hi) / 2;

      if (pairs[mid].sum() > sum) return findPairWithSumRight(sum, pairs, lo, mid-1);
      else if (pairs[mid].sum() < sum) return findPairWithSumRight(sum, pairs, mid+1, hi);
      else {
        int higherMatch = findPairWithSumRight(sum, pairs, mid+1, hi);
        if (higherMatch != -1) return higherMatch;
        return mid;
      }
    }

    private static int findPairWithSumLeft(int sum, Pair[] pairs, int lo) {
      return findPairWithSumLeft(sum, pairs, lo, pairs.length-1);
    }

    private static int findPairWithSumLeft(int sum, Pair[] pairs, int lo, int hi) {
      if (lo > hi) return -1;

      int mid = (lo + hi) / 2;

      if (pairs[mid].sum() > sum) return findPairWithSumLeft(sum, pairs, lo, mid-1);
      else if (pairs[mid].sum() < sum) return findPairWithSumLeft(sum, pairs, mid+1, hi);
      else {
        int lowerMatch = findPairWithSumLeft(sum, pairs, lo, mid-1);
        if (lowerMatch != -1) return lowerMatch;
        return mid;
      }
    }

    private static int findFirstPairWithMinIndexGreaterThan(int index, Pair[] pairs, int lo, int hi) {
      if (lo > hi) return -1;

      if (lo == hi) {
        if (
          pairs[lo].minIndex() <= index &&
          lo+1 < pairs.length &&
          pairs[lo+1].sum() == pairs[lo].sum()
        )
          return lo+1;
        else
          return lo;
      }

      int mid = (lo + hi) / 2;

      if (pairs[mid].minIndex() > index)
        return findFirstPairWithMinIndexGreaterThan(index, pairs, lo, mid-1);
      else if (pairs[mid].minIndex() < index)
        return findFirstPairWithMinIndexGreaterThan(index, pairs, mid+1, hi);
      else if (mid+1 < pairs.length && pairs[mid+1].sum() == pairs[mid].sum())
        return mid+1;
      else
        return -1;
    }

    private static class Pair implements Comparable {
      private int lIndex;
      private int lValue;
      private int rIndex;
      private int rValue;

      private int _sum = -1;
      private int _minIndex = -1;
      private int _maxIndex = -1;

      public Pair(int[] a, int lIndex, int rIndex) {
        this.lIndex = lIndex;
        this.lValue = a[lIndex];
        this.rIndex = rIndex;
        this.rValue = a[rIndex];
      }

      public int sum() {
        if (this._sum == -1) this._sum = lValue + rValue;

        return this._sum;
      }

      public int minIndex() {
        if (this._minIndex == -1)
          this._minIndex = Math.min(lIndex, rIndex);

        return this._minIndex;
      }

      public int maxIndex() {
        if (this._maxIndex == -1)
          this._maxIndex = Math.max(lIndex, rIndex);

        return this._maxIndex;
      }

      public int compareTo(Object object) {
        if (!(object instanceof Pair))
          throw new RuntimeException("Can't compare Pair with something different that another Pair");

        Pair anotherSum = (Pair) object;

        if (this == anotherSum) return 0;
        else if (this.sum() > anotherSum.sum()) return 1;
        else if (this.sum() < anotherSum.sum()) return -1;
        else if (this.minIndex() > anotherSum.minIndex()) return 1;
        else if (this.minIndex() < anotherSum.minIndex()) return -1;
        else return 0;
      }

      public String toString()  {
        return String.format(
          "Pair(sum: %s, minIndex: %s, lIndex: %s, rIndex: %s)",
          sum(), minIndex(), lIndex, rIndex
        );
      }
    }
  }
}
