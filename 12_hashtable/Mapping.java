
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

public class Mapping <K,V> implements IMap <K, V> { // should also implement iterable

  private final double LOAD_FACTOR = 0.75;

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
    for (int i = 0; i < table.size(); i++)
      table.set(i, null);
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
    LinkedList <Entry<K,V>> links = table.get(bucket_index);
    links.remove(entry);
    --size;
  }

  private void bucketInsertEntry(int bucket_index, Entry <K,V> entry) {
    LinkedList<Entry<K,V>> links = table.get(bucket_index);
    if (links == null) {
      links = new LinkedList<>();
      table.set(bucket_index, links);
    }
    if (bucketSeekEntry(bucket_index, entry.key) == null) {
      links.add(entry);
      if (++size > threshold) resizeTable();
    }
  }

  private Entry <K, V> bucketSeekEntry(int bucket_index, K key) {
    LinkedList <Entry<K,V>> links = table.get(bucket_index);
    if (links == null) return null;
    for (Entry <K,V> entry : links)
      if (entry.key.equals(key))
        return entry;
    return null;
  }


  private void resizeTable() {
    
    capacity *= 2;
    // create new table
    // re distribute elements with new MOD value

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

    StringBuilder sb = StringBuilder();
    sb.append("[ ");
    for (int i = 0; i < size(); i++) {
      LinkedList<Entry<K,V>> links = table.get(i);
      for (Entry <K,V> entry : links) {
        sb.append( entry + ", ");
      }
    }
    sb.append(" ]");

  }

}
