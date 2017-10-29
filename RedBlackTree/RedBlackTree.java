/**
 * This file contains an implementation of an AVL tree. An AVL tree
 * is a special type of binary tree which self balances itself to keep
 * operations logarithmic.
 *
 * @author William Fiset, william.alexandre.fiset@gmail.com
 **/

public class RedBlackTree <T extends Comparable<T>> {

  class Node implements TreePrinter.PrintableNode {
    
    // 'bf' is short for Balance Factor
    int bf;

    // The value/data contained within the node.
    T value;

    // The height of this node in the tree.
    int height;

    // The left and the right children of this node.    
    Node left, right;

    public Node(T value, Node left, Node right) {
      this.value = value;
      this.left = left;
      this.right = right;
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
      return String.valueOf(value);
    }

  }

  // The root node of the AVL tree.
  Node root;

  // Tracks the number of nodes inside the tree.
  private int nodeCount = 0;

  // Special token value used as an alternative to returning 'null'.
  // The TOKEN is used to indicate special return value signals. For example, 
  // we can return the TOKEN instead of null when we're inserting a new item
  // and discover the value we were inserting already exists in the tree.
  private Node TOKEN = new Node(null, null, null);

  // The height of a rooted tree is the number of edges between the tree's
  // root and its furthest leaf. This means that a tree containing a single 
  // node has a height of 0.
  public int height() {
    if (root == null) return 0;
    return root.height;
  }

  // Returns the number of nodes in the tree.
  public int size() {
    return nodeCount;
  }

  // Returns whether or not the tree is empty.
  public boolean isEmpty() {
    return size() == 0;
  }

  // Prints a visual representation of the tree to the console.
  // public void display() {
  //   TreePrinter.print(root);
  // }

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

  // Helper method to find the leftmost node (which has the smallest value)
  // private Node findMin(Node node) {
  //   while(node.left != null) 
  //     node = node.left;
  //   return node;
  // }

  // Helper method to find the rightmost node (which has the largest value)
  // private Node findMax(Node node) {
  //   while(node.right != null) 
  //     node = node.right;
  //   return node;
  // }

  // Returns as iterator to traverse the tree in order.
  // public java.util.Iterator<T> iterator () {

  //   final int expectedNodeCount = nodeCount;
  //   final java.util.Stack<Node> stack = new java.util.Stack<>();
  //   stack.push(root);

  //   return new java.util.Iterator<T> () {
  //     Node trav = root;
  //     @Override 
  //     public boolean hasNext() {
  //       if (expectedNodeCount != nodeCount) throw new java.util.ConcurrentModificationException();        
  //       return root != null && !stack.isEmpty();
  //     }
  //     @Override 
  //     public T next () {
        
  //       if (expectedNodeCount != nodeCount) throw new java.util.ConcurrentModificationException();

  //       while(trav != null && trav.left != null) {
  //         stack.push(trav.left);
  //         trav = trav.left;
  //       }
        
  //       Node node = stack.pop();
        
  //       if (node.right != null) {
  //         stack.push(node.right);
  //         trav = node.right;
  //       }
        
  //       return node.value;
  //     }
  //     @Override 
  //     public void remove() {
  //       throw new UnsupportedOperationException();
  //     }      
  //   };
  // }

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
    
  }

}
















