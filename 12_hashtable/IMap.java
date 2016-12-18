
public interface IMap <K, V> {

  public Array <K> keys();
  public Array <V> values();

  // These do the same thing
  public boolean hasKey(K key);
  public boolean containsKey(K key);
  
  // These do the same thing
  public void put(K key, V value);
  public void add(K key, V value);

  // public void inc(K key); // Should be for only for IntMap/LongMap

  public V get(K key);
  public V remove(K key);

  public int size();
  public void clear();
  public boolean isEmpty();

}
