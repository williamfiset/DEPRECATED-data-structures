
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
    @Override public String toString() {
      return data.toString();
    }
  }

  public void clear() {
    Node <T> trav = head;
    while(trav != null) {
      Node <T> next = trav.next;
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

  public void add(T elem) {
    addLast(elem);
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


  public T peekFirst() {
    if (isEmpty()) throw new RuntimeException("Empty list");
    return head.data;
  }

  public T peekLast() {
    if (isEmpty()) throw new RuntimeException("Empty list");
    return tail.data;
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

  private T remove(Node <T> node) {

    if (node.prev == null) return removeFirst();
    if (node.next == null) return removeLast();

    node.next.prev = node.prev;
    node.prev.next = node.next;

    T data = node.data;
    node.data = null;
    node = node.prev = node.next = null;
    --size;

    return data;

  }

  public T removeAt(int index) {

    if (index < 0 || index >= size) throw new IllegalArgumentException();

    int i;
    Node <T> trav;

    // Search bottom up for element
    if (index < size/2) {
      for(i = 0, trav = head; i != index; i++ )
        trav = trav.next;

    // Search top down for element
    } else
      for(i = size-1, trav = tail; i != index; i--)
        trav = trav.prev;
  
    return remove(trav);

  }

  public boolean remove(Object obj) {
    
    Node <T> trav = head;
    
    // Support searching for null
    if (obj == null) {
      for(trav = head; trav != null; trav = trav.next)
        if (trav.data == null) {
          remove(trav);
          return true;
        }
        
    // Search for non null object
    } else {
      for(trav = head; trav != null; trav = trav.next)
        if (obj.equals(trav.data)) {
          remove(trav);
          return true;
        }
    }
    return false;
  }

  public int indexOf(Object obj) {
    
    int index = 0;
    Node <T> trav = head;
    
    // Support searching for null
    if (obj == null) {
      for(trav = head; trav != null; trav = trav.next, index++)
        if (trav.data == null)
          return index;

    // Search for non null object
    } else
      for(trav = head; trav != null; trav = trav.next, index++)
        if (obj.equals(trav.data))
          return index;
    
    return -1;
  }

  public boolean contains(Object obj) {
    return indexOf(obj) != -1;
  }

  @Override public java.util.Iterator <T> iterator () {
    return new java.util.Iterator <T> () {
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
  }

  @Override public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("[ ");
    Node <T> trav = head;
    while(trav != null) {
      sb.append(trav.data + ", ");
      trav = trav.next;
    }
    sb.append(" ]");
    return sb.toString();
  }

}
