package algsex.chapter1.section3;

import edu.princeton.cs.algs4.*;
import algsex.support.Stack;

// 1.3.2 Give the output printed by java Stack for the input
// it was - the best - of times - - - it was - the - -
public class Exercise2 {
    public static void main(String[] args) {
        Stack<String> s = new Stack<String>();
    
        consumeLine(s, "it");    // it
        consumeLine(s, "was");   // it was
        consumeLine(s, "-");     // it (was)
        consumeLine(s, "the");   // it the
        consumeLine(s, "best");  // it the best
        consumeLine(s, "-");     // it the (best)
        consumeLine(s, "of");    // it the of
        consumeLine(s, "times"); // it the of times
        consumeLine(s, "-");     // it the of (times)
        consumeLine(s, "-");     // it the (of)
        consumeLine(s, "-");     // it (the)
        consumeLine(s, "it");    // it it
        consumeLine(s, "was");   // it it was
        consumeLine(s, "-");     // it it (was)
        consumeLine(s, "the");   // it it the
        consumeLine(s, "-");     // it it (the)
        consumeLine(s, "-");     // it (it)
    }
  
    private static void consumeLine(Stack<String> stack, String line) {
        if (!line.equals("-")) stack.push(line);
        else if (!stack.isEmpty()) StdOut.println(stack.pop() + " ");
    }
}
