package algsex.chapter1.section2;

import java.time.*;
import edu.princeton.cs.algs4.*;

public class Exercise19 {
  public static void main(String[] args) {
    SmartDate date = new SmartDate("2020/02/02");

    assert date.year == 2020;
    assert date.month == 2;
    assert date.day == 2;

    Transaction t = new Transaction("Turing 2020/5/22 11.99");

    assert t.who.equals("Turing");
    assert t.when.toString().equals("2020/5/22");
    assert t.amount == 11.99;

    StdOut.println("Tests passed");
  }

  private static class Transaction {
    public String who;
    public SmartDate when;
    public double amount;

    public Transaction(String str) {
      String[] fields = str.split(" ");

      this.who = fields[0];
      this.when = new SmartDate(fields[1]);
      this.amount = Double.parseDouble(fields[2]);
    }

    public String toString() {
      return String.format("%s spent $%s on %s", who, amount, when);
    }
  }

  private static class SmartDate {
    public int year;
    public int month;
    public int day;

    public SmartDate(String str) {
      String[] fields = str.split("/");

      int year = Integer.parseInt(fields[0]);
      int month = Integer.parseInt(fields[1]);
      int day = Integer.parseInt(fields[2]);

      this.year = year;
      this.month = month;
      this.day = day;
    }

    public String toString() {
      return String.format("%s/%s/%s", year, month, day);
    }
  }
}