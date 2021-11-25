package algsex.chapter1.section3;

import algsex.support.*;
import edu.princeton.cs.algs4.StdIn;

// 1.3.4 Write a stack client Parentheses that reads in a text stream from standard input
// and uses a stack to determine whether its parentheses are properly balanced. For example,
// your program should print true for [()]{}{[()()]()} and false for [(]).
public class Exercise4 {
    private static Out out = new StdOut();

    public static void main(String[] args) {
        BalancedParentheses b = new BalancedParentheses(out);
        StringBuilder string = new StringBuilder();
  
        while(!StdIn.isEmpty()) 
            string.append(StdIn.readChar());
  
        if (b.isBalanced(string.toString()))
            out.println("true");
        else
            out.println("false");
    }

    public static void test() {
        OutputInterceptor interceptor = new OutputInterceptor();
        out = interceptor;
        test("[()]{}{[()()]()}", "true\n", interceptor);
        test("[(])", "false\n", interceptor);
    }

    private static void test(String input, String output, OutputInterceptor interceptor) {
        interceptor.reset();
        Test.simulateInput(input);
        main(Test.emptyArgs());
        Test.assertEqual(interceptor.contents(), output);
    }

    private static class BalancedParentheses {
        private final Out out;

        public BalancedParentheses(Out out) {
            this.out = out;
        }

        public boolean isBalanced(String string) {
            Stack<Character> stack = new Stack<>();

            for (int i = 0; i < string.length(); i++) {
                char c = string.charAt(i);

                if (isSpacingChar(c)) continue;
                else if (isOpeningParentheses(c)) stack.push(c);
                else if (isClosingParentheses(c))
                    if (!stack.isEmpty() && balancingChar(c) == stack.peek()) {
                        stack.pop();
                        continue;
                    } else
                        return false;
                else
                    throw new RuntimeException("Invalid character " + c);
            }

            return stack.isEmpty();
        }
  
        private char balancingChar(char c) {
            switch(c) {
                case '}': return '{';
                case ')': return '(';
                case ']': return '[';
                default:
                    throw new RuntimeException("Invalid char " + c);
            }
        }

        private boolean isSpacingChar(char c) {
            return c == ' ' || c == '\n';
        }

        private boolean isOpeningParentheses(char c) {
            return c == '{' || c == '[' || c == '(';
        }

        private boolean isClosingParentheses(char c) {
            return c == '}' || c == ']' || c == ')';
        }
    }
}
