/**
 * An implementation of a hash-table using open addressing with 
 * quadratic probing as a collision resolution method.
 *
 * In this implementation we are using the following probing function:
 *               H(k, x) = h(k) + f(x) mod 2^n 
 *
 * Where h(k) is the hash for the given key, f(x) = (x + x^2) / 2 and n
 * is a natural number. We are using this probing function because it 
 * is guaranteed to find an empty cell (i.e it generates all the numbers
 * in the range [0, 2^n) without repetition for the first 2^n numbers).
 *
 * @author William Fiset, william.alexandre.fiset@gmail.com
 **/
package com.williamfiset.datastructures.hashtable;

@SuppressWarnings("unchecked")
public class HashTableQuadraticProbing<K, V> extends HashTableOpenAddressingBase<K, V> {

  private static final int DEFAULT_CAPACITY = 8;
  private static final double DEFAULT_LOAD_FACTOR = 0.65;

  public HashTableQuadraticProbing() {
    this(DEFAULT_CAPACITY, DEFAULT_LOAD_FACTOR);
  }

  public HashTableQuadraticProbing(int capacity) {
    this(capacity, DEFAULT_LOAD_FACTOR);
  }

  // Designated constructor
  public HashTableQuadraticProbing(int capacity, double loadFactor) {
    if (capacity <= 0)
      throw new IllegalArgumentException("Illegal capacity: " + capacity);
    
    if (loadFactor <= 0 || Double.isNaN(loadFactor) || Double.isInfinite(loadFactor))
      throw new IllegalArgumentException("Illegal loadFactor: " + loadFactor);
    
    this.loadFactor = loadFactor;
    this.capacity = Math.max(DEFAULT_CAPACITY, nextPowerOfTwo(capacity));
    threshold = (int) (this.capacity * loadFactor);

    keys   = (K[]) new Object[this.capacity];
    values = (V[]) new Object[this.capacity];
  }

  // Given a number this method finds the next 
  // power of two above this value. 
  private static int nextPowerOfTwo(int n) {
    return Integer.highestOneBit(n) << 1;
  }

  // No setup required for quadratic probing.
  @Override
  protected void setupProbing(K key) {}

  @Override
  protected int probe(int x) {
    // Quadratic probing function (x^2+x)/2
    return (x*x + x) >> 1;
  }

  // Adjust the capacity of the hashtable to the next power of two.
  @Override
  protected void adjustCapacity() {
    capacity = nextPowerOfTwo(capacity);
  }

}


















