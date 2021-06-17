package algsex.chapter1.section3;

import edu.princeton.cs.algs4.*;

// 1.3.12 Write an iterable Stack client that has a static method copy() that takes a stack
// of strings as argument and returns a copy of the stack. Note : This ability is a prime
// example of the value of having an iterator, because it allows development of such functionality
// without changing the basic API.
public class Exercise12 {
  public static void main(String[] args) {
    Stack<String> original = new Stack<>();

    original.push("asdf");
    original.push("qwer");
    original.push("uiop");

    Stack<String> copy = copy(original);

    assert original.pop() == "uiop";
    assert original.pop() == "qwer";
    assert original.pop() == "asdf";
    assert original.isEmpty();

    assert copy.pop() == "uiop";
    assert copy.pop() == "qwer";
    assert copy.pop() == "asdf";
    assert copy.isEmpty();

    StdOut.println("Tests passed");
  }

  private static Stack<String> copy(Stack<String> s) {
    Stack<String> copy = new Stack<>();
    Stack<String> temp = new Stack<>();

    java.util.Iterator<String> iterator = s.iterator();

    while(iterator.hasNext())
      temp.push(iterator.next());

    while(!temp.isEmpty())
      copy.push(temp.pop());

    return copy;
  }
}
