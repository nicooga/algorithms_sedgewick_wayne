package algsex.chapter1.section1;

import edu.princeton.cs.algs4.StdOut;

public class Exercise7 {
  public static void main(String[] args) {
    StdOut.println("a: ");
    a();
    StdOut.println("b: ");
    b();
    StdOut.println("c: ");
    c();
  }

  private static void a() {
    double t = 9.0; 
    while (Math.abs(t - 9.0/t) > .001) 
    t = (9.0/t + t) / 2.0; 
    StdOut.printf("%.5f\n", t); 
  }

  private static void b() {
    int sum = 0; 
    for (int i = 1; i < 1000; i++) 
    for (int j = 0; j < i; j++) 
    sum++; 
    StdOut.println(sum); 
  }

  private static void c() {
    int sum = 0; // <-- Had to add this so it compiles
    for (int i = 1; i < 1000; i *= 2) 
    for (int j = 0; j < 1000; j++) 
    sum++; 
    StdOut.println(sum); 
  }
}