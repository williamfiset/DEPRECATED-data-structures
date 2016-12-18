
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

@SuppressWarnings("unchecked")
public class Mapping <K,V> implements IMap <K, V> { // should also implement iterable

  private static final int DEFAULT_CAPACITY = 3;
  private static final double DEFAULT_LOAD_FACTOR = 0.75;

  double load_factor;
  int capacity, threshold, size = 0;
  LinkedList <Entry<K,V>> [] table;

  public Mapping () {
    this(DEFAULT_CAPACITY, DEFAULT_LOAD_FACTOR);
  }

  public Mapping (int capacity) {
    this(capacity, DEFAULT_LOAD_FACTOR);
  }

  // Designated constructor
  public Mapping (int capacity, double load_factor) {
    if (capacity < 0) throw new IllegalArgumentException("Capacity cannot be less than zero");
    this.load_factor = load_factor;
    this.capacity = Math.max(DEFAULT_CAPACITY, capacity);
    threshold = (int) (this.capacity * load_factor);
    table = (LinkedList<Entry<K,V>>[]) java.lang.reflect.Array.newInstance(LinkedList.class, this.capacity);
  }

  public int size() {
    return size;
  }
  
  public boolean isEmpty() {
    return size() == 0;
  }

  // Converts a hash value to an index.
  private int normalizeIndex(int keyHash ) {
    return (keyHash & 0x7fffffff) % capacity;
  }

  public void clear() {
    for (int i = 0; i < capacity; i++)
      table[i] = null;
    size = 0;
  }

  public boolean hasKey(K key) {
    int bucket_index = normalizeIndex(key.hashCode());
    return bucketSeekEntry(bucket_index, key) != null;
  }

  public boolean containsKey(K key) {
    return hasKey(key);
  }
  
  public void add(K key, V value) {
    Entry <K, V> new_entry = new Entry<>(key, value);
    int bucket_index = normalizeIndex(new_entry.hash);
    bucketInsertEntry(bucket_index, new_entry);
  }

  public void put(K key, V value) {
    add(key, value);
  }

  public V get(K key) {
    int bucket_index = normalizeIndex(key.hashCode());
    Entry <K, V> entry = bucketSeekEntry(bucket_index, key);
    if (entry != null) return entry.value;
    return null;
  }

  public V remove(K key) {

    int bucket_index = normalizeIndex(key.hashCode());
    Entry <K, V> entry = bucketSeekEntry(bucket_index, key);
    
    if (entry != null) {
      bucketRemoveEntry(bucket_index, entry);
      return entry.value;
    }

    return null;

  }

  private void bucketRemoveEntry(int bucket_index, Entry <K,V> entry) {
    LinkedList <Entry<K,V>> links = table[bucket_index];
    links.remove(entry);
    --size;
  }

  private void bucketInsertEntry(int bucket_index, Entry <K,V> entry) {
    LinkedList<Entry<K,V>> links = table[bucket_index];
    if (links == null)
      table[bucket_index] = links = new LinkedList<>();
    if (bucketSeekEntry(bucket_index, entry.key) == null) {
      links.add(entry);
      if (++size > threshold) resizeTable();
    }
  }

  private Entry <K, V> bucketSeekEntry(int bucket_index, K key) {
    LinkedList <Entry<K,V>> links = table[bucket_index];
    if (links == null) return null;
    for (Entry <K,V> entry : links)
      if (entry.key.equals(key))
        return entry;
    return null;
  }

  private void resizeTable() {

    capacity *= 2;
    threshold = (int) (capacity * load_factor);
    LinkedList <Entry<K,V>> new_table [] = (LinkedList<Entry<K,V>>[]) java.lang.reflect.Array.newInstance(LinkedList.class, this.capacity);
    
    for (int i = 0; i < table.length; i++ ) {
      for (Entry <K,V> entry : table[i]) {

        int bucket_index = normalizeIndex(entry.hash);
        LinkedList<Entry<K,V>> links = new_table[bucket_index];
        if (links == null) new_table[bucket_index] = links = new LinkedList<>();
        links.add(entry);

      }
      table[i] = null; // Avoid memory leak. Help the GC
    }

    table = new_table;

  }

  public Array <K> keys() {

    Array <K> keys = new Array<>(size);
    for(LinkedList<Entry<K,V>> links : table)
      for (Entry <K,V> entry : links)
        keys.add(entry.key);
    return keys;

  }

  public Array <V> values() {

    Array <V> values = new Array<>(size);
    for(LinkedList<Entry<K,V>> links : table)
      for (Entry <K,V> entry : links)
        values.add(entry.value);
    return values;

  }

  @Override public String toString() {

    StringBuilder sb = new StringBuilder();
    sb.append("[ ");
    for (int i = 0; i < size(); i++) {
      LinkedList<Entry<K,V>> links = table[i];
      for (Entry <K,V> entry : links) {
        sb.append( entry + ", ");
      }
    }
    sb.append(" ]");
    return sb.toString();

  }

}
