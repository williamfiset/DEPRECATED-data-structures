
public interface ISet <T> {

  public int size();
  public void clear();
  public boolean isEmpty();
  public boolean add(T elem);
  public boolean remove(T elem);
  public boolean contains(T elem);

}