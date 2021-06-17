package algsex.chapter1.section3;

import edu.princeton.cs.algs4.*;

// 1.3.10 Write a filter InfixToPostfix that converts an arithmetic expression from infix to postfix.
public class Exercise10 {
  public static void main(String[] args) throws Parser.SyntaxError {
    Parser parser = new Parser();

    parser.consume("(((1 + 2) * 3) + (4 * 5))");
    assert parser.toString().equals("1 2 + 3 * 4 5 * + ");

    parser = new Parser();
    parser.consume("((1 + 2) * (3 + 4))");
    assert parser.toString().equals("1 2 + 3 4 + * ");

    StdOut.println("Tests passed");
  }

  private static class Parser {
    private Stack<Task> stack = new Stack<>();
    private State state;
    private StringBuilder result = new StringBuilder();

    public void consume(String s) throws SyntaxError {
      for (int i = 0; i < s.length(); i++) consume(s.charAt(i));
    }

    public void consume(char c) throws SyntaxError {
      if (c == ' ') return;

      if (c == '(') {
        state = new ConsumingOpeningParenthesesState();
      } else if (c == ')') {
        state = new ConsumingClosingParenthesesState();
      } else if (Character.isDigit(c)) {
        state = new ConsumingDigitState();
      } else if (isOperator(c)) {
        state = new ConsumingOperatorState();
      } else throw new SyntaxError();

      state.consume(c);
    }

    public String toString() { return result.toString(); }

    private boolean isOperator(char c) {
      return c == '+' || c == '-' || c == '*' || c == '/';
    }

    private boolean isPendingTaskPresent(String name) {
      return !stack.isEmpty() && stack.peek().name == name;
    }

    private void pushTask(Task t) {
      stack.push(t);
    }

    private void completeLastTask() {
      stack.pop();
    }

    private void output(String s) {
      result.append(s);
      result.append(' ');
    }

    private void output(char c) {
      result.append(c);
      result.append(' ');
    }

    private interface State {
      public void consume(char c) throws SyntaxError;
    }

    private class ConsumingOpeningParenthesesState implements State {
      public void consume(char c) throws SyntaxError {
        if (isPendingTaskPresent("find-operand-1")) {
          completeLastTask();
          pushTask(new Task("find-operator"));
        }

        pushTask(new Task("close-parentheses"));
        pushTask(new Task("find-operand-1"));
      }
    }

    private class ConsumingClosingParenthesesState implements State {
      public void consume(char c) throws SyntaxError {
        if (isPendingTaskPresent("close-parentheses")) completeLastTask();
        else throw new SyntaxError();

        if (isPendingTaskPresent("find-operand-1")) {
          completeLastTask();
          pushTask(new Task("find-operator"));
        }

        if (isPendingTaskPresent("find-operand-2")) {
          output(stack.peek().operator);
          completeLastTask();
        }
      }
    }

    private class ConsumingDigitState implements State {
      public void consume(char c) throws SyntaxError {
        if (isPendingTaskPresent("find-operand-1")) {
          output(c);
          completeLastTask();
          pushTask(new Task("find-operator"));
        } else if (isPendingTaskPresent("find-operand-2")) {
          output(c);
          output(stack.peek().operator);
          completeLastTask();
        } else throw new SyntaxError();
      }
    }

    private class ConsumingOperatorState implements State {
      public void consume(char c) throws SyntaxError {
        if (isPendingTaskPresent("find-operator")) {
          completeLastTask();
          pushTask(new Task("find-operand-2", c));
        } else throw new SyntaxError();
      }
    }

    private class Task {
      public String name;
      public char operator;

      public Task(String name) {
        this.name = name;
      }

      public Task(String name, char operator) {
        this.name = name;
        this.operator = operator;
      }

      public String toString() {
        return name +  "-" + operator;
      }
    }

    private class SyntaxError extends Exception {}
  }
}