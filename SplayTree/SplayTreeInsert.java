public class SplayTree {
	Node root;

	// Function to insert a new key k in splay tree with given root
	public Node insert(Node root, int key) {
		// Simple Case: If tree is empty
		if (root == null)
			return new Node(key);
		// Bring the closest leaf node to root
		root = splay(root, key);

		// If key is already present, then return
		if (root.value == key)
			return root;
		// Otherwise create new node
		Node n = new Node(key);
		// If root's key is greater, make root as right child
		// of newnode and copy the left child of root to newnode
		if (root.value > key) {
			n.right = root;
			n.left = root.left;
			root.left = null;
		}

		// If root's key is smaller, make root as left child
		// of newnode and copy the right child of root to newnode
		else {
			n.left = root;
			n.right = root.right;
			root.right = null;
		}
		return n;// newnode becomes new root

	}

	// This function brings the key at root if key is present in tree.
	// If key is not present, then it brings the last accessed item at
	// root. This function modifies the tree and returns the new root
	public Node splay(Node root, int key) {
		// Base cases: root is NULL or key is present at root
		if (root == null || root.value == key)
			return root;

		// Key lies in left subtree
		if (root.value > key) {
			// Key is not in tree, we are done
			if (root.left == null)
				return root;

			// Left-Left case
			if (root.left.value > key) {
				// First recursively bring the key as root of left-left
				root.left.left = splay(root.left.left, key);
				// Do first rotation for root, second rotation is done after
				// else
				root = rightRotate(root);
			} else if (root.left.value < key) {// Letf-Right case
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
			if (root.right.value > key) {
				// Bring the key as root of right-left
				root.right.left = splay(root.right.left, key);

				// Do first rotation for root.right
				if (root.right.left != null)
					root.right = rightRotate(root.right);
			} else if (root.right.value < key) { // Right right case
				// Bring the key as root of right-right and do first
				// rotation
				root.right.right = splay(root.right.right, key);
				root = leftRotate(root);
			}
			// Do second rotation for root
			return (root.right == null) ? root : leftRotate(root);
		}
	}

	public Node rightRotate(Node root) {
		Node leftChild = root.left;
		Node leftChildsRight = leftChild.right;

		// Perform rotation
		leftChild.right = root;
		root.left = leftChildsRight;

		return leftChild;
	}

	public Node leftRotate(Node root) {
		Node rightChild = root.right;
		Node rightChildsLeft = rightChild.left;

		// Perform rotation
		rightChild.left = root;
		root.right = rightChildsLeft;

		return rightChild;
	}

	public void preOrder(Node root) {
		if (root != null) {
			System.out.println(root.value + " ");
			preOrder(root.left);
			preOrder(root.right);
		}
	}
}

public class Node {
	int value;
	Node left, right;

	public Node(int key) {
		value = key;
	}
}