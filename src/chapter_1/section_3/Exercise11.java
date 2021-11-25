package algsex.chapter1.section3;

import edu.princeton.cs.algs4.*;

// 1.3.11 Write a program EvaluatePostfix that takes a postfix expression from standard
// input, evaluates it, and prints the value. (Piping the output of your program from
// the previous exercise to this program gives equivalent behavior to Evaluate.)
public class Exercise11 {
  public static void main(String[] args) {
    try {
      PostfixExpressionEvaluator evaluator = new PostfixExpressionEvaluator();
  
      assert evaluator.evaluate("120 80 + 2 5 * + 10 -") == 200;
      assert evaluator.evaluate("2 3 *") == 6;
      assert evaluator.evaluate("2 5 /") == 0;
    } catch (PostfixExpressionEvaluator.SyntaxError e) {
      assert false : e;
    }

    StdOut.println("Tests passed");
  }

  private static class PostfixExpressionEvaluator {
    private Stack<Integer> operands;
    private State state;
    private State previousState;

    public int evaluate(String s) throws SyntaxError {
      operands = new Stack<>();
      state = null;
      previousState = null;

      for (int i = 0; i < s.length(); i++)
        consume(s.charAt(i));

      return result();
    }

    private void consume(char c) throws SyntaxError {
      previousState = state;

      if (Character.isDigit(c)) {
        state = new ConsumingDigitState();
      } else if (isOperator(c)) {
        state = new ConsumingOperatorState();
      } else if (c == ' ') {
        state = new ConsumingSpaceState();
      }

      state.consume(c);
    }

    private int result() throws SyntaxError {
      if (operands.size() != 1) throw new SyntaxError();
      return operands.peek();
    }

    private boolean isOperator(char c) {
      return c == '+' || c == '-' || c == '*' || c == '/';
    }

    private interface State {
      void consume(char c) throws SyntaxError;
    }

    private class ConsumingDigitState implements State {
      public void consume(char c) {
        if (operands.isEmpty() || previousState instanceof ConsumingSpaceState)
          operands.push(0);

        int operand = operands.pop();
        operands.push(Integer.parseInt(Integer.toString(operand) + c));
      }
    }

    private class ConsumingOperatorState implements State {
      public void consume(char c) throws SyntaxError {
        if (operands.size() < 2) throw new SyntaxError();

        int rhs = operands.pop();
        int lhs = operands.pop();

        switch(c) {
          case '+':
            operands.push(lhs + rhs);
            break;
          case '-':
            operands.push(lhs - rhs);
            break;
          case '*':
            operands.push(lhs * rhs);
            break;
          case '/':
            operands.push(lhs / rhs);
            break;
        }
      }
    }

    private class ConsumingSpaceState implements State {
      public void consume(char c) throws SyntaxError {}
    }

    private class SyntaxError extends Exception {}
  }
}
