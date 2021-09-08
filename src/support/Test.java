package algsex.support;

import java.util.Arrays;

public class Test {
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

    public static void assertLessThanOrEqual(int actual, int expected) {
        assert actual <= expected :
            String.format(
                "Expected %s to be less than or equal to %s, but it wasn't.",
                actual,
                expected
            );
    }
}