package algsex.chapter1.section3;

import java.util.*;
import edu.princeton.cs.algs4.*;

// 1.3.36 Random iterator. Write an iterator for RandomQueue<Item> from the previous
// exercise that returns the items in random order.
public class Exercise36 {
  private static int CARDS_IN_A_DECK = 52;

  public static void main(String[] args) {
    Set<Integer> set = new HashSet<>();
    RandomQueue<Integer> deck = new RandomQueue<>();

    for (int i = 1; i <= CARDS_IN_A_DECK; i++) deck.enqueue(i);
    for (int n : deck) set.add(n);

    assert set.size() == CARDS_IN_A_DECK;

    StdOut.println("Tests passed");
  }

  private static class RandomQueue<T> implements Iterable<T> {
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

    public Iterator<T> iterator() {
      return new RandomIterator();
    }

    private void resize(int newSize) {
      T[] temp = (T[]) new Object[newSize];
      for (int i = 0; i < size; i++) temp[i] = items[i];
      items = temp;
    }

    private class RandomIterator implements Iterator<T> {
      T[] itemsCopy;
      int i;

      RandomIterator() {
        itemsCopy = (T[]) new Object[size];

        for (int i = 0; i < size; i++)
          itemsCopy[i] = items[i];

        for (int i = 0; i < itemsCopy.length; i++) {
          int randomIndex = i + StdRandom.uniform(itemsCopy.length - i);
          T randomItem = itemsCopy[randomIndex];
          itemsCopy[randomIndex] = itemsCopy[i];
          itemsCopy[i] = randomItem;
        }
      }

      public boolean hasNext() { return i < itemsCopy.length; }
      public T next() { return itemsCopy[i++]; }
    }
  }
}
