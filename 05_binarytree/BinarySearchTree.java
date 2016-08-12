import java.util.Iterator;

interface IBinarySearchTree <T extends Comparable<T> > {

  public boolean isEmpty();
  public int getSize();
  public void add ( T elem );
  public void remove ( T elem );
  public boolean find (T elem);
  public int height();
  
  // Provide multiple iterators to traverse the tree
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

  // TODO: Make sure duplicate values are not allowed in BST
  public void add ( T elem ) {
    nodeCount++;
    root = add(root, elem);
  }
  private Node add(Node node, T elem) {
    if (node == null) {
      node = new Node (null, null, elem);
    } else {
      // Place lower elem values on left
      if ( elem.compareTo(node.data) < 0) {
        node.left = add(node.left, elem);
      } else {
        node.right = add(node.right, elem);
      }
    }
    return node;
  }

  // TODO: Fix remove method
  public void remove ( T elem ) {
    if (root != null) {
      nodeCount--; // This is wrong, what if elem doesn't exist in the tree
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

      // Do node swap.
      // When removing node from a binary tree with two links the succussor is the 
      // smallest node in the right subtree (the leftmost node in the right subtree)
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
        return root != null && !stack.isEmpty();
      }
      @Override public T next () {
        Node node = stack.pop();
        if (node.right != null) stack.push(node.right);
        if (node.left != null) stack.push(node.left);
        return node.data;
      }
    };
  }
  
  private Iterator <T> inOrderTraversal () {
    return new Iterator <T> () {
      
      Node trav = root;
      Stack <Node> stack = new Stack <>(root);

      @Override public boolean hasNext() {
        return root != null && !stack.isEmpty();
      }
      @Override public T next () {
        
        // Dig left
        while(trav != null && trav.left != null) {
          stack.push(trav.left);
          trav = trav.left;
        }
        
        Node node = stack.pop();
        
        // Try moving down right once
        if (node.right != null) {
          stack.push(node.right);
          trav = node.right;
        }
        
        return node.data;
      }
    };
  }

  private Iterator <T> postOrderTraversal () {
    Stack <Node> stack1 = new Stack <>(root);
    Stack <Node> stack2 = new Stack <>();
    while(!stack1.isEmpty()) {
      Node node = stack1.pop();
      if (node != null) {
        stack2.push(node);
        if (node.left != null)  stack1.push(node.left);
        if (node.right != null) stack1.push(node.right);
      }
    }
    return new Iterator <T> () {
      @Override public boolean hasNext() {
        return root != null && !stack2.isEmpty();
      }
      @Override public T next () {
        return stack2.pop().data;
      }
    };
  } 

  private Iterator <T> levelOrderTraversal () {
    return new Iterator <T> () {
      
      Queue <Node> queue = new Queue <>(root);

      @Override public boolean hasNext() {
        return root != null && !queue.isEmpty();
      }
      @Override public T next () {
        Node node = queue.poll();
        if (node.left != null) queue.offer(node.left);
        if (node.right != null) queue.offer(node.right);
        return node.data;
      }
    };
  }

  // Should we allow the user to modify the values of the nodes as they 
  // are being traversed? Sounds like it's perhaps not the best idea, however
  // checking for concurrent modification will cause slower iteration
  // at the cost of safety if implemented. Humm... 
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
