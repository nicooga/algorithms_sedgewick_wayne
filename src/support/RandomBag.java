package algsex.support;

import java.util.Iterator;
import edu.princeton.cs.algs4.StdRandom;

public class RandomBag<T> implements Iterable<T> {
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