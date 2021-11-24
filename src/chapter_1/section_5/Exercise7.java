package algsex.chapter1.section5;

import java.util.*;
import edu.princeton.cs.algs4.*;
import algsex.support.union_find.*;

// 1.5.7 Develop classes QuickUnionUF and QuickFindUF that implement quick-union
// and quick-find, respectively.
public class Exercise7 {
    private static final int SITES = 8;

    public static void main(String[] args) {
        UnionFindTest.runTest(new UnionFindViaQuickFind(SITES));
        UnionFindTest.runTest(new UnionFindViaQuickUnion(SITES));
        StdOut.println("Tests passed");
    }
}
