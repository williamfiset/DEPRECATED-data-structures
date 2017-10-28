
public class AVLTree <T extends Comparable<T>> {

  static class Node <T extends Comparable<T>> implements TreePrinter.PrintableNode {
    // Balance factor
    int bf;
    T value;
    int height;
    Node<T> left, right;
    public Node(T value, Node<T> left, Node<T> right) {
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
      return value+""; // + "("+bf+","+height+")";
    }
  }

  Node<T> root;
  int size = 0;

  public boolean contains(T value) {
    return contains(root, value);
  }

  private boolean contains(Node<T> node, T value) {
    
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

  public void insert(T value) {
    if (value == null) throw new IllegalArgumentException();
    if (!contains(value)) {
      root = insert(root, value);
      size++;
    }
  }

  private Node<T> insert(Node<T> node, T value) {
    
    // Base case.
    if (node == null) return new Node<T>(value, null, null);

    // Compare current value to the value in the node.
    int cmp = value.compareTo(node.value);

    // Dig into left subtree.
    if (cmp < 0) {
      node.left = insert(node.left, value);

    // Dig into right subtree
    } else if (cmp > 0) {
      node.right = insert(node.right, value);

    // Duplicate value in tree.
    } else return node;

    update(node);

    // Re-balance tree.
    return balance(node);

  }

  // Update a node's height and balance factor.
  private void update(Node<T> node) {
    
    // Q: Will this every be null?
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
    if (node.bf == -2) {

      // Left-Left case.
      if (node.left.bf <= 0) {
        return leftLeftCase(node);
        
      // Left-Right case.
      } else {
        return leftRightCase(node);
      }

    // Right heavy subtree needs balancing.
    } else if (node.bf == +2) {

      // Right-Right case.
      if (node.right.bf >= 0) {
        return rightRightCase(node);

      // Right-Left case.
      } else {
        return rightLeftCase(node);
      }

    }

    // Node either has a balance factor of 0, +1 or -1 which is fine.
    return node;

  }

  private Node<T> leftLeftCase(Node<T> node) {
    return rightRotation(node);
  }

  private Node<T> leftRightCase(Node<T> node) {
    node.left = leftRotation(node.left);
    return leftLeftCase(node);
  }

  private Node<T> rightRightCase(Node<T> node) {
    return leftRotation(node);
  }

  private Node<T> rightLeftCase(Node<T> node) {
    node.right = rightRotation(node.right);
    return rightRightCase(node);
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

  // The height of a rooted tree is the number of edges between the tree's
  // root and its furthest leaf. This means that a tree containing a single 
  // node has a height of 0.
  public int height() {
    if (root == null) return 0;
    return root.height;
  }

  public int size() {
    return size;
  }

  public boolean isEmpty() {
    return size() == 0;
  }

  public void display() {
    TreePrinter.print(root);
  }

  public static void main(String[] args) {
    AVLTree<Integer> tree = new AVLTree<>();
    tree.insert(1);    
    tree.insert(2);    
    tree.insert(3);    
    tree.insert(44);    
    tree.insert(-4);    
    tree.insert(6);    
    tree.insert(-8);    
    tree.insert(-12);    
    tree.insert(-8);    
    tree.insert(-9);    
    tree.insert(-10);    
    tree.insert(300);    
    tree.insert(43);    
    tree.insert(9);    
    tree.insert(43);    
    tree.display();
  }

}
















