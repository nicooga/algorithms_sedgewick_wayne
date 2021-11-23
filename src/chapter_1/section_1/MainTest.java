package algsex.chapter1.section1;

import java.io.*;

public class MainTest {
    private static final String[] EMPTY_ARGS = new String[] {};

    public static void main(String[] args) {
        algsex.support.doubling_ratio_testing.DoublingRatioTestV2Test.main(EMPTY_ARGS);

        Exercise1.main(EMPTY_ARGS);
        Exercise2.main(EMPTY_ARGS);
        Exercise3.main(new String[] { "1", "2", "3" });
        Exercise3.main(new String[] { "3", "3", "3" });
        Exercise4.main(EMPTY_ARGS);
        Exercise5.main(new String[] { "0.1", "0.1" });
        Exercise5.main(new String[] { "1", "2" });
        Exercise6.main(EMPTY_ARGS);
        Exercise7.main(EMPTY_ARGS);
        Exercise8.main(EMPTY_ARGS);
        Exercise9.main(new String[] { "8" });
        Exercise11.main(EMPTY_ARGS);
        Exercise12.main(EMPTY_ARGS);
        Exercise13.main(EMPTY_ARGS);
        Exercise14.main(EMPTY_ARGS);
        Exercise15.main(EMPTY_ARGS);
        Exercise16.main(EMPTY_ARGS);
        Exercise18.main(EMPTY_ARGS);
        Exercise19.main(EMPTY_ARGS);
        Exercise20.main(EMPTY_ARGS);

        simulateInput(
            "bob 10 12\n" +
            "greg 13 16\n" +
            "richard 13 16\n" +
            "carlos 12 23\n"
        );
        Exercise21.main(EMPTY_ARGS);

        Exercise22.main(EMPTY_ARGS);

        simulateInput("1 2 3 4 5");
        Exercise23.main(new String[] { "+", "1" ,"2", "3", "4", "5" });

        Exercise24.main(new String[] { "1", "2" });
        Exercise27.main(new String[] { "10", "10", "12" });
        Exercise28.main(new String[] { "10", "10", "12" });
        Exercise29.main(new String[] { "1", "2", "3" });
        Exercise30.main(new String[] { "4" });

        Exercise33.main(EMPTY_ARGS);
        Exercise36.main(new String[] { "10", "5" });

        simulateInput("1 2 3");
        Exercise38.main(new String[] { "3" });

        Exercise39.main(new String[] { "2" });
    }

    private static void simulateInput(String s) {
        System.setIn(new ByteArrayInputStream(s.getBytes()));
    }
}