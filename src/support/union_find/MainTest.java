package algsex.support.union_find;

public class MainTest {
    public static void main(String[] args) {
        runTest(new UnionFindViaQuickFind.Factory());
        runTest(new UnionFindViaQuickUnion.Factory());
        runTest(new UnionFindViaWeightedQuickUnion.Factory());
        runTest(new InstrumentedUnionFindViaWeightedQuickUnion.Factory());
    }

    private static void runTest(UnionFindFactory uff) {
        UnionFindTest.runTest(uff);
    }
}