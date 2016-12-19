
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
    if (capacity <= 0 || load_factor <= 0 || load_factor > 1.0) throw new IllegalArgumentException();
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

    if (key == null) throw new IllegalArgumentException("Null key");
    Entry <K, V> new_entry = new Entry<>(key, value);
    int bucket_index = normalizeIndex(new_entry.hash);
    // System.out.println(bucket_index);
    bucketInsertEntry(bucket_index, new_entry);

  }

  public void put(K key, V value) {
    add(key, value);
  }

  public V get(K key) {

    if (key == null) return null;
    int bucket_index = normalizeIndex(key.hashCode());
    Entry <K, V> entry = bucketSeekEntry(bucket_index, key);
    if (entry != null) return entry.value;
    return null;

  }

  public V remove(K key) {

    if (key == null) return null;
    int bucket_index = normalizeIndex(key.hashCode());
    Entry <K, V> entry = bucketSeekEntry(bucket_index, key);
    // System.out.println("BUCKET: " + bucket_index);
    if (entry != null) {
      // System.out.println("FOUND ENTRY");
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
    // System.out.println("LINKS: " + links);
    if (links == null) {
      links = new LinkedList<>();
      table[bucket_index] = links;
    }
    if (bucketSeekEntry(bucket_index, entry.key) == null) {
      links.add(entry);
      ++size;
      if (size > threshold) resizeTable();
    }
  }

  private Entry <K, V> bucketSeekEntry(int bucket_index, K key) {

    if (key == null) return null;
    LinkedList <Entry<K,V>> links = table[bucket_index];
    if (links == null) return null;
    for (Entry <K,V> entry : links)
      if (entry.key.equals(key))
        return entry;
    return null;

  }

  private void resizeTable() {

    // System.out.println("TABLE RESIZE");
    // System.out.println(java.util.Arrays.toString(table));

    capacity *= 2;
    threshold = (int) (capacity * load_factor);
    LinkedList <Entry<K,V>> new_table [] = (LinkedList<Entry<K,V>>[]) java.lang.reflect.Array.newInstance(LinkedList.class, this.capacity);
    
    for (int i = 0; i < table.length; i++ ) {
      if (table[i] != null) {
        for (Entry <K,V> entry : table[i]) {
          // System.out.println("ENTRY: " + entry);
          int bucket_index = normalizeIndex(entry.hash);
          LinkedList<Entry<K,V>> links = new_table[bucket_index];
          if (links == null) {
            links = new LinkedList<>();
            // System.out.println("NEW BUCKET INDEX: " + bucket_index);
            new_table[bucket_index] = links;
          }
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

  // Test for concurrent modification error..
  @Override public java.util.Iterator <K> iterator() {
    int element_count = size();
    return new java.util.Iterator <K> () {
      
      int bucket_index = 0;
      // LinkedList <Entry<K,V>> bucket = null;
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
