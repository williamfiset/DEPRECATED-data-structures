
import java.util.Iterator;
import java.util.EmptyStackException;

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

interface IStack <T> {

  public int getSize();
  public boolean isEmpty();
  public void push(T elem);
  public T pop();
  public T peek();

}

class Stack <T> implements IStack <T>, Iterable <T> {
  
  private Linked_List <T> lst = new Linked_List <T>();;

  public Stack () { }

  public Stack (T firstElem) {
    push(firstElem);
  }

  public int getSize() {
    return lst.getSize();
  }

  public boolean isEmpty() {
    return getSize() == 0;
  }

  public void push(T elem) {
    lst.addLast(elem);
  }

  public T pop() {
    if (isEmpty())
      throw new EmptyStackException();
    return lst.removeTail();
  }

  public T peek() {
    if (isEmpty()) 
      throw new EmptyStackException();
    return lst.peekLast();
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

public class Video03 {
  public static void main(String[] args) {
    
    Stack <String> stack = new Stack<>();

    stack.push("Hello");
    stack.push("World");
    stack.push("my");
    stack.push("name");
    stack.push("is");
    stack.push("Will");

    System.out.println(stack.getSize());
    System.out.println(stack.isEmpty());

    for (String w : stack) {
      System.out.println(w);
    }

  }
}


















