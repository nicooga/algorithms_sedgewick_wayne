package algsex.chapter1.section3;

import edu.princeton.cs.algs4.*;

// 1.3.4 Write a stack client Parentheses that reads in a text stream from standard input
// and uses a stack to determine whether its parentheses are properly balanced. For example,
// your program should print true for [()]{}{[()()]()} and false for [(]).
public class Exercise4 {
  private static Stack<Character> stack = new Stack<>();

  public static void main(String[] args) {
    try {
      while(!StdIn.isEmpty())
        processChar(StdIn.readChar());
    } catch(UnbalancedParanthesesException _e) {
      fail();
      return;
    }

    if (stack.isEmpty())
      succeed();
    else
      fail();
  }

  private static void succeed() {
    StdOut.println("Parentheses are balanced");
  }

  private static void fail() {
    StdOut.println("Parentheses are not balanced");
  }

  private static void processChar(char c) throws UnbalancedParanthesesException {
    if (c == ' ' || c == '\n') return;

    switch(c) {
      case '{', '[', '(':
        stack.push(c);
        break;
      case '}', ']', ')':
        checkBalances(c);
        break;
      default:
        throw new RuntimeException("Invalid character " + c);
    }
  }

  private static void checkBalances(char c) throws UnbalancedParanthesesException {
    if (stack.isEmpty()) throw new UnbalancedParanthesesException();

    else if (stack.peek() == balancingChar(c)) {
      stack.pop();
      return;
    }

    throw new UnbalancedParanthesesException();
  }

  private static char balancingChar(char c) {
    switch(c) {
      case '}': return '{';
      case ')': return '(';
      case ']': return '[';
      default:
        throw new RuntimeException("Invalid char " + c);
    }
  }

  private static class UnbalancedParanthesesException extends Exception {}
}
