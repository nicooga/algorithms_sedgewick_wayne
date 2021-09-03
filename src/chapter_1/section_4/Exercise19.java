package algsex.chapter1.section4;

import java.util.*;
import edu.princeton.cs.algs4.*;
import algsex.support.Test;

// 1.4.19 Local minimum of a matrix. Given an N-by-N array a[] of N^2 distinct integers,
// design an algorithm that finds a local minimum: an entry a[i][j] that is strictly less
// than its neighbors. Internal entries have 4 neighbors; entries on an edge have 3 neigh-
// bors; entries on a corner have 2 neighbors. The running time of your program should
// be proportional to N in the worst case, which means that you cannot afford to examine
// all N^2 entries.
public class Exercise19 {
    public static void main(String[] args) {
        Test.assertEqual(
            LocalMinimum.find(
                new int[][] {
                    { 7,  8,  96, 9,  10 },
                    { 5,  6,  97, 11, 12 },
                    { 90, 91, 92, 94, 95 },
                    { 1,  2,  98, 13, 14 },
                    { 3,  4,  20, 15, 16 },
                }
            ),
            1
        );

        Test.assertEqual(
            LocalMinimum.find(
                new int[][] {
                    { 7,  8,  96, 9,  10 },
                    { 5,  6,  97, 11, 12 },
                    { 90, 91, 92, 94, 95 },
                    { 1,  2,  98, 13, 14 },
                    { 3,  22, 20, 15, 16 },
                }
            ),
            13
        );

        Test.assertEqual(
            LocalMinimum.find(
                new int[][] {
                    { 7,  8,  96, 9,  10 },
                    { 5,  6,  97, 11, 12 },
                    { 90, 91, 92, 20, 95 },
                    { 1,  2,  98, 13, 14 },
                    { 3,  22, 94, 15, 16 },
                }
            ),
            9
        );

        Test.assertEqual(
            LocalMinimum.find(
                new int[][] {
                    { 7,  8,  96, 9,  10 },
                    { 5,  6,  20, 11, 12 },
                    { 90, 91, 92, 97, 95 },
                    { 1,  2,  98, 13, 14 },
                    { 3,  22, 94, 15, 16 },
                }
            ),
            5
        );

        StdOut.println("Tests passed");
    }

    private static class LocalMinimum {
        public static int find(int[][] a) {
            int N = a.length;
            return find(a, 0, N-1, 0, N-1);
        }

        public static int find(int[][] a, int xLo, int xHi, int yLo, int yHi) {
            int N = a.length;
            Point min = minWithinCross(a, xLo, xHi, yLo, yHi);
            int value = a[min.y][min.x];
            int xMid = (xLo + xHi) / 2;
            int yMid = (yLo + yHi) / 2;

            if (min.x > xLo && value > a[min.y][min.x-1]) {
                int newYLo = yLo;
                int newYHi = yHi;
                if (min.y > yMid) newYLo = yMid+1;
                else newYHi = yMid-1;
                return find(a, xLo, min.x-1, newYLo, newYHi);
            }

            if (min.x <= xHi-1 && value > a[min.y][min.x+1]) {
                int newYLo = yLo;
                int newYHi = yHi;
                if (min.y > yMid) newYLo = yMid+1;
                else newYHi = yMid-1;
                return find(a, min.x+1, xHi, newYLo, newYHi);
            }
            
            if (min.y > yLo && value > a[min.y-1][min.x]) {
                int newXLo = xLo;
                int newXHi = xHi;
                if (min.x > xMid) newXLo = xMid+1;
                else newXHi = xMid-1;
                return find(a, newXLo, newXHi, yLo, min.y-1);
            }

            if (min.y <= yHi-1 && value > a[min.y+1][min.x]) {
                int newXLo = xLo;
                int newXHi = xHi;
                if (min.x > xMid) newXLo = xMid+1;
                else newXHi = xMid-1;
                return find(a, newXLo, newXHi, min.y+1, yHi);
            }

            return a[min.y][min.x];
        }

        private static Point minWithinCross(int[][] a, int xLo, int xHi, int yLo, int yHi) {
            int xMid = (xLo + xHi) / 2;
            int yMid = (yLo + yHi) / 2;
            int min = a[yMid][xMid];
            Point minPoint = new Point(xMid, yMid);

            for (int x = xLo; x <= xHi; x++)
                if (a[yMid][x] < min) {
                    min = a[yMid][x];
                    minPoint.x = x;
                    minPoint.y = yMid;
                }

            for (int y = yLo; y <= yHi; y++)
                if (a[y][xMid] < min) {
                    min = a[y][xMid];
                    minPoint.x = xMid;
                    minPoint.y = y;
                }

            return minPoint;
        }

        private static class Point {
            public int x;
            public int y;

            public Point(int x, int y) {
                this.x = x;
                this.y = y;
            }

            public String toString() {
                return String.format("Point(%s, %s)", x, y);
            }
        }
    }
}
