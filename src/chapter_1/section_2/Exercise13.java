package algsex.chapter1.section2;

import java.time.*;
import edu.princeton.cs.algs4.*;

public class Exercise13 {
  public static void main(String[] args) {
    LocalDateTime dateTime = LocalDateTime.of(2021, 5, 27, 19, 35);
    Transaction t = new Transaction("Ricardo Montaner", dateTime, 22.5);

    assert t.who().equals("Ricardo Montaner");
    assert t.when().equals(dateTime);
    assert t.amount() == 22.5;

    StdOut.println(t);
  }

  private static class Transaction implements Comparable<Transaction> {
    private String who;
    private LocalDateTime when;
    private double amount;

    public Transaction(String who, LocalDateTime when, double amount) {
      this.who = who;
      this.when = when;
      this.amount = amount;
    }

    public String who() { return who; }
    public LocalDateTime when() { return when; }
    public double amount() { return amount; }

    public String toString() {
      return String.format("%s spent $%s on %s", who, amount, when);
    }

    public int compareTo(Transaction t)  {
      if (this == t) return 0;
      if (amount > t.amount()) return 1;
      if (amount < t.amount()) return -1;
      return 0;
    }
  }
}
