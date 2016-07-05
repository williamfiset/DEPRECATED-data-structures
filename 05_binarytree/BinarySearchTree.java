import java.util.Iterator;

interface IBinarySearchTree <T extends Comparable<T> > {

  public boolean isEmpty();
  public int getSize();
  public void add ( T elem );
  public void remove ( T elem );
  public boolean find (T elem);
  public int height();
  public Iterator traverse(TreeTraversalOrder order);

}

public class BinarySearchTree <T extends Comparable<T>> implements IBinarySearchTree <T> {
  
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
    root = add(root, elem);
  }
  private Node add(Node node, T elem) {
    if (node == null) {
      node = new Node (null, null, elem);
    } else {
      if (node.data.compareTo(elem) <= 0) {
        node.left = add(node.left, elem);
      } else {
        node.right = add(node.right, elem);
      }
    }
    return node;
  }

  public void remove ( T elem ) {
    if (root != null) {
      nodeCount--;
      root = remove(root, elem);
    }
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
    return find(root, elem);
  }
  private boolean find(Node node, T elem) {
    
    // Reached bottom, value not found
    if (node == null) return false;

    int cmp = node.data.compareTo(elem);
    
    if (cmp == 0) return true;
    else if (cmp == 1) return find(node.right, elem);
    else return find(node.left, elem);

  }

  public int height() {
    return height(root);
  }
  private int height(Node node) {
    if (node == null) return 0;
    return Math.max( height(node.left), height(node.right) ) + 1;
  }

  private Iterator <T> preOrderTraversal () {
    return new Iterator <T> () {
      
      Stack <Node> stack = new Stack <>(root);

      @Override public boolean hasNext() {
        return !stack.isEmpty();
      }
      @Override public T next () {

        Node node = stack.pop();
        if (node.left != null) stack.push(node.left);
        if (node.right != null) stack.push(node.right);
        return node.data;

      }
    };
  }

  private Iterator <T> inOrderTraversal () {
    return new Iterator <T> () {
      
      Node trav = root;
      Stack <Node> stack = new Stack <>(root);
      // Queue <Node> queue = new Queue <>(root);

      @Override public boolean hasNext() {
        return !stack.isEmpty();
      }
      @Override public T next () {

        Node ret = null;
        while(trav != null && trav.left != null) {
          stack.push(trav.left);
          // queue.offer(trav.left);
          trav = trav.left;
        }
        
        // Node r = queue.poll();
        ret = stack.pop();
        
        if (ret.right != null) {
          stack.push(ret.right);
          // queue.offer(ret.right);
          trav = ret.right;
        }
        
        return ret.data;
        
        // Dig left to find smallest node
        // Node left_node = stack.pop();
        // while( left_node.left != null ) {
        //   stack.push(left_node);
        //   left_node = left_node.left;
        // }
        // Node right_node = left_node;
        // 
        // while( right_node.right != null ) {
        //   stack.push(right_node);
        //   right_node = right_node.right;
        // }
        // return left_node.data;
      }
    };
  }

  private Iterator <T> postOrderTraversal () {
    return new Iterator <T> () {
      
      Stack <Node> stack = new Stack <>(root);

      @Override public boolean hasNext() {
        return !stack.isEmpty();
      }
      @Override public T next () {
        Node node = stack.pop();
        if (node.right != null) stack.push(node.right);
        if (node.left  != null) stack.push(node.left);
        return node.data;
      }
    };
  }  

  private Iterator <T> levelOrderTraversal () {
    return new Iterator <T> () {
      
      Queue<Node> queue = new Queue<>(root);

      @Override public boolean hasNext() {
        return !queue.isEmpty();
      }
      @Override public T next () {

        Node node = queue.poll();
        if (node.right != null) queue.offer(node.right);
        if (node.left != null) queue.offer(node.left);
        return node.data;

      }
    };
  }

  // Should we allow the user to modify the values of the nodes as they 
  // are being traversed? Sounds like building a car as your driving it,
  // perhaps not the best idea. Checking for concurrent modification will
  // slow iteration slightly at the cost of safety if implemented.
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
