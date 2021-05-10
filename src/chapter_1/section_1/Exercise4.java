package algsex.chapter1.section1;

public class Exercise4 {
  public static void main(String[] args) {
    double double1 = Double.parseDouble(args[0]);
    double double2 = Double.parseDouble(args[1]);

    if (double1 == double2) {
      System.out.println("true");
    } else {
      System.out.println("false");
    }
  }
}