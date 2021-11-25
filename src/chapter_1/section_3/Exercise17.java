package algsex.chapter1.section3;

import java.util.Arrays;
import java.util.Iterator;
import edu.princeton.cs.algs4.*;
import algsex.support.Test;

// 1.3.17 Do Exercise 1.3.16 for Transaction.
public class Exercise17 {
    public static void main(String[] args) {
        Transaction[] transactions = readTransactions();
        StdOut.println(Arrays.toString(transactions));
    }

    public static void test() {
        Test.simulateInput(
            "Turing 5/22/1939 11.39\n" +
            "Robert 5/23/1912 23.5\n"
        );

        Transaction[] transactions = readTransactions();

        Test.assertTrue(
            transactions[0].equals(
                new Transaction(
                    "Turing",
                    new Date(5, 22, 1939),
                    11.39
                )
            )
        );

        Test.assertTrue(
            transactions[1].equals(
                new Transaction(
                    "Robert",
                    new Date(5, 23, 1912),
                    23.5
                )
            )
        );
    }
  
    private static Transaction[] readTransactions() {
        Queue<Transaction> queue = new Queue<Transaction>();
  
        while(!StdIn.isEmpty()) {
            Transaction t = parseTransaction(StdIn.readLine());
            queue.enqueue(t);
        }
  
        Transaction[] array = new Transaction[queue.size()];
  
        Iterator<Transaction> iterator = queue.iterator();
  
        for(int i = 0; i < array.length; i++)
            array[i] = queue.dequeue();
  
        return array;
    }
  
    private static Transaction parseTransaction(String str) {
      String[] fields = str.split(" ");
  
      String who = fields[0];
      Date when = parseDate(fields[1]);
      Double amount = Double.parseDouble(fields[2]);
  
      return new Transaction(who, when, amount);
    }
  
    private static Date parseDate(String str) {
      String[] fields = str.split("/");
  
      int month = Integer.parseInt(fields[0]);
      int day = Integer.parseInt(fields[1]);
      int year = Integer.parseInt(fields[2]);
  
      return new Date(month, day, year);
    }
}
