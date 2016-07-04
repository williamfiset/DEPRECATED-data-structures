import java.util.Iterator;

interface IQueue <T> {
  public int getSize();
  public boolean isEmpty();
  public T peek();
  public T poll();
  public void offer(T elem);
}

public class Queue <T> implements IQueue <T>, Iterable <T> {

  private LinkedList <T> list = new LinkedList <T> ();

  public Queue() { }

  public Queue(T firstElem) {
    offer(firstElem);
  }

  public int getSize() {
    return list.getSize();
  }

  public boolean isEmpty() {
    return getSize() == 0;
  }

  public T peek() {
    if (isEmpty()) 
      throw new RuntimeException("Queue Empty");
    return list.peekFirst();
  }

  public T poll() {
    if (isEmpty()) 
      throw new RuntimeException("Queue Empty");
    return list.removeFirst();    
  }

  public void offer(T elem) {
    list.addLast(elem);
  }

  @Override public Iterator <T> iterator () {
    return new Iterator <T> () {
      @Override public boolean hasNext() {
        return !isEmpty();
      }
      @Override public T next () {
        return poll();
      }
    };
  }

}
