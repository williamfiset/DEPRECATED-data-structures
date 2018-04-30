/**
 * File WIP.
 */

public class IndexedBinaryHeap <T extends Comparable<T>> {

  // Number of elements in the heap
  private int n;

  // Size of the heap.
  private final int N;

  private final T[] values;
  private final int[] pq, qp;

  public IndexedBinaryHeap(int N) {
    // if (N <= 0) ...
    this.N = N;
    pq = new int[N];
    qp = new int[N];
    values = (T[]) new Object[N];
    java.util.Arrays.fill(pq, -1);
    java.util.Arrays.fill(qp, -1);
  }

  public int size() {
    return n;
  }

  public boolean isEmpty() {
    return n == 0;
  }

  public boolean contains(int i) {
    return qp[i] != -1;
  }

  public void insert(int i, T value) {
    if (contains(i)) throw new IllegalArgumentException("index already present.");
    pq[i] = n; // pq and qp are reversed -_-
    qp[n] = i;
    values[n] = value;
    swim(n);
    n++;
  }

  public T valueOf(int i) {
    if (!contains(i)) throw new NoSuchElementException("index does not exist");
    return values[i];
  }

  public void update(int i, T value) {
    // Update the value mapped with index i
  }

  public void decreaseValue(int i, T value) {

  }

  public void increaseValue(int i, T value) {

  }

  private void sink(int i) {

  }

  private void swim(int i) {

  }

  private void swap(int i, int j) {

  }

}



















