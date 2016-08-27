
// UnionFind/Disjoint Set Data-structure.
// 
// This code is an inspired modification of the union find implementation found in 
// 'Algorithms Fourth Edition' by Robert Sedgewick and Kevin Wayne.

public class UnionFind {

  private int[] sz;
  private int[] id;
  private int numComponents;

  public UnionFind(int size) {
    sz = new int[size];
    id = new int[size];
    numComponents = size;
    for(int i = 0; i < size; i++) {
      id[i] = i; // Link to itself (self root)
      sz[i] = 1; // Each component is of size one originally
    }
  }

  public int find(int p) {
    
    // Find the root of the component/set
    int root = p;
    while( root != id[root] ) 
      root = id[root];

    // Path compression
    while(p != root) {
      int next = id[p];
      id[p] = root;
      p = next;
    }

    return root;

  }

  // Return whether or not the elements 'p' and
  // 'q' are in the same components/set.
  public boolean connected(int p, int q) {
    return find(p) == find(q);
  }

  // Return the size of the components/set 'p' belongs to
  public int getSize(int p) {
    return sz[find(p)];
  }

  // Returns the number of remaining components/sets 
  public int numberOfComponents() {
    return numComponents;
  }

  // Unify the components/sets containing elements 'p' and 'q'
  public void union(int p, int q) {

    int root1 = find(p);
    int root2 = find(q);

    // These elements are already in the same group!
    if (root1 == root2) return;

    // Merge two components/sets together
    if (sz[root1] < sz[root2]) {
      sz[root2] += sz[root1];
      id[root1] = root2;
    } else {
      sz[root1] += sz[root2];
      id[root2] = root1;
    }

    // Since the roots found are different we know that the
    // number of components/sets has decreased by one
    numComponents--;

  }

}
