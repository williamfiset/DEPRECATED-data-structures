

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

/*##################################################################################*/
/*##################################################################################*/
/*##################################################################################*/


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

/*##################################################################################*/
/*##################################################################################*/
/*##################################################################################*/

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

/*##################################################################################*/
/*##################################################################################*/
/*##################################################################################*/

enum TreeTraversalOrder {
  PRE_ORDER,
  IN_ORDER,
  POST_ORDER,
  LEVEL_ORDER
}

interface IBinaryTree <T extends Comparable<T> > {

  public boolean isEmpty();
  public int getSize();
  public void add ( T elem );
  public void remove ( T elem );
  public boolean find (T elem);
  public int height();
  public Iterator traverse(TreeTraversalOrder order);

}


class BinaryTree <T extends Comparable<T>> implements IBinaryTree <T> {
  
  private Node root = null;
  private int nodeCount = 0;

  private class Node {
    T data;
    Node left, right;
    public Node (Node left, Node right, T elem) {
      this.data = elem;
      this.left = left;
      this.right = right;
    }
  }

  public boolean isEmpty() {
    return getSize() == 0;
  }

  public int getSize() {
    return nodeCount;
  }

  public void add ( T elem ) {
    nodeCount++;
    root = _add(root, elem);
  }
  private Node _add(Node node, T elem) {
    if (node == null) {
      node = new Node (null, null, elem);
    } else {
      if (node.data.compareTo(elem) <= 0) {
        node.left = _add(node.left, elem);
      } else {
        node.right = _add(node.right, elem);
      }
    }
    return node;
  }

  public void remove ( T elem ) {
    nodeCount--;
    root = remove(root, elem);
  }
  private Node remove(Node node, T elem) {
    
    if (node == null) return null;
    
    // Dig into left subtree
    if ( node.data.compareTo(elem) < 0 ) {
      node.left = remove(node.left, elem);
    
    // Dig into right subtree
    } else if (node.data.compareTo(elem) > 0) {
      node.right = remove(node.right, elem);

    } else {

      // Node with only one child or no child
      if (node.left == null) {
        return node.right;
      } else if (node.right == null) {
        return node.left;
      }

      // Do node swap
      Node tmp = findMinValueNode(node.right);
      node.data = tmp.data;
      node.right = remove(node.right, tmp.data);

    }
    return node;

  }

  private Node findMinValueNode(Node node) {
    Node cur = node;
    while(cur.left != null) 
      cur = cur.left;
    return cur;
  }


  public boolean find (T elem) {
    return _find(root, elem);
  }
  private boolean _find(Node node, T elem) {
    
    // Reached bottom, value not found
    if (node == null) return false;

    int cmp = node.data.compareTo(elem);
    
    if (cmp == 0) return true;
    else if (cmp == 1) return _find(node.right, elem);
    else return _find(node.left, elem);

  }

  public int height() {
    return _height(root);
  }
  private int _height(Node node) {
    if (node == null) return 0;
    return Math.max( _height(node.left), _height(node.right) ) + 1;
  }

  private Iterator <T> preOrderTraversal () {
    return new Iterator <T> () {
      
      Node trav = root;
      Stack<Node> stack = new Stack<>(root);

      @Override public boolean hasNext() {
        return trav != null;
      }
      @Override public T next () {

        Node node = stack.pop();

        if (node.left != null) stack.push(node.left);
        if (node.right != null) stack.push(node.right);

        // Done traversal
        if (stack.isEmpty()) 
          trav = null;
        else 
          trav = node;

        return node.data;

      }
    };
  }

  private Iterator <T> inOrderTraversal () {
    return null; // Let listener implement
  }

  private Iterator <T> postOrderTraversal () {
    return null; // Let listener implement
  }  

  private Iterator <T> levelOrderTraversal () {
    return new Iterator <T> () {
      
      Node trav = root;
      Queue<Node> queue = new Queue<>(root);

      @Override public boolean hasNext() {
        return trav != null;
      }
      @Override public T next () {

        Node node = queue.poll();

        if (node.right != null) queue.offer(node.right);
        if (node.left != null) queue.offer(node.left);

        // Done traversal
        if (queue.isEmpty()) 
          trav = null;
        else 
          trav = node;

        return node.data;

      }
    };
  }

  @Override public Iterator <T> traverse(TreeTraversalOrder order) {
    switch (order) {
      case PRE_ORDER: return preOrderTraversal();
      case IN_ORDER: return inOrderTraversal();
      case POST_ORDER: return postOrderTraversal();
      case LEVEL_ORDER: return levelOrderTraversal();
      default: return null;
    }
  }

}

public class Video05 {
  public static void main(String[] args) {
    
    BinaryTree<Integer> tree = new BinaryTree<>();
    tree.add(3);
    tree.add(5);
    tree.add(1);
    tree.add(4);
    tree.add(2);
    tree.add(6);

    tree.remove(6);
    tree.remove(3);
    tree.remove(1);
    tree.remove(5);
    tree.remove(5);
    tree.remove(2);
    tree.remove(4);


    Iterator <Integer> iter = tree.traverse(TreeTraversalOrder.LEVEL_ORDER);
    
    while (iter.hasNext()) {
      Integer n = iter.next();
      System.out.println(n);
    }


  }
}


