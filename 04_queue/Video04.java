
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

interface IQueue <T> {
  public int getSize();
  public boolean isEmpty();
  public T peek();
  public T poll();
  public void offer(T elem);
}

class Queue <T> implements IQueue <T>, Iterable <T> {

  private Linked_List <T> lst = new Linked_List <T> ();;

  public Queue () { }

  public Queue (T firstElem) {
    offer(firstElem);
  }

  public int getSize() {
    return lst.getSize();
  }

  public boolean isEmpty() {
    return getSize() == 0;
  }

  public T peek() {
    if (isEmpty()) 
      throw new RuntimeException("Queue Empty");
    return lst.peekFirst();
  }

  public T poll() {
    if (isEmpty()) 
      throw new RuntimeException("Queue Empty");
    return lst.removeHead();    
  }

  public void offer(T elem) {
    lst.addLast(elem);
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

public class Video04 {
  public static void main(String[] args) {

    Queue <Integer> q = new Queue<>();
    q.offer(1);
    q.offer(2);
    q.offer(3);
    q.offer(4);

    System.out.println(q.peek());
    System.out.println( q.poll() );
    System.out.println(q.peek());
    System.out.println( q.poll() );

    // for (int num : q) {
    //   System.out.println(num);
    // }
    
    // Demo doing a BFS?

  }
}












