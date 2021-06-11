package algsex.chapter1.section3;

import edu.princeton.cs.algs4.*;

public class Exercise9 {
  public static void main(String[] args) throws MathExpressionParser.SyntaxError {
    MathExpressionParser parser = new MathExpressionParser();

    parser.consume("1 + 2 ) * 3 - 4 ) * 5 - 6 ) ) )");
    assert parser.toString().equals("( ( 1 + 2 ) * ( ( 3 - 4 ) * ( 5 - 6 ) ) )");

    StdOut.println("Tests passed");
  }

  private static class MathExpressionParser {
    private Stack<Character> unresolved = new Stack<>();
    private StringBuilder builder = new StringBuilder();
    private State state;

    public String toString() { return builder.toString(); }

    public void consume(String str) throws SyntaxError {
      for (int i = str.length() - 1; i >= 0; i--)
        consume(str.charAt(i));
    }

    private void consume(char c) throws SyntaxError {
      if (c == ' ') {
        output(' ');
        return;
      }

      if (c == ')') {
        state = new ConsumingClosingParenthesesState();
      } else if (Character.isDigit(c)) {
        state = new ConsumingDigitState();
      } else if (isOperator(c)) {
        state = new ConsumingOperatorState();
      } else throw new SyntaxError();

      state.consume(c);
    }

    private boolean isOperator(char c) { return c == '-' || c == '+' || c == '*' || c == '/'; }
    private void output(char... chars) { for (char c : chars) builder.insert(0, c); }

    private interface State {
      void consume(char c) throws SyntaxError;
    }

    private class ConsumingClosingParenthesesState implements State {
      public void consume(char c) throws SyntaxError {
        if (!(unresolved.isEmpty() || unresolved.peek() == ')' || unresolved.peek() == 'o'))
          throw new SyntaxError();

        unresolved.push(c);
        output(c);
      }
    }

    private class ConsumingDigitState implements State {
      public void consume(char c) throws SyntaxError {
        if (unresolved.peek() == ')') {
          unresolved.push('o');
          output(c);
        } else if (unresolved.peek() == 'o') {
          output(c);

          while(!unresolved.isEmpty() && unresolved.peek() == 'o') {
            unresolved.pop();

            if (unresolved.peek() == ')') unresolved.pop();
            else throw new SyntaxError();

            output(' ', '(');
          }
        } else throw new SyntaxError();
      }
    }

    private class ConsumingOperatorState implements State {
      public void consume(char c) throws SyntaxError {
        if (!(unresolved.peek() == 'o' || unresolved.peek() == ')'))
          throw new SyntaxError();

        if (unresolved.peek() == ')')
          unresolved.push('o');

        output(c);
      }
    }

    private class SyntaxError extends Exception {}
  }
}
