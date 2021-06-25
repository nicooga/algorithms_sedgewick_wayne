package algsex.chapter1.section3;

import java.util.*;
import edu.princeton.cs.algs4.*;

// 1.3.35 Random queue. A random queue stores a collection of items and supports the following API:

// public class RandomQueue<Item>
// - RandomQueue() create an empty random queue
// - boolean isEmpty() is the queue empty?
// - void enqueue(Item item) add an item
// - Item dequeue() remove and return a random item (sample without replacement)
// - Item sample() return a random item, but do not remove (sample with replacement)

// Write a class RandomQueue that implements this API.
// Hint: Use an array representation (with resizing).
// To remove an item, swap one at a random position (indexed 0 through N-1) with the one at the last position (index N-1).
// Then delete and return the last object, as in ResizingArrayStack.
// Write a client that deals bridge hands (13 cards each) using RandomQueue<Card>.
public class Exercise35 {
  private static int CARDS_IN_A_DECK = 52;
  private static int ITERATIONS = 100;

  public static void main(String[] args) {
    Set<Integer> set = new HashSet<>();
    RandomQueue<Integer> deck;

    for (int i = 0; i < ITERATIONS; i++) {
      deck = new RandomQueue<>();

      set.clear();

      assert deck.isEmpty();

      for (int j = 1; j <= CARDS_IN_A_DECK; j++) deck.enqueue(j);

      assert !deck.isEmpty();

      for (int j = 1; j <= CARDS_IN_A_DECK; j++) set.add(deck.dequeue());

      assert set.size() == CARDS_IN_A_DECK;
    }

    StdOut.println("Tests passed");
  }

  private static class RandomQueue<T> {
    private T[] items;
    private int size;

    public RandomQueue() {
      this.items = (T[]) new Object[10];
    }

    public boolean isEmpty() { return size == 0; }

    public void enqueue(T item) {
      if (size == items.length)
        resize(items.length * 2);
      items[size] = item;
      size++;
    }

    public T dequeue() {
      int randomIndex = StdRandom.uniform(size);
      T randomItem = items[randomIndex];

      items[randomIndex] = items[size-1];
      items[size-1] = null;

      size--;

      if (size > 0 && size == items.length / 4)
        resize(items.length / 2);

      return randomItem;
    }

    public T sample() {
      int randomIndex = StdRandom.uniform(size);
      return items[randomIndex];
    }

    private void resize(int newSize) {
      T[] temp = (T[]) new Object[newSize];
      for (int i = 0; i < size; i++) temp[i] = items[i];
      items = temp;
    }
  }
}
