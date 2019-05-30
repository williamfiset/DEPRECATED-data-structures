
public class SplayTree <T extends Comparable<T>> {

  private Node<T> root;

  private class Node<T> {
    T value;
    Node<T> left, right;

    public Node(T key) {
      if (key == null) throw new IllegalArgumentException();
      value = key;
    }
  }

  public void insert(T key) {
  	insert(root, key);
  }

  // Function to insert a new key k in splay tree with given root
  private Node<T> insert(Node<T> root, T key) {
    
    // Tree is empty.
    if (root == null) return new Node<T>(key);

    // Bring the closest leaf node to root
    root = splay(root, key);

    // If key is already present, then return
    if (root.value == key) return root;

    // Otherwise create new node
    Node<T> n = new Node<T>(key);
    
    // If root's key is greater, make root as right child
    // of newnode and copy the left child of root to newnode
    if (root.value.compareTo(key) > 0) {
      n.right = root;
      n.left = root.left;
      root.left = null;

    // If root's key is smaller, make root as left child
    // of newnode and copy the right child of root to newnode
    } else {
      n.left = root;
      n.right = root.right;
      root.right = null;
    }
    return n; // newnode becomes new root

  }

  // Return whether a key exists within a tree
  public boolean contains(T key) {
  	root = splay(root, key);
  	if (root == null) return false;
  	return java.util.Objects.equals(root.value, key);
  }

  // This function brings the key at root if key is present in tree.
  // If key is not present, then it brings the last accessed item at
  // root. This function modifies the tree and returns the new root
  private Node<T> splay(Node<T> root, T key) {

    // Base cases: root is NULL or key is present at root
    if (root == null || root.value.compareTo(key) == 0)
      return root;

    // Key lies in left subtree
    if (root.value.compareTo(key) > 0) {
      // Key is not in tree, we are done
      if (root.left == null)
        return root;

      // Left-Left case
      if (root.left.value.compareTo(key) > 0) {
        // First recursively bring the key as root of left-left
        root.left.left = splay(root.left.left, key);
        // Do first rotation for root, second rotation is done after
        // else
        root = rightRotate(root);
      } else if (root.left.value.compareTo(key) < 0) {// Left-Right case
        // First recursively bring the key as root of left-right
        root.left.right = splay(root.left.right, key);
        // Do first rotation for root->left
        if (root.left.right != null)
          root.left = leftRotate(root.left);
      }
      // DO second rotation for root
      return (root.left == null) ? root : rightRotate(root);
    } else {// Key lies in right subtree
        // Key is not in tree, we are done
      if (root.right == null)
        return root;

      // Right left
      if (root.right.value.compareTo(key) > 0) {
        // Bring the key as root of right-left
        root.right.left = splay(root.right.left, key);

        // Do first rotation for root.right
        if (root.right.left != null)
          root.right = rightRotate(root.right);
      } else if (root.right.value.compareTo(key) < 0) { // Right right case
        // Bring the key as root of right-right and do first
        // rotation
        root.right.right = splay(root.right.right, key);
        root = leftRotate(root);
      }
      // Do second rotation for root
      return (root.right == null) ? root : leftRotate(root);
    }
  }

  private Node<T> rightRotate(Node<T> root) {
    Node<T> leftChild = root.left;
    Node<T> leftChildsRight = leftChild.right;

    // Perform rotation
    leftChild.right = root;
    root.left = leftChildsRight;

    return leftChild;
  }

  private Node<T> leftRotate(Node<T> root) {
    Node<T> rightChild = root.right;
    Node<T> rightChildsLeft = rightChild.left;

    // Perform rotation
    rightChild.left = root;
    root.right = rightChildsLeft;

    return rightChild;
  }

  public void displayTree() {
  	preOrder(root);
  }
  public boolean isEmpty(){
   return root==null;
  }
		
  public int size_totalKeys(Node<T> root){
   if(root==null)
     return 0;
   return 1+(size_totalKeys(root.left)+size_totalKeys(root.right));
  }
  public int height(Node<T> root){
    if(root==null)
      return 0;
    return 1+Math.max(height(root.left), height(root.right));
 }

  private void preOrder(Node<T> root) {
    if (root != null) {
      System.out.println(root.value + " ");
      preOrder(root.left);
      preOrder(root.right);
    }
  }

}
