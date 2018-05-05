/**
 * File WIP.
 */
package com.williamfiset.datastructures.priorityqueue;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class MinIndexedBinaryHeap <T extends Comparable<T>> {

  // Number of elements in the heap
  private int n;

  // Maximum number of elements in the size of the heap.
  private final int N;

  // The values associated with the key indexes. It is very important
  // to note that these values are indexed by the key indexes themselves.
  private final T[] values;

  private final int[] pq, keyposmap; // kpm?

  @SuppressWarnings("unchecked")
  public MinIndexedBinaryHeap(int N) {
    if (N <= 0) throw new IllegalArgumentException("N <= 0");
    this.N = N;
    pq = new int[N];
    keyposmap = new int[N];
    values = (T[]) new Comparable[N];
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
    return keyposmap[ki] != -1;
  }

  // TODO(williamfiset): Poll, Min, Delete

  public void insert(int ki, T value) {
    if (contains(ki)) throw new IllegalArgumentException("index already present.");
    if (value == null) throw new IllegalArgumentException("value cannot be null");
    keyposmap[ki] = n;
    values[ki] = value;
    pq[n] = ki;
    swim(n);
    n++;
  }

  public T valueOf(int ki) {
    if (!contains(ki)) throw new NoSuchElementException("index does not exist");
    return values[ki];
  }

  public void update(int ki, T value) {
    if (!contains(ki)) throw new NoSuchElementException("index does not exist");
    if (value == null) throw new IllegalArgumentException("value cannot be null");
    final int i = keyposmap[ki];
    values[ki] = value;
    sink(i);
    swim(i);
  }
  
  public void decrease(int ki, T value) {
    if (!contains(ki)) throw new NoSuchElementException("index does not exist");
    if (value == null) throw new IllegalArgumentException("value cannot be null");
    final int i = keyposmap[ki];
    if (value.compareTo(values[ki]) <= 0) {
      values[ki] = value;
      swim(i);
    }
  }

  public void increase(int ki, T value) {
    if (!contains(ki)) throw new NoSuchElementException("index does not exist");
    if (value == null) throw new IllegalArgumentException("value cannot be null");
    final int i = keyposmap[ki];
    if (value.compareTo(values[ki]) >= 0) {
      values[ki] = value;
      sink(i);
    }
  }

  private void sink(int i) {
    while (true) {
      int left  = 2 * i + 1; // Left node
      int right = 2 * i + 2; // Right node
      int smallest = left;   // Assume left is the smallest node of the two children

      // Find which is smaller left or right
      // If right is smaller set smallest to be right
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
      swap(p, i);
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
  // This method assumes i & j are valid indices, O(1)
  private boolean less(int i, int j) {
    return values[i].compareTo(values[j]) <= 0;
  }

}



















