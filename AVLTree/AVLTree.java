

public class AVLTree <T extends Comparable<T>> {

  static class Node <T extends Comparable<T>> {
    // Balance factor
    int bf;
    T value;
    int height;
    Node<T> left, right;
    public Node(T value, Node<T> left, Node<T> right) {
      if (value == null) throw new IllegalArgumentException();
      this.value = value;
      this.left = left;
      this.right = right;
      this.height = -1;
    }
  }

  Node<T> root;

  public void insert(T value) {
    root = insert(root, value);
  }

  private Node<T> insert(Node<T> node, T value) {
    
    // Base case: Hit null node
    if (node == null) {
      return new Node<T>(value, null, null);
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

    update(node);

    // Rebalance tree.
    return balance(node);

  }

  // Update height and balance factor.
  private void update(Node<T> node) {
    
    if (node == null) return;

    int leftNodeHeight  = (node.left  == null) ? -1 : node.left.height;
    int rightNodeHeight = (node.right == null) ? -1 : node.right.height;

    // Update this node's height.
    node.height = 1 + Math.max(leftNodeHeight, rightNodeHeight);

    // Update balance factor.
    node.bf = rightNodeHeight - leftNodeHeight;

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

  private Node<T> leftLeftCase(Node<T> node) {
    
  }

  private Node<T> leftRightCase(Node<T> node) {

  }

  private Node<T> rightLeftCase(Node<T> node) {
    
  }

  private Node<T> rightRightCase(Node<T> node) {
    
  }

  private Node<T> leftRotation(Node<T> node) {
    Node<T> newParent = node.right;
    node.right = newParent.left;
    newParent.left = node;
    update(node);
    update(newParent);
    return newParent;
  }

  private Node<T> rightRotation(Node<T> node) {
    Node<T> newParent = node.left;
    node.left = newParent.right;
    newParent.right = node;
    update(node);
    update(newParent);
    return newParent;
  }

}











