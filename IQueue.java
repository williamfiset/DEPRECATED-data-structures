
public interface IQueue <T> {

  public int size();
  public boolean isEmpty();
  public T peek();
  public T poll();
  public void offer(T elem);

}
