package algsex.chapter1.section3;

import java.util.Iterator;
import edu.princeton.cs.algs4.*;

// 1.3.3 4Random bag. A random bag stores a collection of items and supports the following API:

// public class RandomBag<Item> implements Iterable<Item>
// - RandomBag() create an empty random bag
// - boolean isEmpty() is the bag empty?
// - int size() number of items in the bag
// - void add(Item item) add an item

// Write a class RandomBag that implements this API.
// Note that this API is the same as for Bag, except for the adjective random,
// which indicates that the iteration should provide the items in random order
// (all N! permutations equally likely, for each iterator).

// Hint: Put the items in an array and randomize their order in the iterator’s constructor.
public class Exercise34 {
  private static int BAG_SIZE = 10;
  private static int ITERATIONS = 10000000;

  public static void main(String[] args) {
    RandomBag<Integer> bag = new RandomBag<>();
    double[][] distribution = new double[BAG_SIZE][BAG_SIZE];

    assert bag.isEmpty();
    assert bag.size() == 0;

    for (int i = 1; i <= BAG_SIZE; i++) bag.add(i);

    assert !bag.isEmpty();
    assert bag.size() == BAG_SIZE;

    for (int i = 1; i <= ITERATIONS; i++) {
      int j = 0;

      for (int n : bag) {
        distribution[n-1][j] += 1.0;
        j++;
      }
    }

    for (double[] d : distribution)
      for (int i = 0; i < d.length; i++)
        d[i] = 1.0/BAG_SIZE - d[i]/ITERATIONS;

    int n = 0;

    for (double[] d : distribution)
      for (double dd : d)
        assert dd < 0.01;

    StdOut.println("Tests passed");
  }

  public static class RandomBag<T> implements Iterable<T> {
    private Node first;
    private int size;

    public boolean isEmpty() { return size == 0; }
    public int size() { return size; }

    public void add(T item) {
      Node oldFirst = first;

      first = new Node();
      first.item = item;
      first.next = oldFirst;

      size++;
    }

    public RandomIterator iterator() { return new RandomIterator(); }

    private class Node {
      T item;
      Node next;
    }

    private class RandomIterator implements Iterator<T> {
      T[] items;
      int i;

      RandomIterator() {
        Node node = first;

        items = (T[]) new Object[size];
        i = 0;

        while (node != null) {
          items[i] = node.item;
          node = node.next;
          i++;
        }

        for (int i = 0; i < items.length; i++) {
          int randomIndex = i + StdRandom.uniform(items.length - i);
          T temp = items[i];
          items[i] = items[randomIndex];
          items[randomIndex] = temp;
        }

        i = 0;
      }

      public boolean hasNext() {
        return i < size;
      }

      public T next() { return items[i++]; }
    }
  }
}
