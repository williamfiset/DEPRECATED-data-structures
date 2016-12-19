
public interface IStack <T> {

  public int size();
  public boolean isEmpty();
  public void push(T elem);
  public T pop();
  public T peek();

}