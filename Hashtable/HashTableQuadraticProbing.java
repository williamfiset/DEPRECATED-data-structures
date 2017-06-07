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

import java.util.*;

@SuppressWarnings("unchecked")
public class HashTableQuadraticProbing <K, V> implements Iterable <K> {

  private double loadFactor;
  private int capacity, threshold;

  // 'usedBuckets' counts the total number of used buckets inside the 
  // hash-table (includes cells marked as deleted). While 'keyCount; 
  // tracks the number of unique keys currently inside the hash-table.
  private int usedBuckets = 0, keyCount = 0;

  // These arrays store the key-value pairs. 
  private K [] keyTable;
  private V [] valueTable;

  // Special marker token used to indicate the deletion of a key-value pair
  private final K DELETED_KEY_TOKEN = (K) (new Object());

  private static final int DEFAULT_CAPACITY = 8;
  private static final double DEFAULT_LOAD_FACTOR = 0.45;

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
    this.capacity = Math.max(DEFAULT_CAPACITY, next2Power(capacity));
    threshold = (int) (this.capacity * loadFactor);

    keyTable   = (K[]) new Object[this.capacity];
    valueTable = (V[]) new Object[this.capacity];

  }
  
  // Given a number this method finds the next 
  // power of two above this value. 
  private static int next2Power(int n) {
    return Integer.highestOneBit(n) << 1;
  }
  
  // Quadratic probing function
  private static int f(int x) {
    return (x + x*x) >> 1;
  }
  
  // Converts a hash value to an index. Essentially, this strips the
  // negative sign and places the hash value in the domain [0, capacity)
  private int normalizeIndex(int keyHash ) {
    return (keyHash & 0x7fffffff) % capacity;
  }

  // Clears all the contents of the hash-table
  public void clear() {
    for (int i = 0; i < capacity; i++) {
      keyTable[i] = null;
      valueTable[i] = null;
    }
    keyCount = usedBuckets = 0;
  }

  // Returns the number of keys currently inside the hash-table
  public int size() {
    return keyCount;
  }
  
  // Returns true/false depending on whether the hash-table is empty
  public boolean isEmpty() {
    return keyCount == 0;
  }

  // Insert, put and add all place a value in the hash-table
  public V put(K key, V value) { return insert(key, value); }
  public V add(K key, V value) { return insert(key, value); }

  // Place a key-value pair into the hash-table. If the value already
  // exists inside the hash-table then the value is updated
  public V insert(K key, V val) {
    
    if (key == null) throw new IllegalArgumentException("Null key");
    if (usedBuckets >= threshold) resizeTable();
    
    int keyHash = normalizeIndex(key.hashCode());
    int i = keyHash, j = -1, x = 1;

    do {
      
      // The current slot was previously deleted
      if (keyTable[i] == DELETED_KEY_TOKEN) {

        if (j == -1) j = i;
      
      // The current cell already contains a key
      } else if (keyTable[i] != null) {

        // The key we're trying to insert already exists in the hash-table,
        // so update its value with the most recent value
        if (keyTable[i].equals(key)) {

          V oldValue = valueTable[i];
          if (j == -1) {
            valueTable[i] = val;
          } else {
            keyTable[i] = DELETED_KEY_TOKEN;
            valueTable[i] = null;
            keyTable[j] = key;
            valueTable[j] = val;
          }
          return oldValue;

        }

      // Current cell is null so an insertion/update can occur
      } else {

        // No previously encountered deleted buckets
        if (j == -1) {
          usedBuckets++; keyCount++;
          keyTable[i] = key;
          valueTable[i] = val;

        // Previously seen deleted bucket. Instead of inserting
        // the new element at i where the null element is insert
        // it where the deleted token was found.
        } else {
          keyCount++;
          keyTable[j] = key;
          valueTable[j] = val;
        }

        return null;

      }

      i = normalizeIndex(keyHash + f(x++));
     
    } while(true);

  }

  // Returns true/false on whether a given key exists within the hash-table
  public boolean containsKey(K key) {
    return hasKey(key);
  }

  // Returns true/false on whether a given key exists within the hash-table
  public boolean hasKey(K key) {

    if (key == null) throw new IllegalArgumentException("Null key");
    
    int keyHash = normalizeIndex(key.hashCode());
    int i = keyHash, j = -1, x = 1;

    // Starting at the original keyHash index quadratically probe until we find a spot where
    // our key is or we hit a null element in which case our element does not exist.
    do {

      // Ignore deleted cells, but record where the first index
      // of a deleted cell is found to perform lazy relocation later.
      if (keyTable[i] == DELETED_KEY_TOKEN) {
        
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

            // Clear the contents in bucket i and mark it as deleted
            keyTable[i] = DELETED_KEY_TOKEN;
            valueTable[i] = null;

          }

          return true;

        }
      
      // Key was not found in the hash-table :/
      } else return false;

      i = normalizeIndex(keyHash + f(x++));

    } while(true);

  }

  // Get the value associated with the input key.
  // NOTE: returns null if the value is null AND also returns
  // null if the key does not exists.
  public V get(K key) {
    
    if (key == null) throw new IllegalArgumentException("Null key");
    int keyHash = normalizeIndex(key.hashCode());
    int i = keyHash, j = -1, x = 1;

    // Starting at the original keyHash index quadratically probe until we find a spot where
    // our key is or we hit a null element in which case our element does not exist.
    do {

      // Ignore deleted cells, but record where the first index
      // of a deleted cell is found to perform lazy relocation later.
      if (keyTable[i] == DELETED_KEY_TOKEN) {
        
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

            // Clear the contents in bucket i and mark it as deleted
            keyTable[i] = DELETED_KEY_TOKEN;
            valueTable[i] = null;

            return valueTable[j];
          } else {
            return valueTable[i];
          }

        }
      
        // Element was not found in the hash-table :/
      } else return null;

      i = normalizeIndex(keyHash + f(x++));

    } while( true );
    
  }

  // Removes a key from the map and returns the value.
  // NOTE: returns null if the value is null AND also returns
  // null if the key does not exists.
  public V remove(K key) {
    
    if (key == null) throw new IllegalArgumentException("Null key");

    int keyHash = normalizeIndex(key.hashCode());
    int i = keyHash, x = 1;

    // Starting at the keyHash index quadratically probe until we find a spot where
    // our key is or we hit a null element in which case our element does not exist
    for (;; i = normalizeIndex(keyHash + f(x++)) ) {

      // Ignore deleted cells
      if (keyTable[i] == DELETED_KEY_TOKEN) continue;

      // Key was not found in hash-table.
      if (keyTable[i] == null) return null;

      // The key we want to remove is in the hash-table!
      if (keyTable[i].equals(key)) {
        keyCount--;
        V oldValue = valueTable[i];
        keyTable[i] = DELETED_KEY_TOKEN;
        valueTable[i] = null;
        return oldValue;
      }

    }

  }

  public List <K> keys() {
    List <K> keys = new ArrayList<>(size());
    for (int i = 0; i < capacity; i++)
      if (keyTable[i] != null && keyTable[i] != DELETED_KEY_TOKEN)
        keys.add(keyTable[i]);
    return keys;     
  }

  public List <V> values() {
    List <V> values = new ArrayList<>(size());
    for (int i = 0; i < capacity; i++)
      if (keyTable[i] != null && keyTable[i] != DELETED_KEY_TOKEN)
        values.add(valueTable[i]);
    return values;    
  }

  // Double the size of the hash-table
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

    // Reset the key count and buckets used since we are about to 
    // re-insert all the keys into the hash-table.
    keyCount = usedBuckets = 0;

    for (int i = 0; i < oldKeyTable.length; i++) {
      if (oldKeyTable[i] != null && oldKeyTable[i] != DELETED_KEY_TOKEN)
        insert(oldKeyTable[i], oldValueTable[i]);
      oldValueTable[i] = null;
      oldKeyTable[i] = null;
    }

  }

  @Override public java.util.Iterator <K> iterator() {

    // Before the iteration begins record the number of keys in 
    // the hash-table. This value should not change as we iterate
    // otherwise a concurrent modification has occurred.
    final int KEY_COUNT = keyCount;

    return new java.util.Iterator <K> () {

      int keysLeft = KEY_COUNT, index = 0;

      @Override public boolean hasNext() {

        // An item was added or removed while iterating
        if (KEY_COUNT != keyCount) throw new java.util.ConcurrentModificationException();
        return keysLeft != 0;

      }

      // Find the next element and return it
      @Override public K next() {
        while( keyTable[index] == null || keyTable[index] == DELETED_KEY_TOKEN) index++;
        keysLeft--;
        return keyTable[index++];
      }
      @Override public void remove() {
        throw new UnsupportedOperationException();
      }

    };

  }

  // Return a String version of this hash-table
  @Override public String toString() {

    StringBuilder sb = new StringBuilder();

    sb.append("{");
    for (int i = 0; i < capacity; i++)
      if (keyTable[i] != null && keyTable[i] != DELETED_KEY_TOKEN)
        sb.append( keyTable[i] + " => " + valueTable[i] + ", ");
    sb.append("}");

    return sb.toString();

  }


}




















