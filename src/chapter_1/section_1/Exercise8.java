package algsex.chapter1.section1;

import edu.princeton.cs.algs4.StdOut;

public class Exercise8 {
  public static void main(String[] args) {
    StdOut.print("a: ");
    a();
    StdOut.print("b: ");
    b();
    StdOut.print("c: ");
    c();
  }

  private static void a() {
    System.out.println('b'); 
    System.out.println("Its a valid or printable character");
  }

  private static void b() {
    System.out.println('b' + 'c'); 
    System.out.println("The int values for these chars where summed and the resulting char code did not belong to a printable character, so the int value was printed instead");
  }

  private static void c() {
    System.out.println((char) ('a' + 4)); 
    System.out.println("Again, the int values for these chars where summed, but this time the resulting code was printable");
  }
}