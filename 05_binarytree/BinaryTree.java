import java.util.Iterator;

interface IBinaryTree <T extends Comparable<T> > {

  public boolean isEmpty();
  public int getSize();
  public void add ( T elem );
  public void remove ( T elem );
  public boolean find (T elem);
  public int height();
  public Iterator traverse(TreeTraversalOrder order);

}

public class BinaryTree <T extends Comparable<T>> implements IBinaryTree <T> {
  
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
