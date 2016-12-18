import java.util.Iterator;
import java.util.EmptyStackException;

interface IStack <T> {

  public int size();
  public boolean isEmpty();
  public void push(T elem);
  public T pop();
  public T peek();

}

public class Stack <T> implements IStack <T>, Iterable <T> {
  
  private LinkedList <T> list = new LinkedList <T>();

  public Stack () { }

  public Stack (T firstElem) {
    push(firstElem);
  }

  public int size() {
    return list.size();
  }

  public boolean isEmpty() {
    return size() == 0;
  }

  public void push(T elem) {
    list.addLast(elem);
  }

  public T pop() {
    if (isEmpty())
      throw new EmptyStackException();
    return list.removeLast();
  }

  public T peek() {
    if (isEmpty()) 
      throw new EmptyStackException();
    return list.peekLast();
  }

  @Override public Iterator <T> iterator () {
    return new Iterator <T> () {
      @Override public boolean hasNext() {
        return !isEmpty();
      }
      @Override public T next () {
        return pop();
      }
    };
  }

}
