/**
 * An implementation of a hash-table using open addressing with linear probing 
 * as a collision resolution method. 
 *
 * NOTE: This file is still in development.
 *
 * @author William Fiset, william.alexandre.fiset@gmail.com
 **/

import java.lang.reflect.*;
import java.util.BitSet;

@SuppressWarnings("unchecked")
public class HashTableLinearProbing <K, V> {

  private double loadFactor;
  private int capacity, threshold, size = 0;

  // Store the key-value pairs in separate arrays 
  // instead of bundling them in a wrapper class
  private K [] keyTable;
  private V [] valueTable;
  private BitSet deleted;

  private static final int DEFAULT_CAPACITY = 3;
  private static final double DEFAULT_LOAD_FACTOR = 0.7;

  public HashTableLinearProbing() {
    this(DEFAULT_CAPACITY, DEFAULT_LOAD_FACTOR);
  }

  public HashTableLinearProbing(int capacity) {
    this(capacity, DEFAULT_LOAD_FACTOR);
  }

  // Designated constructor
  public HashTableLinearProbing(int capacity, double loadFactor) {
    
    if (capacity < 0)
      throw new IllegalArgumentException("Illegal capacity: " + capacity);
    
    if (loadFactor <= 0 || Double.isNaN(loadFactor) || Double.isInfinite(loadFactor))
      throw new IllegalArgumentException("Illegal loadFactor: " + loadFactor);

    this.loadFactor = loadFactor;
    this.capacity = Math.max(DEFAULT_CAPACITY, capacity);
    threshold = (int) (this.capacity * loadFactor);

    keyTable   = (K[]) new Object[this.capacity];
    valueTable = (V[]) new Object[this.capacity];
    deleted = new BitSet(capacity);

  }

  // Clears all the contents of the hash-table
  public void clear() {
    for (int i = 0; i < capacity; i++) {
      keyTable[i] = null;
      valueTable[i] = null;
    }
    deleted.clear();
    size = 0;
  }

  // Returns the number of elements currently inside the hash-table
  public int size() {
    return size;
  }
  
  // Returns true/false depending on whether the hash-table is empty
  public boolean isEmpty() {
    return size == 0;
  }

  // Converts a hash value to an index. Essentially, this strips the
  // negative sign and places the hash value in the domain [0, 2^31)
  private int normalizeIndex(int keyHash ) {
    return (keyHash & 0x7fffffff) % capacity;
  }

  // Insert, put and add all place a value in the hash-table
  public V put(K key, V value) { return insert(key, value); }
  public V add(K key, V value) { return insert(key, value); }

  public V insert(K key, V val) {
  
    if (key == null) throw new IllegalArgumentException("Null key");
    if (size >= threshold) resizeTable();
    int i = normalizeIndex(key.hashCode());
    
    for (;;) {

      // The current slot was previously deleted
      if (deleted.get(i)) {

        size++;
        keyTable[i] = key;
        valueTable[i] = val;
        deleted.set(i, false);
        return null;
      
      // The current cell already contains a key
      } else if (keyTable[i] != null) {

        // The key we're trying to insert already exists in the hash-table,
        // so update its value with the most recent value
        if (keyTable[i].equals(key)) {
          V oldValue = valueTable[i];
          valueTable[i] = val;
          return oldValue;
        }

      // Current cell is null so insert new key here
      } else {

        size++;
        keyTable[i] = key;
        valueTable[i] = val;
        return null;

      }

      i = (i == capacity-1) ? 0 : i + 1;

    }

  }

  public V get(K key) {
    
    if (key == null) throw new IllegalArgumentException("Null key");
    int bucketIndex = normalizeIndex(key.hashCode());
    int i = bucketIndex, j = -1;

    // Starting at the original bucketIndex linearly probe until we find a spot where
    // our key is or we hit a null element in which case our element does not exist.
    for (;;) {

      // Ignore deleted cells, but record where the first index
      // of a deleted cell is found to perform lazy relocation later.
      if (deleted.get(i)) {
        
        if (j == -1) j = i;

      // We hit a non-null key, perhaps it's the one we're looking for.
      } else if (keyTable[i] != null) {

        // The key we want is in the hash-table!
        if (keyTable[i].equals(key)) {

          // If j != -1 this means we previously encountered a deleted cell.
          // We can perform an optimization by swapping the entries in cells
          // i and j so that the next time we search for this key it will be
          // found faster. This is called lazy deletion/relocation.
          if (j != -1) {

            // Copy values to where deleted bucket is
            keyTable[j] = keyTable[i];
            valueTable[j] = valueTable[i];

            // Clear the contents in the current bucket
            keyTable[i] = null;
            valueTable[i] = null;

            // Mark i to now be the deleted cell
            deleted.set(i, true);
            deleted.set(j, false);

            return valueTable[j];
          } else {
            return valueTable[i];
          }

        }
      
      // Element was not found in the hash-table :/
      } else return null;

      i = (i == capacity-1) ? 0 : i + 1;

    }

  }

  public V remove(K key) {
    
    if (key == null) throw new IllegalArgumentException("Null key");

    int bucketIndex = normalizeIndex(key.hashCode());
    int i = bucketIndex;

    // Starting at the bucketIndex linearly probe until we find a spot where
    // our key is or we hit a null element in which case our element does not exist
    for (;; i = (i == capacity-1) ? 0 : i + 1) {

      // Ignore deleted cells
      if (deleted.get(i)) continue;

      // Key was not found in hash-table.
      if (keyTable[i] == null) return null;

      // The key we want to remove is in the hash-table!
      if (keyTable[i].equals(key)) {
        size--;
        V oldValue = valueTable[i];
        keyTable[i] = null;
        valueTable[i] = null;
        deleted.set(i);
        return oldValue;
      }

    }

  }

  private void resizeTable() {

    capacity *= 2;
    threshold = (int) (capacity * loadFactor);

    K[] oldKeyTable   = (K[]) new Object[capacity];
    V[] oldValueTable = (V[]) new Object[capacity];

    // Perform key table pointer swap
    K[] keyTableTmp = keyTable;
    keyTable = oldKeyTable;
    oldKeyTable = keyTableTmp;

    // Perform value table pointer swap
    V[] valueTableTmp = valueTable;
    valueTable = oldValueTable;
    oldValueTable = valueTableTmp;

    for (int i = 0; i < oldKeyTable.length; i++) {
      if (!deleted.get(i) && oldKeyTable[i] != null)
        insert(oldKeyTable[i], oldValueTable[i]);
      oldValueTable[i] = null;
      oldKeyTable[i] = null;
    }

    deleted = new BitSet(capacity);

  }

  @Override public String toString() {

    StringBuilder sb = new StringBuilder();
    sb.append("{");
    for (int i = 0; i < capacity; i++)
      if (!deleted.get(i) && keyTable[i] != null)
        sb.append( keyTable[i] + " => " + valueTable[i] + ", ");
    sb.append("}");
    return sb.toString();

  }

  public static void main(String[] args) {

    // HashTableLinearProbing <Integer, Integer> map = new HashTableLinearProbing<>(30);
    // for (int i = 0; i < 50; i++) {
    //   int v = (int)(Math.random() * 15);
    //   map.insert(v, i*i);
    // }

    // for (int i = 0; i < 10; i++) {
    //   int v = (int)(Math.random() * 40);
    //   System.out.println(map.get(v));
    //   map.remove(v);
    //   System.out.println(map.get(v));
    // }

    // for (int i = 0; i < 50; i++) {
    //   int v = (int)(Math.random() * 15);
    //   map.insert(v, i*i);
    // }

    // for (int i = 0; i < 10; i++) {
    //   int v = (int)(Math.random() * 40);
    //   System.out.println(map.get(v));
    //   map.remove(v);
    //   System.out.println(map.get(v));
    // }


    // // map.remove(55);
    // // map.remove(65);
    // // map.remove(70);
    // System.out.println(map);
    
  }

}




















