
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
public class Mapping <K,V> implements IMap <K, V>, Iterable <K> {

  private static final int DEFAULT_CAPACITY = 3;
  private static final double DEFAULT_LOAD_FACTOR = 0.75;

  private double load_factor;
  private int capacity, threshold, size = 0;
  private LinkedList <Entry<K,V>> [] table;

  public Mapping () {
    this(DEFAULT_CAPACITY, DEFAULT_LOAD_FACTOR);
  }

  public Mapping (int capacity) {
    this(capacity, DEFAULT_LOAD_FACTOR);
  }

  // Designated constructor
  public Mapping (int capacity, double load_factor) {
    if (capacity <= 0)
      throw new IllegalArgumentException("Illegal capacity");
    if (load_factor <= 0 || Double.isNaN(load_factor) || Double.isInfinite(load_factor))
      throw new IllegalArgumentException("Illegal load_factor");
    this.load_factor = load_factor;
    this.capacity = Math.max(DEFAULT_CAPACITY, capacity);
    threshold = (int) (this.capacity * load_factor);
    table = (LinkedList<Entry<K,V>>[]) java.lang.reflect.Array.newInstance(LinkedList.class, this.capacity);
  }

  public int size() {
    return size;
  }
  
  public boolean isEmpty() {
    return size == 0;
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

  public boolean containsKey(K key) {
    return hasKey(key);
  }

  public boolean hasKey(K key) {
    int bucket_index = normalizeIndex(key.hashCode());
    return bucketSeekEntry(bucket_index, key) != null;
  }

  public V put(K key, V value) {
    return add(key, value);
  }

  public V add(K key, V value) {

    if (key == null) throw new IllegalArgumentException("Null key");
    Entry <K, V> new_entry = new Entry<>(key, value);
    int bucket_index = normalizeIndex(new_entry.hash);
    return bucketInsertEntry(bucket_index, new_entry);

  }

  // Gets a key's values from the map and returns the value.
  // NOTE: returns null if the value is null AND also returns
  // null if the key does not exists, so watch out..
  public V get(K key) {

    if (key == null) return null;
    int bucket_index = normalizeIndex(key.hashCode());
    Entry <K, V> entry = bucketSeekEntry(bucket_index, key);
    if (entry != null) return entry.value;
    return null;

  }

  // Removes a key from the map and returns the value.
  // NOTE: returns null if the value is null AND also returns
  // null if the key does not exists, so watch out..
  public V remove(K key) {

    if (key == null) return null;
    int bucket_index = normalizeIndex(key.hashCode());
    return bucketRemoveEntry(bucket_index, key);

  }

  // Removes an entry from a given bucket if it exists
  private V bucketRemoveEntry(int bucket_index, K key) {

    Entry <K, V> entry = bucketSeekEntry(bucket_index, key);
    if (entry != null) {
      LinkedList <Entry<K,V>> links = table[bucket_index];
      links.remove(entry);
      --size;
      return entry.value;
    } else return null;

  }

  // Inserts an entry in a given bucket only if the entry does not already
  // exist in the given bucket. If it does then the entry value if updated
  private V bucketInsertEntry(int bucket_index, Entry <K,V> entry) {
    
    LinkedList <Entry<K,V>> links = table[bucket_index];
    if (links == null) table[bucket_index] = links = new LinkedList<>();
    
    Entry <K, V> existantEntry = bucketSeekEntry(bucket_index, entry.key);
    if (existantEntry == null) {
      links.add(entry);
      if (++size > threshold) resizeTable();
    } else existantEntry.value = entry.value;

    return entry.value;
  }

  // Finds and returns a particular entry in a given bucket if it exists returns null otherwise
  private Entry <K, V> bucketSeekEntry(int bucket_index, K key) {

    if (key == null) return null;
    LinkedList <Entry<K,V>> links = table[bucket_index];
    if (links == null) return null;
    for (Entry <K,V> entry : links)
      if (entry.key.equals(key))
        return entry;
    return null;

  }

  // Resizes the internal table holding buckets of entries
  private void resizeTable() {

    capacity *= 2;
    threshold = (int) (capacity * load_factor);
    LinkedList <Entry<K,V>> new_table [] = (LinkedList<Entry<K,V>>[]) java.lang.reflect.Array.newInstance(LinkedList.class, this.capacity);
    
    for (int i = 0; i < table.length; i++ ) {
      if (table[i] != null) {
        for (Entry <K,V> entry : table[i]) {

          int bucket_index = normalizeIndex(entry.hash);
          LinkedList<Entry<K,V>> links = new_table[bucket_index];
          if (links == null) new_table[bucket_index] = links = new LinkedList<>();
          links.add(entry);

        }
        table[i] = null; // Avoid memory leak. Help the GC
      }
    }

    table = new_table;

  }

  public Array <K> keys() {

    Array <K> keys = new Array<>(size);
    for(LinkedList<Entry<K,V>> links : table)
      if (links != null)
        for (Entry <K,V> entry : links)
          keys.add(entry.key);
    return keys;

  }

  public Array <V> values() {

    Array <V> values = new Array<>(size);
    for(LinkedList<Entry<K,V>> links : table)
      if (links != null)
        for (Entry <K,V> entry : links)
          values.add(entry.value);
    return values;

  }

  @Override public java.util.Iterator <K> iterator() {
    int element_count = size();
    return new java.util.Iterator <K> () {
      
      int bucket_index = 0;
      java.util.Iterator <Entry<K,V>> bucket_iter = (table[0] == null) ? null : table[0].iterator();

      public boolean hasNext() {
        
        // An item was added or removed while iterating
        if (element_count != size) throw new java.util.ConcurrentModificationException();
        
        if (bucket_iter == null || !bucket_iter.hasNext()) {
          bucket_index++;
          while(bucket_index < capacity)
            if (table[bucket_index] != null) {
              bucket_iter = table[bucket_index].iterator();
              break;
            } else bucket_index++;
        }
        return bucket_index < capacity && bucket_iter.hasNext();

      }
      public K next() {
        return bucket_iter.next().key;
      }
    };
  }

  @Override public String toString() {

    StringBuilder sb = new StringBuilder();
    sb.append("[ ");
    for (int i = 0; i < capacity; i++) {
      if (table[i] == null) continue;
      for (Entry <K,V> entry : table[i])
        sb.append( entry + ", ");
    }
    sb.append(" ]");
    return sb.toString();

  }

}
