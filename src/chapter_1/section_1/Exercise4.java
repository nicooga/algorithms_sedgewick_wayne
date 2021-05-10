package algsex.chapter1.section1;

public class Exercise4 {
  public static void main(String[] _args) {
    int a = 0;
    int b = 0;
    int c = 0;

    // a.  if (a > b) then c = 0; 
    // Java uses brackets not if/then.
    // Fixed:
    if (a > b) { c = 0; }

    // b.  if a > b { c = 0; } 
    // Need parentheses around conditions
    // Fixed:
    if (a > b) { c = 0; } 

    // c.  if (a > b) c = 0; 
    // Works ok, but c will not be recognized as initialized by the compiler.
    // We can awork around that by assigning a default value to c;
    if (a > b) c = 0; 

    // d.  if (a > b) c = 0 else b = 0; 
    // Was wrong, needed to add the first semi colon
    //     this one |
    if (a > b) c = 0; else b = 0; 
  }
}