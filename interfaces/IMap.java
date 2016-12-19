
public interface IMap <K, V> {

  public Array <K> keys();
  public Array <V> values();

  public boolean hasKey(K key);
  public boolean containsKey(K key);
  
  public V put(K key, V value);
  public V add(K key, V value);

  public V get(K key);
  public V remove(K key);

  public int size();
  public void clear();
  public boolean isEmpty();

}
