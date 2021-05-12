package algsex.chapter1.section1;

import java.util.*;
import java.util.regex.*;
import edu.princeton.cs.algs4.*;

// 1.1.21 Write a program that reads in lines from standard input with each line containing
// a name and two integers and then uses printf() to print a table with a column of
// the names, the integers, and the result of dividing the first by the second, accurate to
// three decimal places. You could use a program like this to tabulate batting averages for
// baseball players or grades for students.
public class Exercise21 {
  private static ArrayList<Item> data = new ArrayList<>();
  private static Pattern linePattern = Pattern.compile("(\\w+)\\s+(\\d+)\\s+(\\d+)");

  public static void main(String[] args) {
    StdOut.print(
      "Enter data in the following format: <name> <n1> <n2>\n" +
      "Type \"EOF\" to finish accepting lines\n"+
      "====================================================\n"
    );

    while (!StdIn.isEmpty()) {
      String line = StdIn.readLine();

      if (line.equals("EOF")) {
        break;
      } else {
        consumeLine(line);
      }
    }

    ListIterator<Item> dataIterator = data.listIterator();

    while (dataIterator.hasNext()) {
      Item item = dataIterator.next();
      StdOut.printf("%s %d %d %.3f\n", item.name, item.n1, item.n2, (float) item.n1 / item.n2);
    }
  }

  public static void consumeLine(String line) {
    Matcher matcher = linePattern.matcher(line);

    if (!matcher.matches()) {
      StdOut.print("Invalid Line");
    }

    String name = matcher.group(1);
    int n1 = Integer.parseInt(matcher.group(2));
    int n2 = Integer.parseInt(matcher.group(3));

    data.add(new Item(name, n1, n2));
  }

  private static class Item {
    public String name;
    public int n1;
    public int n2;

    public Item(String name, int n1, int n2) {
      this.name = name;
      this.n1 = n1;
      this.n2 = n2;
    }

    public String toString() {
      return String.format("Item: %s %d %d", name, n1, n2);
    }
  }
}