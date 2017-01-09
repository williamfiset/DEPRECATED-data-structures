
public class FenwickTree {

  private int sz;
  private long[] tree;

  public FenwickTree(int sz) {
    this.sz = sz;
    tree = new long[sz + 1];
  }

  // Get the sum of the index from 1 to 'i'
  public long sum(int i) {
    long sum = 0;
    while (i > 0) {
      sum += tree[i];
      i -= i & -i;
    }
    return sum;
  }

  // Sum up interval i to j in Fenwick Tree
  public long interval_sum(int i, int j) {
    return sum(j) - sum(i - 1);
  }

  // Add 'k' to index 'i'
  public void add(int i, long k) {
    while (i < tree.length) {
      tree[i] += k;
      i += i & -i;
    }
  }

}
