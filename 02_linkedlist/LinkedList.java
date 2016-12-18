import java.util.Iterator;

// This probably shouldn't be an interface
// The linked list should inherit from the IList interface
// interface ILinkedList <T> {

//   public int size();
//   public boolean isEmpty();

//   public T removeFirst();
//   public T removeLast();

//   public T peekFirst();
//   public T peekLast();

//   public void addFirst(T elem);
//   public void addLast(T elem);

// }

public class LinkedList <T> implements IList <T>, Iterable <T> {
  
  private int size = 0;
  private Node <T> head = null;
  private Node <T> tail = null;

  private class Node <T> {
    T data;
    Node <T> prev, next;
    public Node(T data, Node <T> prev, Node <T> next) {
      this.data = data;
      this.prev = prev;
      this.next = next;
    }
  }

  public void clear() {
    Node trav = head;
    while(trav != null) {
      Node next = trav.next;
      trav.prev = trav.next = null;
      trav.data = null;
      trav = next;
    }
    head = tail = trav = null;
    size = 0;
  }

  public int size() {
    return size;
  }

  public boolean isEmpty() {
    return size() == 0; 
  }

  public T removeFirst() {

    if (isEmpty()) throw new RuntimeException("Empty list");
    
    T data = head.data;
    head = head.next;
    --size;

    if (head != null) head.prev = null;
    else tail = null;

    return data;

  }

  public T removeLast() {

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

  public void add(T elem) {
    addFirst(elem);
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

  public int indexOf(Object obj) {
    int index = 0;
    Node trav = head;
    while(trav != null) {
      Node next = trav.next;
      if ( (trav.data == obj) || trav.data.equals(obj))
        return index;
      trav = next;
      index++;
    }
    return -1;
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
