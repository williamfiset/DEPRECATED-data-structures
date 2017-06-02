/**
 * An implementation of a hash-table using separate chaining with a linked list.
 * @author William Fiset, william.alexandre.fiset@gmail.com
 **/

import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;

class Entry <K, V> {

  int hash;
  K key; V value;

  public Entry(K key, V value) {
    this.key = key;
    this.value = value;
    this.hash = key.hashCode();
  }

  // No casting is required with this method.
  // We are not overriding the Object equals method
  public boolean equals(Entry <K,V> other) {
    // if (other == null) return false; // Obsolete check as entries should not be null
    if ( hash != other.hash ) return false;
    return key.equals( other.key );
  }

  @Override public String toString() {
    return key + " => " + value; 
  }

}

@SuppressWarnings("unchecked")
public class HashTableSeperateChaining <K,V> implements Iterable <K> {

  private static final int DEFAULT_CAPACITY = 3;
  private static final double DEFAULT_LOAD_FACTOR = 0.75;

  private double load_factor;
  private int capacity, threshold, size = 0;
  private LinkedList <Entry<K,V>> [] table;

  public HashTableSeperateChaining () {
    this(DEFAULT_CAPACITY, DEFAULT_LOAD_FACTOR);
  }

  public HashTableSeperateChaining (int capacity) {
    this(capacity, DEFAULT_LOAD_FACTOR);
  }

  // Designated constructor
  public HashTableSeperateChaining (int capacity, double load_factor) {
    if (capacity < 0)
      throw new IllegalArgumentException("Illegal capacity");
    if (load_factor <= 0 || Double.isNaN(load_factor) || Double.isInfinite(load_factor))
      throw new IllegalArgumentException("Illegal load_factor");
    this.load_factor = load_factor;
    this.capacity = Math.max(DEFAULT_CAPACITY, capacity);
    threshold = (int) (this.capacity * load_factor);
    table = (LinkedList<Entry<K,V>>[]) java.lang.reflect.Array.newInstance(LinkedList.class, this.capacity);
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

  // Clears all the contents of the hash-table
  public void clear() {
    for (int i = 0; i < capacity; i++)
      table[i] = null;
    size = 0;
  }

  public boolean containsKey(K key) {
    return hasKey(key);
  }

  public boolean hasKey(K key) {
    int bucketIndex = normalizeIndex(key.hashCode());
    return bucketSeekEntry(bucketIndex, key) != null;
  }

  // Insert, put and add all place a value in the hash-table
  public V put(K key, V value) { return insert(key, value); }
  public V add(K key, V value) { return insert(key, value); }

  public V insert(K key, V value) {

    if (key == null) throw new IllegalArgumentException("Null key");
    Entry <K, V> newEntry = new Entry<>(key, value);
    int bucketIndex = normalizeIndex(newEntry.hash);
    return bucketInsertEntry(bucketIndex, newEntry);

  }

  // Gets a key's values from the map and returns the value.
  // NOTE: returns null if the value is null AND also returns
  // null if the key does not exists, so watch out..
  public V get(K key) {

    if (key == null) return null;
    int bucketIndex = normalizeIndex(key.hashCode());
    Entry <K, V> entry = bucketSeekEntry(bucketIndex, key);
    if (entry != null) return entry.value;
    return null;

  }

  // Removes a key from the map and returns the value.
  // NOTE: returns null if the value is null AND also returns
  // null if the key does not exists.
  public V remove(K key) {

    if (key == null) return null;
    int bucketIndex = normalizeIndex(key.hashCode());
    return bucketRemoveEntry(bucketIndex, key);

  }

  // Removes an entry from a given bucket if it exists
  private V bucketRemoveEntry(int bucketIndex, K key) {

    Entry <K, V> entry = bucketSeekEntry(bucketIndex, key);
    if (entry != null) {
      LinkedList <Entry<K,V>> links = table[bucketIndex];
      links.remove(entry);
      --size;
      return entry.value;
    } else return null;

  }

  // Inserts an entry in a given bucket only if the entry does not already
  // exist in the given bucket, but if it does then update the entry value
  private V bucketInsertEntry(int bucketIndex, Entry <K,V> entry) {
    
    LinkedList <Entry<K,V>> bucket = table[bucketIndex];
    if (bucket == null) table[bucketIndex] = bucket = new LinkedList<>();
    
    Entry <K, V> existantEntry = bucketSeekEntry(bucketIndex, entry.key);
    if (existantEntry == null) {
      bucket.add(entry);
      if (++size > threshold) resizeTable();
      return null; // Use null to indicate that there was no previous entry
    } else {
      V oldVal = existantEntry.value;
      existantEntry.value = entry.value;
      return oldVal;
    }

  }

  // Finds and returns a particular entry in a given bucket if it exists, returns null otherwise
  private Entry <K, V> bucketSeekEntry(int bucketIndex, K key) {

    if (key == null) return null;
    LinkedList <Entry<K,V>> bucket = table[bucketIndex];
    if (bucket == null) return null;
    for (Entry <K,V> entry : bucket)
      if (entry.key.equals(key))
        return entry;
    return null;

  }

  // Resizes the internal table holding buckets of entries
  private void resizeTable() {

    capacity *= 2;
    threshold = (int) (capacity * load_factor);

    LinkedList <Entry<K,V>> newTable [] = (LinkedList<Entry<K,V>>[]) java.lang.reflect.Array.newInstance(LinkedList.class, this.capacity);
    
    for (int i = 0; i < table.length; i++ ) {
      if (table[i] != null) {
        
        for (Entry <K,V> entry : table[i]) {
          int bucketIndex = normalizeIndex(entry.hash);
          LinkedList<Entry<K,V>> bucket = newTable[bucketIndex];
          if (bucket == null) newTable[bucketIndex] = bucket = new LinkedList<>();
          bucket.add(entry);
        }

        // Avoid memory leak. Help the GC
        table[i].clear();
        table[i] = null;

      }
    }

    table = newTable;

  }

  public K [] keys() {

    int index = 0;
    K [] keys = (K[]) new Object[size()];
    for(LinkedList<Entry<K,V>> bucket : table)
      if (bucket != null)
        for (Entry <K,V> entry : bucket)
          keys[index++] = entry.key;
    return keys;

  }

  public V [] values() {

    int index = 0;
    V [] values = (V[]) new Object[size()];
    for(LinkedList<Entry<K,V>> bucket : table)
      if (bucket != null)
        for (Entry <K,V> entry : bucket)
          values[index++] = entry.value;
    return values;

  }

  // Return an iterator to iterate over all the keys in this map
  @Override public java.util.Iterator <K> iterator() {
    final int elementCount = size();
    return new java.util.Iterator <K> () {
      
      int bucketIndex = 0;
      java.util.Iterator <Entry<K,V>> bucketIter = (table[0] == null) ? null : table[0].iterator();

      @Override public boolean hasNext() {
        
        // An item was added or removed while iterating
        if (elementCount != size) throw new java.util.ConcurrentModificationException();
        
        // No iterator or the current iterator is empty
        if (bucketIter == null || !bucketIter.hasNext()) {

          // Search next buckets until a valid iterator is found
          while(++bucketIndex < capacity) {
            if (table[bucketIndex] != null) {
              
              // Make sure this iterator actually has elements -_-
              java.util.Iterator <Entry<K,V>> nextIter = table[bucketIndex].iterator();
              if ( nextIter.hasNext() ) {
                bucketIter = nextIter;
                break;
              }

            }
          }
        
        }

        return bucketIndex < capacity;

      }
      @Override public K next() {
        return bucketIter.next().key;
      }
      @Override public void remove() {
        throw new UnsupportedOperationException();
      }      
    };
  }

  @Override public String toString() {

    StringBuilder sb = new StringBuilder();
    sb.append("{");
    for (int i = 0; i < capacity; i++) {
      if (table[i] == null) continue;
      for (Entry <K,V> entry : table[i])
        sb.append( entry + ", ");
    }
    sb.append("}");
    return sb.toString();

  }

}
