
public class BinarySearchTree <T extends Comparable<T>> {
  
  private int nodeCount = 0;
  private Node root = null;

  // Internal node
  private class Node {
    T data;
    Node left, right;
    public Node (Node left, Node right, T elem) {
      this.data = elem;
      this.left = left;
      this.right = right;
    }
  }

  // Check if this binary tree is empty
  public boolean isEmpty() {
    return size() == 0;
  }

  // Get the number of nodes in this binary tree
  public int size() {
    return nodeCount;
  }

  // Add an element to this binary tree
  public boolean add(T elem) {

    // Check if the value already exists in this
    // binary tree, if it does ignore adding it
    if (contains(elem)) {
      return false;

    // Otherwise add this element to the binary tree
    } else {
      nodeCount++;
      root = add(root, elem);
      return true;
    }

  }

  // Private method to recursively add a value in the binary tree
  private Node add(Node node, T elem) {

    // Base case: found a leaf node
    if (node == null) {
      node = new Node (null, null, elem);

    } else {
      // Place lower elem values on left
      if (elem.compareTo(node.data) < 0) {
        node.left = add(node.left, elem);
      } else {
        node.right = add(node.right, elem);
      }
    }

    return node;

  }

  public boolean remove(T elem) {
    if (contains(elem)) {
      nodeCount--;
      root = remove(root, elem);
      return true;
    }
    return false;
  }

  private Node remove(Node node, T elem) {
    
    if (node == null) return null;
    
    int cmp = elem.compareTo(node.data);

    // Dig into left subtree, the value we're looking
    // for is smaller than the current value
    if (cmp < 0) {
      node.left = remove(node.left, elem);
    
    // Dig into right subtree, the value we're looking
    // for is greater than the current value
    } else if (cmp > 0) {
      node.right = remove(node.right, elem);

    // Found the node we wish to remove
    } else {

      // Node with only one child or no child
      if (node.left == null) return node.right;
      else if (node.right == null) return node.left;

      // Do node swap.
      // When removing a node from a binary tree with two links 
      // the successor of the node being removed is the 
      // smallest node in the leftmost node in the right subtree.
      Node tmp = digLeft(node.right);
      node.data = tmp.data;
      node.right = remove(node.right, tmp.data);

    }

    return node;

  }

  // Helper method for remove.
  private Node digLeft(Node node) {
    Node cur = node;
    while(cur.left != null) 
      cur = cur.left;
    return cur;
  }

  // returns true is the element exists in the tree
  public boolean contains(T elem) {
    return contains(root, elem);
  }
  
  // private recursive method to find an element in the tree
  private boolean contains(Node node, T elem) {
    
    // Base case: reached bottom, value not found
    if (node == null) return false;

    int cmp = elem.compareTo(node.data);
    
    // We found the value we were looking for
    if (cmp == 0) return true;

    // Dig into the left subtree because the value we're
    // looking for is smaller than the current value
    else if (cmp < 0) return contains(node.left, elem);

    // Dig into the right subtree because the value we're
    // looking for is greater than the current value
    else return contains(node.right, elem);

  }

  // Computes the height of the tree, O(n)
  public int height() {
    return height(root);
  }

  // Recursive helper method of compute the height of the tree
  private int height(Node node) {
    if (node == null) return 0;
    return Math.max( height(node.left), height(node.right) ) + 1;
  }

  // This method returns an iterator for a given TreeTraversalOrder
  // TODO: Check for concurrent modification
  public java.util.Iterator <T> traverse(TreeTraversalOrder order) {
    switch (order) {
      case PRE_ORDER: return preOrderTraversal();
      case IN_ORDER: return inOrderTraversal();
      case POST_ORDER: return postOrderTraversal();
      case LEVEL_ORDER: return levelOrderTraversal();
      default: return null;
    }
  }

  // Returns as iterator to traverse the tree in pre order
  private java.util.Iterator <T> preOrderTraversal () {
    
    java.util.Stack <Node> stack = new java.util.Stack<>();
    stack.push(root);
    
    return new java.util.Iterator <T> () {
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

  // Returns as iterator to traverse the tree in order
  private java.util.Iterator <T> inOrderTraversal () {

    java.util.Stack <Node> stack = new java.util.Stack <>();
    stack.push(root);

    return new java.util.Iterator <T> () {
      Node trav = root;
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

  // Returns as iterator to traverse the tree in post order
  private java.util.Iterator <T> postOrderTraversal () {
    java.util.Stack <Node> stack1 = new java.util.Stack <>();
    java.util.Stack <Node> stack2 = new java.util.Stack <>();
    stack1.push(root);
    while(!stack1.isEmpty()) {
      Node node = stack1.pop();
      if (node != null) {
        stack2.push(node);
        if (node.left != null)  stack1.push(node.left);
        if (node.right != null) stack1.push(node.right);
      }
    }
    return new java.util.Iterator <T> () {
      @Override public boolean hasNext() {
        return root != null && !stack2.isEmpty();
      }
      @Override public T next () {
        return stack2.pop().data;
      }
    };
  } 

  // Returns as iterator to traverse the tree in level order  
  private java.util.Iterator <T> levelOrderTraversal () {

    java.util.Queue <Node> queue = new java.util.LinkedList<>();
    queue.offer(root);

    return new java.util.Iterator <T> () {
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

}
