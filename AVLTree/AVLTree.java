

public class AVLTree <T extends Comparable<T>> {

  static class Node <T extends Comparable<T>> {
    // Balance factor
    int bf;
    int height;
    T value;
    Node<T> left, right;
    public Node(T value, Node<T> left, Node<T> right, int height) {
      if (value == null) throw new IllegalArgumentException();
      this.value = value;
      this.left = left;
      this.right = right;
      this.height = height;
    }
  }

  Node<T> root;

  public void insert(T value) {
    root = insert(root, value);
  }

  private Node<T> insert(Node<T> node, T value) {
    
    // Base case: Hit null node
    if (node == null) {
      return new Node<T>(value, null, null, 0);
    }

    // Compare current value to the value in the node.
    int cmp = node.value.compareTo(value);

    // Dig into left subtree.
    if (cmp < 0) {
      node.left = insert(node.left, value);

    // Dig into right subtree
    } else if (cmp > 0) {
      node.right = insert(node.right, value);

    // Duplicate value in tree.
    } else {
      System.out.println("Duplicate value :0");
      return node;
    }

    int leftNodeHeight  = (node.left  == null ? 0) : node.left.height;
    int rightNodeHeight = (node.right == null ? 0) : node.right.height;

    // Update this node's height.
    node.height = 1 + Math.max(leftNodeHeight, rightNodeHeight);

    // Update balance factor.
    node.bf = rightNodeHeight - leftNodeHeight;

    // Rebalance tree.
    return balance(node);

  }

  private Node<T> balance(Node<T> node) {

    // Left heavy subtree.
    if (node.bf == +2) {

      // Left-Right case.
      if (node.left.bf == -1) {
        return leftRightCase(node);
        
      // Left-Left case.
      } else if (node.left.bf == +1) {
        return leftLeftCase(node);
      }

      // what about bf = 0?
    
    // Right heavy subtree needs balancing.
    } else if (node.bf == -2) {

      // Right-Left case.
      if (node.right.bf == +1) {
        return rightLeftCase(node);

      // Right-Right case.
      } else if (node.right.bf == -1) {
        return rightRightCase(node);
      }

      // what about bf = 0?

    }

    // Node either has a balance factor of 0, +1 or -1 which is fine.
    return node;

  }

  private void leftLeftCase(Node<T> node) {
    
  }

  private void leftRightCase(Node<T> node) {

  }

  private void rightLeftCase(Node<T> node) {
    
  }

  private void rightRightCase(Node<T> node) {
    
  }

}











