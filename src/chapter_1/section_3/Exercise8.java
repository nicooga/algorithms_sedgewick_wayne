package algsex.chapter1.section3;

import java.util.*;
import edu.princeton.cs.algs4.*;

// 1.3.8 Give the contents and size of the array for ResizingArrayStackOfStrings with
// the input
// it was - the best - of times - - - it was - the - -
public class Exercise8 {
  public static void main(String[] args) {
    ResizingArrayStack<String> stack = new ResizingArrayStack<>();

    stack.push("it");    // it
    stack.push("was");   // it was
    stack.pop();         // it
    stack.push("the");   // it the
    stack.push("best");  // it the best
    stack.pop();         // it the
    stack.push("of");    // it the of
    stack.push("times"); // it the of times
    stack.pop();         // it the of
    stack.pop();         // it the
    stack.pop();         // it
    stack.push("it");    // it it
    stack.push("was");   // it was
    stack.pop();         // it
    stack.push("the");   // it the
    stack.pop();         // it
    stack.pop();         // *empty*

    StdOut.println(stack.size());
  }
}
