
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
    // if (other == null) return false; // Entries should not be null
    if ( hash != other.hash ) return false;
    return key.equals( other.key );
  }

  @Override public String toString() {
    return key + " => " + value; 
  }

}

public class Mapping <K,V> implements IMap <K, V> { // also implement iterable

  private final double LOAD_FACTOR = 0.75;

  int num_keys = 0;
  int capacity, threshold, size = 0;
  Array <LinkedList<Entry<K,V>>> table;

  public Mapping () {
    this(3);
  }

  public Mapping (int capacity) {
    if (capacity < 0) throw new IllegalArgumentException("Capacity cannot be less than zero");
    this.capacity = Math.max(3, capacity);
    threshold = (int) (this.capacity * LOAD_FACTOR);
    table = new Array<>(capacity);
  }

  public Array <K> keys() {

    Array <K> keys = new Array<>(num_keys);
    for(LinkedList<Entry<K,V>> links : table)
      for (Entry <K,V> entry : links)
        keys.add(entry.key);
    return keys;

  }

  public Array <V> values() {
    return null;
  }

  // These do the same thing
  public boolean hasKey(K key) {
    return false;
  }

  public boolean containsKey(K key) {
    return hasKey(key);
  }
  
  public void put(K key, V value) {
    add(key, value);
  }

  public void add(K key, V value) {

  }

  // public void inc(K key) // Should be for only for IntMap/LongMap

  public V get(K key) {
    return null;
  }

  public V remove(K key) {
    return null;
  }

  public int size() {
    return 0;
  }

  public void clear() {
    
  }
  
  public boolean isEmpty() {
    return false;
  }  

}

