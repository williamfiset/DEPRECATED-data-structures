/**
 * The Size Balanced Tree (SBT) is a balanced binary search tree that uses the
 * size of subtrees to rebalance itself. 
 *
 *
 * See nice article about the SBT:
 * http://wcipeg.com/wiki/Size_Balanced_Tree#Insertion
 *
 * Read the excellent research paper in the research_paper/ folder.
 *
 * @author William Fiset, william.alexandre.fiset@gmail.com
 **/

public class SizeBalancedTree <T extends Comparable<T>> implements Iterable<T> {
  
  // Tracks the number of nodes in this tree.
  private int nodeCount = 0;

  // This is a rooted tree so we maintain a handle on the root node.
  private Node root = null;

  class Node implements TreePrinter.PrintableNode {

    T value;

    // The number of nodes below this node.
    int sz;

    Node left, right;

    public Node (T value) {
      this.value = value;
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
      return String.valueOf(value) + " (" + sz + ")";
    }

  }

  public void display() {
    TreePrinter.print(root);
  }

  // Check if the tree is empty.
  public boolean isEmpty() {
    return size() == 0;
  }

  // Get the number of nodes in this binary tree
  public int size() {
    return nodeCount;
  }

  // Add an element to this binary tree. Returns true
  // if we successfully perform an insertion
  public boolean add(T elem) {

    // Check if the value already exists in this
    // binary tree, if it does ignore adding it
    if (contains(elem)) {
      return false;

    // Otherwise add this element to the binary tree
    } else {
      // checkSize();
      root = add(root, elem);
      nodeCount++;
      return true;
    }

  }

  // Private method to recursively add a value in the binary tree
  private Node add(Node node, T elem) {

    // Base case: found a leaf node
    if (node == null) {
      node = new Node(elem);
    } else {
      
      node.sz++;

      // Pick a subtree to insert element
      if (elem.compareTo(node.value) < 0) {
        node.left = add(node.left, elem);
      } else {
        node.right = add(node.right, elem);
      }
    }

    // Re-balance
    return node;

  }

  // Returns the number of nodes below a certain node.
  private int sz(Node node) {
    if (node == null) return 0;
    return node.sz;
  }

  private Node rightRotate(Node node) {
    Node child = node.left;
    node.left = child.right;
    child.right = node;
    child.sz = sz(node);
    node.sz = sz(node.left) + sz(node.right) + 1;
    return child;
  }

  private Node leftRotate(Node node) {
    Node child = node.right;
    node.right = child.left;
    child.left = node;
    child.sz = sz(node);
    node.sz = sz(child.left) + sz(child.right) + 1;
    return child;
  }

  // Remove a value from this binary tree if it exists, O(n)
  public boolean remove(T elem) {

    // Make sure the node we want to remove 
    // actually exists before we remove it
    if (contains(elem)) {
      root = remove(root, elem);
      nodeCount--;
      return true;
    }
    return false;

  }

  private Node remove(Node node, T elem) {
    
    if (node == null) return null;
    
    int cmp = elem.compareTo(node.value);

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

      // This is the case with only a right subtree or 
      // no subtree at all. In this situation just
      // swap the node we wish to remove with its right child.
      if (node.left == null) {
        
        Node rightChild = node.right;
        
        node.value = null;
        node = null;

        return rightChild;
        
      // This is the case with only a left subtree or 
      // no subtree at all. In this situation just
      // swap the node we wish to remove with its left child.
      } else if (node.right == null) {

        Node leftChild = node.left;

        node.value = null;
        node = null;

        return leftChild;

      // When removing a node from a binary tree with two links the
      // successor of the node being removed can either be the largest
      // value in the left subtree or the smallest value in the right 
      // subtree. In this implementation I have decided to find the 
      // smallest value in the right subtree which can be found by 
      // traversing as far left as possible in the right subtree.
      } else {
        
        // Find the leftmost node in the right subtree
        Node tmp = findMin(node.right);

        // Swap the data
        node.value = tmp.value;

        // Go into the right subtree and remove the leftmost node we
        // found and swapped data with. This prevents us from having
        // two nodes in our tree with the same value.
        node.right = remove(node.right, tmp.value);
        
        // If instead we wanted to find the largest node in the left
        // subtree as opposed to smallest node in the right subtree 
        // here is what we would do:
        // Node tmp = findMax(node.left);
        // node.value = tmp.value;
        // node.left = remove(node.left, tmp.value);

      }

    }

    return node;

  }

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

  // Return true/false depending on whether a value exists in the tree.
  public boolean contains(T value) {
    
    Node node = root;

    if (node == null || value == null) return false;

    while(node != null) {

      // Compare current value to the value in the node.
      int cmp = value.compareTo(node.value);

      // Dig into left subtree.
      if (cmp < 0) node = node.left;

      // Dig into right subtree.
      else if (cmp > 0) node = node.right;

      // Found value in tree.
      else return true;
    }

    return false;
  }

  // TODO: Implement iterator. See other BST for examples.
  public java.util.Iterator<T> iterator() {
    return null;
  }

  public static void main(String[] args) {
    
    int[] values = {1,6,3,8,9,2,7,-5,5};
    SizeBalancedTree<Integer> tree = new SizeBalancedTree<>();

    for (int v : values) {
      tree.add(v);
      // System.out.println("========================================================================================================================");
    }
    tree.display();

  }

}

