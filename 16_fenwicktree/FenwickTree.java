
public class FenwickTree {

  private long[] tree;

  // Create an empty Fenwick Tree
  public FenwickTree(int sz) {
    tree = new long[sz + 1];
  }

  // Make sure the numbers in 'values' are one based
  // meaning values[0] does not get used
  public FenwickTree(long[] values) {

    if (values == null)
      throw new NullPointerException("Values array cannot be null!");

    this.tree = values.clone();

    for (int i = 1; i < tree.length; i++) {
      int j = i + lsb(i);
      if (j < tree.length) tree[j] += tree[i];
    }

  }

  // Returns the value of the least significant bit (LSB)
  // lsb(108) = lsb(0b1101100) =     0b100 = 4
  // lsb(104) = lsb(0b1101000) =    0b1000 = 8
  // lsb(96)  = lsb(0b1100000) =  0b100000 = 32
  // lsb(64)  = lsb(0b1000000) = 0b1000000 = 64
  private int lsb(int i) {
    return i & -i;
  }

  // Computes the prefix sum up to index i, one based
  public long prefixSum(int i) {
    long sum = 0;
    while (i > 0) {
      sum += tree[i];
      i -= lsb(i);
    }
    return sum;
  }

  // Returns the sum of the interval [i, j), one based
  public long interval_sum(int i, int j) {
    return prefixSum(j - 1) - prefixSum(i - 1);
  }

  // Add 'k' to index 'i', one based
  public void add(int i, long k) {
    while (i < tree.length) {
      tree[i] += k;
      i += lsb(i);
    }
  }

  // Set index 'i' to be equal to 'k', one based
  public void set(int i, long k) {
    long value = interval_sum(i, i+1);
    add( i, k - value );
  }

  @Override public String toString() {
    return java.util.Arrays.toString(tree);
  }

}
