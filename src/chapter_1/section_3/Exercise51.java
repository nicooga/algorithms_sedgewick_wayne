package algsex.chapter1.section3;

import java.util.*;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class Exercise51 {
  public static void main(String[] args) {
    Evaluator evaluator = new Evaluator();

    try {
      assert evaluator.evaluate("1 + 2 * 3 - 2 * 3 * 2 + 3 * (2 + 2 - 1 * (2 - 1))") == 4;
      assert evaluator.evaluate("1 + 2 + 3 * 4 - 5 * (6 + 7)") == -50;
      assert evaluator.evaluate("10 + 2") == 12;
      assert evaluator.evaluate("(10 + 9 * (32 - 31)) / 2") == 9.5;
    } catch(Evaluator.SyntaxError e) {
      assert false : e;
    }

    StdOut.println("Tests passed");
  }

  private static class Evaluator {
    Stack<Double> values = new Stack<>();
    Stack<Character> operators = new Stack<>();
    State state;
    State previousState;

    public double evaluate(String expression) throws SyntaxError {
      for (int i = 0; i < expression.length(); i++) consume(expression.charAt(i));
      resolveAllOperations();
      assert values.size() == 1;
      assert operators.isEmpty();
      return values.pop();
    }

    private void consume(char c) throws SyntaxError {
      if (c == ' ') return;

      previousState = state;

      if (Character.isDigit(c)) state = new ConsumingDigitState();
      else if (isOperator(c)) state = new ConsumingOperatorState();
      else if (c == '(') state = new ConsumingOpeningParenthesesState();
      else if (c == ')') state = new ConsumingClosingParenthesesState();
      else throw new SyntaxError();

      state.consume(c);
    }

    private boolean isOperator(char c) {
      return c == '+' ||  c == '-' || c == '*' || c == '/';
    }

    private interface State {
      void consume(char c) throws SyntaxError;
    }

    private class ConsumingDigitState implements State {
      public void consume(char c) throws SyntaxError {
        int digit = Character.getNumericValue(c);
  
        if (previousState instanceof ConsumingDigitState) {
          double existingValue = values.pop();
          values.push(existingValue * 10 + digit);
        } else
          values.push((double) digit);
      }
    }

    private class ConsumingOperatorState implements State {
      public void consume(char c) throws SyntaxError {
        if (c == '-' || c == '+') resolveAllOperations();
        operators.push(c);
      }
    }

    private class ConsumingOpeningParenthesesState implements State {
      public void consume(char c) throws SyntaxError {
        operators.push(c);
      }
    }

    private class ConsumingClosingParenthesesState implements State {
      public void consume(char c) throws SyntaxError {
        resolveAllOperations();
        assert operators.peek() == '(';
        operators.pop();
      }
    }

    private void resolveAllOperations() throws SyntaxError {
      if (operators.isEmpty()) return;
      if (operators.peek() == '(') return;

      char operator = operators.pop();
      double rhs = values.pop();
      double lhs = values.pop();

      if (operator == '+') values.push(lhs + rhs);
      else if (operator == '-') values.push(lhs - rhs);
      else if (operator == '*') values.push(lhs * rhs);
      else if (operator == '/') values.push(lhs / rhs);
      else throw new SyntaxError();

      resolveAllOperations();
    }

    private class SyntaxError extends Exception {}
  }
}