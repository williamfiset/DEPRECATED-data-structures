
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

  // Converts a hash value to an index.
  private int normalizeIndex(int keyHash ) {
    return (keyHash & 0x7fffffff) % capacity;
  }

  public Array <K> keys() {

    Array <K> keys = new Array<>(size);
    for(LinkedList<Entry<K,V>> links : table)
      for (Entry <K,V> entry : links)
        keys.add(entry.key);
    return keys;

  }

  public Array <V> values() {

    Array <K> values = new Array<>(size);
    for(LinkedList<Entry<K,V>> links : table)
      for (Entry <K,V> entry : links)
        values.add(entry.value);
    return values;

  }

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
    Entry <K, V> new_entry = new Entry<>(key, value);
    int index = normalizeIndex(new_entry.hash);
    insertAt(index, new_entry);
  }

  private void insertAt(int index, Entry <K,V> entry) {
    LinkedList<Entry<K,V>> links = table.get(index);
    if (links == null) {
      links = new LinkedList<>();
      links.add(entry);
      table.set(index, links);
    } else {
      if (!containsAt(index))
        links.add(entry);
    }
  }

  private int containsAt(int index, Entry <K, V> entry ) {
    LinkedList<Entry<K,V>> links = table.get(index);
    if (links == null) return -1;
    for (int i = 0; i < links.size(); i++)
      if (links.get(i).equals(entry))
        return i;
    return -1;
  }

  public V get(K key) {
    int index = normalizeIndex(key.hashCode());

  }

  public V remove(K key) {
    int index = normalizeIndex(key.hashCode());
    return null;
  }

  public int size() {
    return size;
  }

  public void clear() {
    for (int i = 0; i < table.size(); i++)
      table.set(i, null);
    size = 0;
  }
  
  public boolean isEmpty() {
    return size() == 0;
  }  

}

