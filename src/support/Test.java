package algsex.support;

import java.util.Arrays;
import java.lang.reflect.*;
import java.io.ByteArrayInputStream;
import edu.princeton.cs.algs4.StdIn;

public class Test {
    private static final String[] EMPTY_ARGS = new String[] {};

    public static void assertEqual(int actual, int expected) {
        assertEqual(actual, expected, String.format("Expected %s, but got %s", expected, actual));
    }

    public static void assertEqual(int actual, int expected, String errorMessage) {
        assert actual == expected : errorMessage;
    }

    public static void assertEqual(String actual, String expected) {
        assert actual.equals(expected) :
          String.format("\nExpected \"%s\",\nbut got: \"%s\"", expected, actual);
    }

    public static void assertEqual(double actual, double expected) {
        assert actual == expected :
          String.format("\nExpected %s,\nbut got: %s", expected, actual);
    }

    public static void assertEqual(Object actual, Object expected) {
        assert actual == expected :
          String.format("\nExpected \"%s\",\nbut got: \"%s\"", expected, actual);
    }

    public static void assertTrue(boolean bool) {
        assertTrue(bool, "Expected true but got false");
    }

    public static void assertTrue(boolean bool, String errorMessage) {
        assert bool : errorMessage;
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

    public static void assertLessThanOrEqual(double actual, double expected) {
        assertLessThanOrEqual(
            actual,
            expected,
            String.format(
                "expected %s to be less than or equal to %s, but it wasn't.",
                actual,
                expected
            )
        );
    }

    public static void assertLessThanOrEqual(int actual, int expected) {
        assertLessThanOrEqual(
            actual,
            expected,
            String.format(
                "expected %s to be less than or equal to %s, but it wasn't.",
                actual,
                expected
            )
        );
    }

    public static void assertLessThanOrEqual(int actual, int expected, String message) {
        assert actual <= expected : message;
    }

    public static void assertLessThanOrEqual(double actual, double expected, String message) {
        assert actual <= expected : message;
    }

    public static void simulateInput(String s) {
        System.setIn(new ByteArrayInputStream(s.getBytes()));

        // StdIn takes a reference to StdIn once.
        // We need to call the private method `StdIn.resync()` to force it to use the new input.
        try {
            Method method = StdIn.class.getDeclaredMethod("resync");
            method.setAccessible(true);
            method.invoke(null);
        } catch (Exception e) {
            assert false : "Error while hacking StdIn.resync() method:\n" + e.getMessage();
        }
    }

    public static String[] emptyArgs() {
        return EMPTY_ARGS;
    }
}