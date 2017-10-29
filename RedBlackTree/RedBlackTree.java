/**
 * This file contains an implementation of a Red-Black tree. A RB tree
 * is a special type of binary tree which self balances itself to keep
 * operations logarithmic.
 *
 * Great visualization tool: 
 * https://www.cs.usfca.edu/~galles/visualization/RedBlack.html
 *
 * @author William Fiset, william.alexandre.fiset@gmail.com
 **/

public class RedBlackTree <T extends Comparable<T>> {

  static final boolean RED = true;
  static final boolean BLACK = false;

  class Node implements TreePrinter.PrintableNode {
    
    // The color of the node. By default all nodes start red.
    boolean color = RED;

    // The value/data contained within the node.
    T value;

    // The left, right and parent references for this node.    
    Node left, right, parent;

    public Node(T value, Node parent) {
      this.value = value;
      this.parent = parent;
    }

    @Override 
    public Node getLeft() {
      return left;
    }

    @Override
    public Node getRight() {
      return right;
    }

    @Override
    public String getText() {
      return String.valueOf(value) + "("+(color==RED?"r":"b")+")";
    }

    @Override
    public String toString() {
      return getText();
    }

  }

  // The root node of the AVL tree.
  Node root;

  // Tracks the number of nodes inside the tree.
  private int nodeCount = 0;

  // Returns the number of nodes in the tree.
  public int size() {
    return nodeCount;
  }

  // Returns whether or not the tree is empty.
  public boolean isEmpty() {
    return size() == 0;
  }

  // Prints a visual representation of the tree to the console.
  public void display() {
    TreePrinter.print(root);
  }

  private void swapColors(Node a, Node b) {
    boolean tmpColor = a.color;
    a.color = b.color;
    b.color = tmpColor;
  }

  // Return true/false depending on whether a value exists in the tree.
  public boolean contains(T value) {
    return contains(root, value);
  }

  // Recursive contains helper method.
  private boolean contains(Node node, T value) {
    
    if (node == null) return false;

    // Compare current value to the value in the node.
    int cmp = value.compareTo(node.value);

    // Dig into left subtree.
    if (cmp < 0) return contains(node.left, value);

    // Dig into right subtree.
    if (cmp > 0) return contains(node.right, value);

    // Found value in tree.
    return true;

  }

  public boolean insert(T value) {

    if (value == null) throw new IllegalArgumentException();

    // No root node.
    if (root == null) {
      root = new Node(value, null);
      insertionRelabel(root);
      return true;      
    }

    for(Node node = root;;) {

      int cmp = value.compareTo(node.value);

      // Left subtree.
      if (cmp < 0) {
        if (node.left == null) {
          node.left = new Node(value, node);
          insertionRelabel(node.left);
          return true;
        }
        node = node.left;

      // Right subtree.
      } else if (cmp > 0) {
        if (node.right == null) {
          node.right = new Node(value, node);
          insertionRelabel(node.right);
          return true;
        }
        node = node.right;

      // The value we're trying to insert already exists in the tree.
      } else return false;

    }

  }

  private void insertionRelabel(Node node) {

    Node parent = node.parent;

    // Root node case.
    if (parent == null) {
      node.color = BLACK;
      return;
    }

    Node grandParent = parent.parent;

    // Tree has a height of one, in which case the root is black
    // and the new node added is red, so everything is fine.
    if (grandParent == null) return;

    // The red-black tree invariant is already satisfied.
    if (parent.color == BLACK) return;

    boolean nodeIsLeftChild = (parent.left == node);
    boolean nodeIsRightChild = !nodeIsLeftChild;
    boolean parentIsLeftChild = (parent == grandParent.left);
    boolean parentIsRightChild = !parentIsLeftChild;

    Node uncle = parentIsLeftChild ? grandParent.right : grandParent.left;
    
    boolean uncleIsRedNode = (uncle == null) ? BLACK : uncle.color;

    if (uncleIsRedNode) {
      
      parent.color = BLACK;
      grandParent.color = RED;
      uncle.color = BLACK;

    } else {

      // System.out.println("HERE");

      // Left-left case.
      if (parentIsLeftChild && nodeIsLeftChild) {
        System.out.println("Left-left case.");
        grandParent = leftLeftCase(grandParent);

      // Left-right case.
      } else if (parentIsLeftChild && nodeIsRightChild) {
        
        System.out.println("Left-right case");
        grandParent = leftRightCase(grandParent);
        // System.out.println("GP: " + grandParent.getText());
        // System.out.println( (grandParent.left==null?"null":grandParent.left.getText()) + " - " + (grandParent.right==null?"null":grandParent.right.getText()));
        
      // Right-left case.
      } else if (parentIsRightChild && nodeIsLeftChild) {
        System.out.println("Right-left case.");
        grandParent = rightLeftCase(grandParent);

      // Right-right case.
      } else {
        System.out.println("Right-right case.");
        grandParent = rightRightCase(grandParent);
      }

      if (grandParent.parent == null) {
        root = grandParent;
      }

    }

    insertionRelabel(grandParent);
    
  }

  private Node leftLeftCase(Node node) {
    node = rightRotate(node);
    swapColors(node, node.right);
    return node;
  }

  private Node leftRightCase(Node node) {
    node.left = leftRotate(node.left);
    return leftLeftCase(node);
  }

  private Node rightRightCase(Node node) {
    node = leftRotate(node);
    swapColors(node, node.left);
    return node;
  }

  private Node rightLeftCase(Node node) {
    node.right = rightRotate(node.right);
    return rightRightCase(node);
  }

  private Node rightRotate(Node parent) {
    
    Node grandParent = parent.parent;
    Node child = parent.left;

    parent.left = child.right;
    child.right = parent;
    child.parent = grandParent;
    parent.parent = child;

    return child;
  }

  private Node leftRotate(Node parent) {

    Node grandParent = parent.parent;
    Node child = parent.right;

    parent.right = child.left;
    child.left = parent;
    child.parent = grandParent;
    parent.parent = child;
    
    return child;
  }

  // Sometimes the left or right child node of a parent changes and the
  // parent's reference needs to be updated to point to the new child. 
  // This is a helper method to do just that.
  // private void updateParentChildLink(Node parent, Node oldChild, Node newChild) {
  //   if (parent != null) {
  //     if (parent.left == oldChild) {
  //       parent.left = newChild;
  //     } else {
  //       parent.right = newChild;
  //     }
  //   }
  // }

  // Helper method to find the leftmost node (which has the smallest value)
  private Node findMin(Node node) {
    while(node.left != null) 
      node = node.left;
    return node;
  }

  // Helper method to find the rightmost node (which has the largest value)
  private Node findMax(Node node) {
    while(node.right != null) 
      node = node.right;
    return node;
  }

  // Returns as iterator to traverse the tree in order.
  public java.util.Iterator<T> iterator () {

    final int expectedNodeCount = nodeCount;
    final java.util.Stack<Node> stack = new java.util.Stack<>();
    stack.push(root);

    return new java.util.Iterator<T> () {
      Node trav = root;
      @Override 
      public boolean hasNext() {
        if (expectedNodeCount != nodeCount) throw new java.util.ConcurrentModificationException();        
        return root != null && !stack.isEmpty();
      }
      @Override 
      public T next () {
        
        if (expectedNodeCount != nodeCount) throw new java.util.ConcurrentModificationException();

        while(trav != null && trav.left != null) {
          stack.push(trav.left);
          trav = trav.left;
        }
        
        Node node = stack.pop();
        
        if (node.right != null) {
          stack.push(node.right);
          trav = node.right;
        }
        
        return node.value;
      }
      @Override 
      public void remove() {
        throw new UnsupportedOperationException();
      }      
    };
  }

  // Make sure all left child nodes are smaller in value than their parent and
  // make sure all right child nodes are greater in value than their parent.
  // (Used only for testing)
  // boolean validateBSTInvarient(Node node) {
  //   if (node == null) return true;
  //   T val = node.value;
  //   boolean isValid = true;
  //   if (node.left  != null) isValid = isValid && node.left.value.compareTo(val)  < 0;
  //   if (node.right != null) isValid = isValid && node.right.value.compareTo(val) > 0;
  //   return isValid && validateBSTInvarient(node.left) && validateBSTInvarient(node.right);
  // }

  public static void main(String[] args) {
    
    RedBlackTree<Integer> rbTree = new RedBlackTree<>();
    int[] values = {9,8,4,3,7,6,5,2,1,0};
    for (int v : values) {
      rbTree.insert(v);
      rbTree.display();
      System.out.println("------------------------------------------------------------------------------");
    }

  }

}
















