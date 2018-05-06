/**
 * File WIP.
 */
package com.williamfiset.datastructures.priorityqueue;

import java.util.NoSuchElementException;
import java.util.List;
import java.util.ArrayList;

public class MinIndexedBinaryHeap <T> {

  // Number of elements in the heap
  private int n;

  // Maximum number of elements in the heap.
  private final int N;

  // The values associated with the key indexes. It is very important
  // to note that these values are indexed by the key indexes themselves.
  public final Object[] values;

  public final int[] pq;

  public final int[] keyposmap;

  public MinIndexedBinaryHeap(int maxSize) {
    if (maxSize <= 0) throw new IllegalArgumentException("maxSize <= 0");
    N = maxSize;
    pq = new int[N];
    keyposmap = new int[N];
    values = new Object[N];
    for (int i = 0; i < N; i++)
      pq[i] = keyposmap[i] = -1;
  }

  public int size() {
    return n;
  }

  public boolean isEmpty() {
    return n == 0;
  }

  public boolean contains(int ki) {
    keyInBoundsOrThrow(ki);
    return keyposmap[ki] != -1;
  }

  public int peekMinIndex() {
    isNotEmptyOrThrow();
    return pq[0];
  }

  public int pollMinIndex() {
    int minki = peekMinIndex();
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
    delete(peekMinIndex());
    return minValue;
  }

  public void delete(int ki) {
    keyExistsOrThrow(ki);
    final int i = keyposmap[ki];
    swap(i, --n);
    sink(i);
    swim(i);
    values[ki] = null;
    keyposmap[ki] = -1;
    pq[n] = -1;
  }

  public void insert(int ki, Object value) {
    if (contains(ki))
      throw new IllegalArgumentException("index already exists; received: " + ki);
    valueNotNullOrThrow(value);
    keyposmap[ki] = n;
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
    final int i = keyposmap[ki];
    values[ki] = value;
    sink(i);
    swim(i);
  }

  public void decrease(int ki, Object value) {
    keyExistsAndValueNotNullOrThrow(ki, value);
    if (less(value, values[ki])) {
      values[ki] = value;
      swim(keyposmap[ki]);
    }
  }

  public void increase(int ki, Object value) {
    keyExistsAndValueNotNullOrThrow(ki, value);
    if (less(values[ki], value)) {
      values[ki] = value;
      sink(keyposmap[ki]);
    }
  }

  // Recursively checks if this heap is a min heap. This method is used
  // for testing purposes to validate the heap invariant.
  public boolean isMinHeap() {
    return isMinHeap(0);
  }

  private boolean isMinHeap(int i) {
    // If we are outside the bounds of the heap return true 
    if (i >= n) return true;

    int left  = 2 * i + 1;
    int right = 2 * i + 2;

    // Make sure that the current node i is less than
    // both of its children left, and right if they exist
    // return false otherwise to indicate an invalid heap
    if (left < n && !less(i, left)) return false;
    if (right < n && !less(i, right)) return false;

    // Recurse on both children to make sure they're also valid heaps
    return isMinHeap(left) && isMinHeap(right);
  }

  private void sink(int i) {
    while (true) {
      int left  = 2 * i + 1;
      int right = 2 * i + 2;
      int smallest = left;

      // Find which is smaller left or right.
      if (right < n && less(right, left))
        smallest = right;

      // Stop if we're outside the bounds of the tree
      // or stop early if we cannot sink k anymore
      if (left >= n || less(i, smallest)) break;
      
      // Move down the tree following the smallest node
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
    keyposmap[pq[i]] = i;
    keyposmap[pq[j]] = j;
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
    List<Integer> lst1 = new ArrayList<>(n);
    List<Integer> lst2 = new ArrayList<>(n);
    List<Object> lst3 = new ArrayList<>(n);
    for(int i = 0; i < n; i++) {
      lst1.add(pq[i]);
      lst2.add(keyposmap[i]);
      lst3.add(values[i]);
    }
    return lst1.toString() + "\n" + lst2.toString() + "\n" + lst3.toString();
  }

}



















