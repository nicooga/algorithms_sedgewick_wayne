package algsex.chapter2.section1;

import algsex.support.sorting.*;

// 2.1.1 Show, in the style of the example trace with Algorithm 2.1, how selection sort
// sorts the array E A S Y Q U E S T I O N.
public class Exercise1 {
    public static void main(String[] args) {
        Comparable[] a = toCharArray("EASYQUESTION");
        InstrumentationHelper instrumentationHelper = new CommandLineInstrumentationHelper();
        Sort sort = new SelectionSort(instrumentationHelper);
        sort.sort(a);
    }

    private static Character[] toCharArray(String s) {
        Character[] a = new Character[s.length()];

        for (int i = 0; i < s.length(); i++)
            a[i] = s.charAt(i);

        return a;
    }
}
