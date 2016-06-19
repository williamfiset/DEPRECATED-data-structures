
import java.util.Iterator;

interface ILinkedList <T> {

  public int getSize();
  public boolean isEmpty();

  public T removeHead();
  public T removeTail();

  public T peekFirst();
  public T peekLast();

  public void addFirst(T elem);
  public void addLast(T elem);

}

class Linked_List <T> implements ILinkedList <T>, Iterable <T> {
  
  private Node <T> head = null;
  private Node <T> tail = null;
  private int size = 0;

  private class Node <T> {
    T data;
    Node <T> prev, next;
    public Node(T data, Node <T> prev, Node <T> next) {
      this.data = data;
      this.prev = prev;
      this.next = next;
    }
  }

  public int getSize() {
    return size;
  }

  public boolean isEmpty() {
    return size == 0; 
  }

  public T removeHead() {

    if (isEmpty()) throw new RuntimeException("Empty list");
    
    T data = head.data;
    head = head.next;
    --size;

    if (head != null) head.prev = null;
    else tail = null;

    return data;

  }

  public T removeTail() {

    if (isEmpty()) throw new RuntimeException("Empty list");
    
    T data = tail.data;
    tail = tail.prev;
    --size;

    if (tail != null) tail.next = null;
    else head = null;

    return data;

  }

  public T peekFirst() {
    if (isEmpty()) throw new RuntimeException("Empty list");
    return head.data;
  }

  public T peekLast() {
    if (isEmpty()) throw new RuntimeException("Empty list");
    return tail.data;
  }

  public void addFirst(T elem) {
    if (head == null) {
      head = tail = new Node <T> ( elem, null, null );
    } else {
      head.prev = new Node <T> ( elem, null, head );
      head = head.prev;
    }
    size++;
  }

  public void addLast(T elem) {
    if (tail == null) {
      head = tail = new Node <T> ( elem, null, null );
    } else {
      tail.next = new Node <T> ( elem, tail, null );
      tail = tail.next;
    }
    size++;
  }

  @Override public Iterator <T> iterator () {
    Iterator <T> iter = new Iterator <T> () {
      private Node <T> trav = head;
      @Override public boolean hasNext() {
        return trav != null;
      }
      @Override public T next () {
        T data = trav.data;
        trav = trav.next;
        return data;
      }
    };
    return iter;
  }

}

public class Video02 {
    
    public static void main(String[] args) {
      


    }



}













