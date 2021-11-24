package algsex.chapter1.section2;

import algsex.support.Test;

public class MainTest {
    public static void main(String[] args) {
        Exercise1.main(new String[] { "10" });

        Test.simulateInput(
            "10.4 25.0\n" +
            "2.4 30.0\n" +
            "2.3 100.0\n"
        );

        Exercise2.main(new String[] { "3" });

        Exercise4.main(Test.emptyArgs());
        Exercise5.main(Test.emptyArgs());
        Exercise6.main(new String[] { "asdf", "sdfa" });
        Exercise6.main(new String[] { "asdf", "qwer" });
        Exercise7.main(new String[] { "asdf" });

        Test.simulateInput("1 2 3 4 8\n");
        Exercise9.main(new String[] { "4" });

        Exercise11.main(Test.emptyArgs());
        Exercise12.main(Test.emptyArgs());
        Exercise13.main(Test.emptyArgs());
        Exercise14.main(Test.emptyArgs());

        Test.simulateInput("1 2 3");
        Exercise15.main(Test.emptyArgs());

        Exercise16.main(Test.emptyArgs());
        Exercise17.main(Test.emptyArgs());
        Exercise18.main(Test.emptyArgs());
        Exercise19.main(Test.emptyArgs());
    }
}