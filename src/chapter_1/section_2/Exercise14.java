package algsex.chapter1.section2;

import java.time.*;
import java.util.*;
import edu.princeton.cs.algs4.*;

public class Exercise14 {
  public static void main(String[] args) {
    LocalDateTime date1 = LocalDateTime.of(2021, 5, 27, 19, 35);
    LocalDateTime date2 = LocalDateTime.of(2021, 5, 27, 19, 36);

    Transaction t1 = new Transaction("Ricardo Montaner", date1, 22.5);
    Transaction t2 = new Transaction("Ricardo Montaner", date1, 22.5);
    Transaction t3 = new Transaction("Ricardo Montaner", date2, 223.5);

    assert t1.equals(t1);
    assert t2.equals(t2);
    assert t3.equals(t3);
    assert !t1.equals(t2);
    assert !t1.equals(t3);
    assert !t2.equals(t3);

    StdOut.println("All tests passed");
  }

  private static class Transaction implements Comparable<Transaction> {
    private UUID id;
    private String who;
    private LocalDateTime when;
    private double amount;

    public Transaction(String who, LocalDateTime when, double amount) {
      this.id = UUID.randomUUID();
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

    public boolean equals(Object x) {
      if (this == x) return true;
      if (x == null) return false;
      if (this.getClass() != x.getClass()) return false;
      Transaction that = (Transaction) x;
      if (this.id.equals(that.id)) return true;
      return false;
    }
  }
}
