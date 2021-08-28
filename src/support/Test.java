package algsex.support;

import java.util.Arrays;

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

    public static void assertArrayEquals(int[] actual, int[] expected) {
        assert Arrays.equals(actual, expected) :
            String.format(
                "Expected array to equal %s, but got %s",
                Arrays.toString(expected),
                Arrays.toString(actual)
            );
    }
}