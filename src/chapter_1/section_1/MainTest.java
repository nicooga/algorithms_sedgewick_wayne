package algsex.chapter1.section1;

import algsex.support.Test;

public class MainTest {
    public static void main(String[] args) {
        algsex.support.doubling_ratio_testing.DoublingRatioTestV2Test.main(Test.emptyArgs());

        Exercise1.main(Test.emptyArgs());
        Exercise2.main(Test.emptyArgs());
        Exercise3.main(new String[] { "1", "2", "3" });
        Exercise3.main(new String[] { "3", "3", "3" });
        Exercise4.main(Test.emptyArgs());
        Exercise5.main(new String[] { "0.1", "0.1" });
        Exercise5.main(new String[] { "1", "2" });
        Exercise6.main(Test.emptyArgs());
        Exercise7.main(Test.emptyArgs());
        Exercise8.main(Test.emptyArgs());
        Exercise9.main(new String[] { "8" });
        Exercise11.main(Test.emptyArgs());
        Exercise12.main(Test.emptyArgs());
        Exercise13.main(Test.emptyArgs());
        Exercise14.main(Test.emptyArgs());
        Exercise15.main(Test.emptyArgs());
        Exercise16.main(Test.emptyArgs());
        Exercise18.main(Test.emptyArgs());
        Exercise19.main(Test.emptyArgs());
        Exercise20.main(Test.emptyArgs());

        Test.simulateInput(
            "bob 10 12\n" +
            "greg 13 16\n" +
            "richard 13 16\n" +
            "carlos 12 23\n"
        );
        Exercise21.main(Test.emptyArgs());

        Exercise22.main(Test.emptyArgs());

        Test.simulateInput("1 2 3 4 5");
        Exercise23.main(new String[] { "+", "1" ,"2", "3", "4", "5" });

        Exercise24.main(new String[] { "1", "2" });
        Exercise27.main(new String[] { "10", "10", "12" });
        Exercise28.main(new String[] { "10", "10", "12" });
        Exercise29.main(new String[] { "1", "2", "3" });
        Exercise30.main(new String[] { "4" });

        Exercise33.main(Test.emptyArgs());
        Exercise36.main(new String[] { "10", "5" });

        Test.simulateInput("1 2 3");
        Exercise38.main(new String[] { "3" });

        Exercise39.main(new String[] { "2" });
    }
}