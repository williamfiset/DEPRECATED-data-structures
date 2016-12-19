
public interface IList <T> {

  public int size();
  public void clear();
  public void add(T elem);
  public boolean isEmpty();
  public int indexOf(Object obj);
  public T removeAt(int index);
  public boolean remove(Object obj);
  public boolean contains(Object obj);

}
