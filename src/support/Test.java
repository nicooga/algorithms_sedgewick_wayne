package algsex.support;

public class Test {
    // public static void assertEqual(int actual, int expected) {
    //     assertEquals((Integer) actual, (Integer) expected);
    // }

    public static void assertEqual(Object actual, Object expected) {
        assert actual == expected :
          String.format("Expected %s, but got %s", expected, actual);
    }

    public static void assertTrue(boolean bool) {
        assert bool : "Expected true but got false";
    }

    public static void assertFalse(boolean bool) {
        assert !bool : "Expected false but got true";
    }
}