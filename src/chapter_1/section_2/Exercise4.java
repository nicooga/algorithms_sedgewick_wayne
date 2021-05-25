package algsex.chapter1.section2;

import edu.princeton.cs.algs4.*;

// 1.2.4 What does the following code fragment print?
public class Exercise4 {
  public static void main(String[] args) {
    String string1 = "hello";
    String string2 = string1;
    string1 = "world";
    StdOut.println(string1);
    StdOut.println(string2);
  }
}
