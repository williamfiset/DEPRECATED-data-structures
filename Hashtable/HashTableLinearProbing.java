/**
 * An implementation of a hash-table using open addressing with linear probing 
 * as a collision resolution method.
 *
 * @author William Fiset, william.alexandre.fiset@gmail.com
 **/

import java.lang.reflect.*;
import java.util.BitSet;

@SuppressWarnings("unchecked")
public class HashTableLinearProbing <K, V> {
  
  private Class <K> keyClassType;
  private Class <V> valueClassType;

  private double loadFactor;
  private int capacity, threshold, size = 0;

  // Store the key-value pairs in separate arrays 
  // instead of bundling them in a wrapper class
  private K [] keyTable;
  private V [] valueTable;
  private BitSet deleted;

  // Delete token marker. Set at runtime in constructor.
  private K DELETE_TOKEN = null;

  private static final int DEFAULT_CAPACITY = 3;
  private static final double DEFAULT_LOAD_FACTOR = 0.7;

  public HashTableLinearProbing(Class <K> keyClassType, Class <V> valueClassType) {
    this(keyClassType, valueClassType, DEFAULT_CAPACITY, DEFAULT_LOAD_FACTOR);
  }

  public HashTableLinearProbing (Class <K> keyClassType, Class <V> valueClassType, int capacity) {
    this(keyClassType, valueClassType, capacity, DEFAULT_LOAD_FACTOR);
  }

  // Designated constructor
  public HashTableLinearProbing (Class <K> keyClassType, Class <V> valueClassType, int capacity, double loadFactor) {
    
    if (capacity < 0)
      throw new IllegalArgumentException("Illegal capacity: " + capacity);
    
    if (loadFactor <= 0 || Double.isNaN(loadFactor) || Double.isInfinite(loadFactor))
      throw new IllegalArgumentException("Illegal loadFactor: " + loadFactor);

    this.loadFactor = loadFactor;
    this.capacity = Math.max(DEFAULT_CAPACITY, capacity);
    threshold = (int) (this.capacity * loadFactor);

    this.keyClassType = keyClassType;
    this.valueClassType = valueClassType;

    keyTable   = (K[]) java.lang.reflect.Array.newInstance(keyClassType, this.capacity);
    valueTable = (V[]) java.lang.reflect.Array.newInstance(valueClassType, this.capacity);
    deleted = new BitSet(capacity);

    // DELETE_TOKEN = keyClassType.newInstance();

    // keyTable   = (K[]) new Object[this.capacity];
    // valueTable = (V[]) new Object[this.capacity];

  }

  public void clear() {
    for (int i = 0; i < capacity; i++) {
      keyTable[i] = null;
      valueTable[i] = null;
    }
    size = 0;
  }

  public int size() {
    return size;
  }
  
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
    int bucketIndex = normalizeIndex(key.hashCode());
    
    // Starting at the bucketIndex linearly probe until a free spot is found.
    for (int i = bucketIndex ;; i = ((i == capacity) ? 0 : i + 1)  ) {

      if (keyTable[i] != null) {

        // If key already exists in the hash-table update its value
        if (keyTable[i].equals(key)) {
          V oldValue = valueTable[i];
          valueTable[i] = val;
          return oldValue;
        }

      // The current slot is free because it was deleted
      } else if (deleted.get(i)) {
        keyTable[i] = key;
        valueTable[i] = val;
        deleted.set(i);
        return null;

      // Insert new key
      } else {
        size++;
        keyTable[i] = key;
        valueTable[i] = val;
        return null;
      }
    }

  }

  public V get(K key) {
    
    if (key == null) throw new IllegalArgumentException("Null key");
    int bucketIndex = normalizeIndex(key.hashCode());
    int i = bucketIndex, j = -1;

    // Starting at the bucketIndex linearly probe until we find a spot where
    // our key is or we hit a null element in which case our element does not exist
    for (;;) {

      // If the cell was deleted record the first
      // index of where a deleted cell was found.
      if (deleted.get(i)) {
        
        if (j != -1) j = i;

      } else if (keyTable[i] != null) {

        // The key we want is in the hash-table!
        if (keyTable[i].equals(key)) {

          // If we encountered a DELETE_TOKEN while searching for the key
          // we can swap the location the DELETE_TOKEN is with the current
          // entry so that the next time we search for this key it will happen 
          // faster. This is called lazy deletion/relocation.
          if (j != -1) {

            // Copy values to where deleted bucket is
            keyTable[j] = keyTable[i];
            valueTable[j] = valueTable[i];

            // Clear the contents in the current bucket
            keyTable[i] = null;
            valueTable[i] = null;

            // Mark i to now be the deleted cell
            deleted.set(j, false);
            deleted.set(i, true);

            return valueTable[j];
          } else {
            return valueTable[i];
          }

        }
      
      // Element was not found in the hash-table :/
      } else return null;

      i = (i == capacity) ? 0 : i + 1;

    }

  }

  public V remove(K key) {
    
    if (key == null) throw new IllegalArgumentException("Null key");
    int bucketIndex = normalizeIndex(key.hashCode());
    int i = bucketIndex;

    // Starting at the bucketIndex linearly probe until we find a spot where
    // our key is or we hit a null element in which case our element does not exist
    for (;;) {

      // Ignore deleted cells
      if (deleted.get(i)) continue;

      // Key was not found in hash-table.
      if (keyTable[i] == null) break;

      // The key we want to remove is in the hash-table!
      if (keyTable[i].equals(key)) {
        V oldValue = valueTable[i];
        keyTable[i] = null;
        valueTable[i] = null;
        deleted.set(i);
        return oldValue;
      }

      i = (i == capacity) ? 0 : i + 1;

    }

    // Element was not found in the hash-table :/
    return null;

  }

  public static void main(String[] args) {

    // HashTableLinearProbing <String, Integer> map = new HashTableLinearProbing<>( String.class, Integer.class, 10, 0.5);
    // map.insert("Will", 21);

  }

}




















