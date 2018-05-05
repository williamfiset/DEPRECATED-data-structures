/**
 * File WIP.
 */
package com.williamfiset.datastructures.priorityqueue;

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

  public T peekMinValue() {
    isNotEmptyOrThrow();
    return values[pq[0]];
  }

  public T pollMinValue() {
    T minValue = peekMinValue();
    delete(peekMinIndex());
    return minValue;
  }

  public void delete(int ki) {
    final int i = keyposmap[ki];
    swap(i, --n);
    sink(i);
    values[ki] = null;
    keyposmap[ki] = -1;
  }

  public void insert(int ki, T value) {
    if (contains(ki))
      throw new IllegalArgumentException("index already exists; received: " + ki);
    valueNotNullOrThrow(value);
    keyposmap[ki] = n;
    values[ki] = value;
    pq[n] = ki;
    // System.out.println("insert: " + ki + " with value: " + value);
    // System.out.println(java.util.Arrays.toString(pq));
    // System.out.println(java.util.Arrays.toString(keyposmap));
    // System.out.println(java.util.Arrays.toString(values));
    swim(n++);
  }

  public T valueOf(int ki) {
    keyExistsOrThrow(ki);
    return values[ki];
  }

  public void update(int ki, T value) {
    keyExistsAndValueNotNullOrThrow(ki, value);
    final int i = keyposmap[ki];
    values[ki] = value;
    sink(i);
    swim(i);
  }

  public void decrease(int ki, T value) {
    keyExistsAndValueNotNullOrThrow(ki, value);
    if (value.compareTo(values[ki]) <= 0) {
      values[ki] = value;
      swim(keyposmap[ki]);
    }
  }

  public void increase(int ki, T value) {
    keyExistsAndValueNotNullOrThrow(ki, value);
    if (value.compareTo(values[ki]) >= 0) {
      values[ki] = value;
      sink(keyposmap[ki]);
    }
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
  // This method assumes i & j are valid indexes, O(1)
  private boolean less(int i, int j) {
    return values[pq[i]].compareTo(values[pq[j]]) <= 0;
  }

  private void isNotEmptyOrThrow() {
    if (isEmpty()) throw new NoSuchElementException("Priority queue underflow");
  }

  private void keyExistsAndValueNotNullOrThrow(int ki, T value) {
    keyExistsOrThrow(ki);
    valueNotNullOrThrow(value);
  }

  private void keyExistsOrThrow(int ki) {
    if (!contains(ki)) 
      throw new NoSuchElementException("Index does not exist; received: " + ki);
  }

  private void valueNotNullOrThrow(T value) {
    if (value == null) 
      throw new IllegalArgumentException("value cannot be null");
  }

  private void keyInBoundsOrThrow(int ki) {
    if (ki < 0 || ki >= N) 
      throw new IllegalArgumentException("Key index out of bounds; received: " + ki);
  }

}



















