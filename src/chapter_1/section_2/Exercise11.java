package algsex.chapter1.section2;

import java.util.*;
import java.lang.*;
import edu.princeton.cs.algs4.*;

public class Exercise11 {
  public static void main(String[] args) {
    if (args.length > 0) {
      int year = Integer.parseInt(args[0]);
      int month = Integer.parseInt(args[1]);
      int day = Integer.parseInt(args[2]);

      try {
        SmartDate date = new SmartDate(year, month, day);
        StdOut.println(date);
      } catch (Exception e) {
        StdOut.println(e);
      }
    }

    try {
      SmartDate date = new SmartDate(2018, 13, 2);
      assert false;
    } catch(SmartDate.InvalidDateError e) {
      assert e.getMessage().equals("Month 13 is invalid. Valid months are integers 1 to 12");
    }

    try {
      SmartDate date = new SmartDate(2018, 0, 2);
      assert false;
    } catch(SmartDate.InvalidDateError e) {
      assert e.getMessage().equals("Month 0 is invalid. Valid months are integers 1 to 12");
    }

    try {
      SmartDate date1 = new SmartDate(2020, 1, 32);
      assert false;
    } catch(SmartDate.InvalidDateError e) {
      assert e.getMessage().equals("Date 2020/1/32 is invalid, month 1 has a maximum of 31 days.");
    }

    try {
      SmartDate date = new SmartDate(2021, 2, 29);
      assert false;
    } catch(SmartDate.InvalidDateError e) {
      assert e.getMessage().equals("Date 2021/2/29 is invalid, month 2 has a maximum of 28 days.");
    }

    try {
      SmartDate date = new SmartDate(2020, 2, 30);
      assert false;
    } catch(SmartDate.InvalidDateError e) {
      assert e.getMessage().equals("Date 2020/2/30 is invalid, month 2 has a maximum of 29 days.");
    }

    try {
      SmartDate date = new SmartDate(2021, 3, 32);
      assert false;
    } catch(SmartDate.InvalidDateError e) {
      assert e.getMessage().equals("Date 2021/3/32 is invalid, month 3 has a maximum of 31 days.");
    }

    try {
      SmartDate date = new SmartDate(2021, 4, 31);
      assert false;
    } catch(SmartDate.InvalidDateError e) {
      assert e.getMessage().equals("Date 2021/4/31 is invalid, month 4 has a maximum of 30 days.");
    }

    try {
      SmartDate date = new SmartDate(2021, 5, 32);
      assert false;
    } catch(SmartDate.InvalidDateError e) {
      assert e.getMessage().equals("Date 2021/5/32 is invalid, month 5 has a maximum of 31 days.");
    }

    try {
      SmartDate date = new SmartDate(2021, 6, 31);
      assert false;
    } catch(SmartDate.InvalidDateError e) {
      assert e.getMessage().equals("Date 2021/6/31 is invalid, month 6 has a maximum of 30 days.");
    }

    try {
      SmartDate date = new SmartDate(2021, 7, 32);
      assert false;
    } catch(SmartDate.InvalidDateError e) {
      assert e.getMessage().equals("Date 2021/7/32 is invalid, month 7 has a maximum of 31 days.");
    }

    try {
      SmartDate date = new SmartDate(2021, 8, 32);
      assert false;
    } catch(SmartDate.InvalidDateError e) {
      assert e.getMessage().equals("Date 2021/8/32 is invalid, month 8 has a maximum of 31 days.");
    }

    try {
      SmartDate date = new SmartDate(2021, 9, 31);
      assert false;
    } catch(SmartDate.InvalidDateError e) {
      assert e.getMessage().equals("Date 2021/9/31 is invalid, month 9 has a maximum of 30 days.");
    }

    try {
      SmartDate date = new SmartDate(2021, 10, 32);
      assert false;
    } catch(SmartDate.InvalidDateError e) {
      assert e.getMessage().equals("Date 2021/10/32 is invalid, month 10 has a maximum of 31 days.");
    }

    try {
      SmartDate date = new SmartDate(2021, 11, 31);
      assert false;
    } catch(SmartDate.InvalidDateError e) {
      assert e.getMessage().equals("Date 2021/11/31 is invalid, month 11 has a maximum of 30 days.");
    }

    try {
      SmartDate date = new SmartDate(2021, 12, 32);
      assert false;
    } catch(SmartDate.InvalidDateError e) {
      assert e.getMessage().equals("Date 2021/12/32 is invalid, month 12 has a maximum of 31 days.");
    }

    try {
      SmartDate date = new SmartDate(2021, 12, 31);

      assert date.year() == 2021;
      assert date.month() == 12;
      assert date.day() == 31;
    } catch(SmartDate.InvalidDateError _e) {
      assert false;
    }

    StdOut.println("All tests passed");
  }

  private static class SmartDate {
    private int year;
    private int month;
    private int day;

    public SmartDate(int year, int month, int day) throws InvalidDateError {
      this.year = year;
      this.month = month;
      this.day = day;

      validate();
    }

    public int year() { return year; }
    public int month() { return month; }
    public int day() { return day; }

    public String toString() {
      return String.format("%s/%s/%s", year, month, day);
    }

    private void validate() throws InvalidDateError {
      validateMonth();
      validateDay();
    }

    private void validateMonth() throws InvalidDateError {
      if (month >= 1 && month <= 12) return;

      String errorMessage = String.format(
        "Month %s is invalid. Valid months are integers 1 to 12",
        month
      );

      throw new InvalidDateError(errorMessage);
    }

    private void validateDay() throws InvalidDateError {
      int maxDays = maxDays();

      if (day <= maxDays) return;

      String errorMessage = String.format(
        "Date %s/%s/%s is invalid, month %s has a maximum of %s days.",
        year, month, day, month, maxDays
      );

      throw new InvalidDateError(errorMessage);
    }

    private int maxDays() {
      switch(month) {
        case 1, 3, 5, 7, 8, 10, 12:
          return 31;
        case 2:
          return isLeapYear() ? 29 : 28;
        case 4, 6, 9, 11:
          return 30;
        default:
          assert false;
          throw new RuntimeException("Unexpected month number");
      }
    }

    private boolean isLeapYear() {
      return (
        isDivisibleBy(year, 100) ?
        isDivisibleBy(year, 400) :
        isDivisibleBy(year, 4)
      );
    }

    private boolean isDivisibleBy(int dividend, int divisor) {
      return dividend % divisor == 0;
    }

    public static class InvalidDateError extends Exception {
      public InvalidDateError(String errorMessage) {
        super(errorMessage);
      }
    }
  }
}
