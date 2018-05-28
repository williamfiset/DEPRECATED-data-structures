/**
 * An implementation of an indexed binary heap priority queue.
 * 
 * This implementation supports arbitrary keys with comparable values.  
 * To use arbitrary keys (such as strings or objects) first map all your
 * keys to the integer domain [0, N) where N is the number of keys
 * you have and then use the mapping with this indexed priority queue.
 *
 * As convention, I denote  'ki' as the index value in the domain [0, N)
 * associated with key k, therefore: ki = map[k]
 *
 * @author William Fiset, william.alexandre.fiset@gmail.com
 */

package com.williamfiset.datastructures.priorityqueue;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class MinIndexedBinaryHeap <T> {

  // Current number of elements in the heap
  private int n;

  // Maximum number of elements in the heap.
  private final int N;

  // The values associated with the keys. It is very important  to note 
  // that this array is indexed by the key indexes (aka 'ki')
  public final Object[] values;

  // The Position Map (pm) maps Key Indexes (ki) to where the
  // position of that key is represented in the priority queue
  // which uses the domain [0, n).
  public final int[] pm;

  // The Priority Queue (pq) stores the indexes of the keys in the range
  // [0, n) which make up the priority queue. It should be noted that
  // pq and pm are inverses of each other, so: pm[pq[i]] = pq[pm[i]] = i
  public final int[] pq;

  public MinIndexedBinaryHeap(int maxSize) {
    if (maxSize <= 0) throw new IllegalArgumentException("maxSize <= 0");
    N = maxSize;
    pq = new int[N];
    pm = new int[N];
    values = new Object[N];
    for (int i = 0; i < N; i++)
      pq[i] = pm[i] = -1;
  }

  public int size() {
    return n;
  }

  public boolean isEmpty() {
    return n == 0;
  }

  public boolean contains(int ki) {
    keyInBoundsOrThrow(ki);
    return pm[ki] != -1;
  }

  public int peekMinKeyIndex() {
    isNotEmptyOrThrow();
    return pq[0];
  }

  public int pollMinKeyIndex() {
    int minki = peekMinKeyIndex();
    delete(minki);
    return minki;
  }

  @SuppressWarnings("unchecked")
  public T peekMinValue() {
    isNotEmptyOrThrow();
    return (T) values[pq[0]];
  }

  public T pollMinValue() {
    T minValue = peekMinValue();
    delete(peekMinKeyIndex());
    return minValue;
  }

  public void delete(int ki) {
    keyExistsOrThrow(ki);
    final int i = pm[ki];
    swap(i, --n);
    sink(i);
    swim(i);
    values[ki] = null;
    pm[ki] = -1;
    pq[n] = -1;
  }

  public void insert(int ki, Object value) {
    if (contains(ki))
      throw new IllegalArgumentException("index already exists; received: " + ki);
    valueNotNullOrThrow(value);
    pm[ki] = n;
    values[ki] = value;
    pq[n] = ki;
    swim(n++);
  }

  @SuppressWarnings("unchecked")
  public T valueOf(int ki) {
    keyExistsOrThrow(ki);
    return (T) values[ki];
  }

  public void update(int ki, Object value) {
    keyExistsAndValueNotNullOrThrow(ki, value);
    final int i = pm[ki];
    values[ki] = value;
    sink(i);
    swim(i);
  }

  public void decrease(int ki, Object value) {
    keyExistsAndValueNotNullOrThrow(ki, value);
    if (less(value, values[ki])) {
      values[ki] = value;
      swim(pm[ki]);
    }
  }

  public void increase(int ki, Object value) {
    keyExistsAndValueNotNullOrThrow(ki, value);
    if (less(values[ki], value)) {
      values[ki] = value;
      sink(pm[ki]);
    }
  }

    /* Helper functions */

  private void sink(int i) {
    while (true) {
      int left  = 2 * i + 1;
      int right = 2 * i + 2;
      int smallest = left;

      // Find which is smaller left or right.
      if (right < n && less(right, left))
        smallest = right;

      // Stop if we're outside the bounds of the tree
      // or stop early if we cannot sink i anymore.
      if (left >= n || less(i, smallest)) break;
      
      // Move down the tree following the smallest node.
      swap(smallest, i);
      i = smallest;
    }
  }

  private void swim(int i) {
    for(int p = (i-1)/2; i > 0 && less(i, p); p = (i-1)/2) {
      swap(i, p);
      i = p;
    }
  }

  private void swap(int i, int j) {
    int tmp = pq[i];
    pq[i] = pq[j];
    pq[j] = tmp;
    pm[pq[i]] = i;
    pm[pq[j]] = j;
  }

  // Tests if the value of node i <= node j
  @SuppressWarnings("unchecked")
  private boolean less(int i, int j) {
    return ((Comparable<? super T>) values[pq[i]]).compareTo((T) values[pq[j]]) <= 0;
  }

  @SuppressWarnings("unchecked")
  private boolean less(Object obj1, Object obj2) {
    return ((Comparable<? super T>) obj1).compareTo((T) obj2) <= 0;
  }

    /* Helper functions to make the code more readable. */

  private void isNotEmptyOrThrow() {
    if (isEmpty()) throw new NoSuchElementException("Priority queue underflow");
  }

  private void keyExistsAndValueNotNullOrThrow(int ki, Object value) {
    keyExistsOrThrow(ki);
    valueNotNullOrThrow(value);
  }

  private void keyExistsOrThrow(int ki) {
    if (!contains(ki)) 
      throw new NoSuchElementException("Index does not exist; received: " + ki);
  }

  private void valueNotNullOrThrow(Object value) {
    if (value == null) 
      throw new IllegalArgumentException("value cannot be null");
  }

  private void keyInBoundsOrThrow(int ki) {
    if (ki < 0 || ki >= N) 
      throw new IllegalArgumentException("Key index out of bounds; received: " + ki);
  }

  @Override
  public String toString() {
    List<Integer> lst = new ArrayList<>(n);
    for(int i = 0; i < n; i++) lst.add(pq[i]);
    return lst.toString();
  }

    /* Test functions */

  // Recursively checks if this heap is a min heap. This method is used
  // for testing purposes to validate the heap invariant.
  public boolean isMinHeap() {
    return isMinHeap(0);
  }

  private boolean isMinHeap(int i) {
    if (i >= n) return true;
    int left  = 2 * i + 1;
    int right = 2 * i + 2;

    // Make sure that the current node i is less than both of its children left, 
    // and right if they exist return false otherwise to indicate an invalid heap.
    if (left < n && !less(i, left)) return false;
    if (right < n && !less(i, right)) return false;

    // Recurse on both children to make sure they're also valid heaps
    return isMinHeap(left) && isMinHeap(right);
  }

}



















