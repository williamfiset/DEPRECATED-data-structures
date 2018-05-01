/**
 * File WIP.
 */

import java.util.Arrays;
import java.util.NoSuchElementException;

public class MinIndexedBinaryHeap <T extends Comparable<T>> {

  // Number of elements in the heap
  private int n;

  // Maximum number of elements in the size of the heap.
  private final int N;

  private final T[] values;
  private final int[] pq, qp;

  @SuppressWarnings("unchecked")
  public MinIndexedBinaryHeap(int N) {
    if (N <= 0) throw new IllegalArgumentException("N <= 0");
    this.N = N;
    pq = new int[N];
    qp = new int[N];
    values = (T[]) new Object[N];
    for (int i = 0; i < N; i++)
      pq[i] = qp[i] = -1;
  }

  public int size() {
    return n;
  }

  public boolean isEmpty() {
    return n == 0;
  }

  public boolean contains(int ki) {
    return qp[ki] != -1;
  }

  public void insert(int ki, T value) {
    if (contains(ki)) throw new IllegalArgumentException("index already present.");
    pq[n] = ki;
    qp[ki] = n;
    values[n] = value;
    swim(n);
    n++;
  }

  public T valueOf(int ki) {
    if (!contains(ki)) throw new NoSuchElementException("index does not exist");
    return values[qp[ki]];
  }

  public void update(int ki, T value) {
    // Update the value mapped with index i
  }

  public void decreaseValue(int ki, T value) {
    if (value == null) throw new IllegalArgumentException("value cannot be null");
    final int i = qp[ki];
    if (value.compareTo(values[i]) <= 0) {
      values[i] = value;
      swim(i);
    }
  }

  public void increaseValue(int ki, T value) {
    if (value == null) throw new IllegalArgumentException("value cannot be null");
    final int i = qp[ki];
    if (value.compareTo(values[i]) >= 0) {
      values[i] = value;
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
      // Exchange i with the parent
      swap(p, i);
      i = p;
    }
  }

  private void swap(int i, int j) {
    int tmp = pq[i];
    pq[i] = pq[j];
    pq[j] = tmp;
    qp[pq[i]] = i;
    qp[pq[j]] = j;
  }

  // Tests if the value of node i <= node j
  // This method assumes i & j are valid indices, O(1)
  private boolean less(int i, int j) {
    return values[i].compareTo(values[j]) <= 0;
  }

}



















