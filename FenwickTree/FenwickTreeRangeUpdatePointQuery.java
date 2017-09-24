public class FenwickTreeRangeUpdatePointQuery {

  // The size of the array holding the Fenwick tree values
  final int N;

  // This array contains the Fenwick tree ranges in original form when creation
  private long[] tree;

  // The clone tree will contain the upgraded range values
  private long[] cloneTree;

  // Make sure the 'values' array is one based meaning
  // values[0] does not get used, O(n) construction
  public FenwickTreeRangeUpdatePointQuery(long[] values) {

    if (values == null)
      throw new IllegalArgumentException("Values array cannot be null!");

    N = values.length + 1;

    tree = new long[N];
    cloneTree = new long[N];

    long[] feniTree = new long[N];

    for(int i = 1, j = 0; i < feniTree.length; i++) {
      long val = values[i-1];
      j = i;
      while(j < N) {
        feniTree[j] += val;
        j += lsb(j);
      }
    }

    tree = feniTree.clone();
    cloneTree = feniTree.clone();

  }

  // Update the left+1 and right+2 index in fenwick tree with the update value
  public void updateRange(int left, int right, long val) {
    add(left + 1, val);
    add(right + 2, -val);
  }

  // Add 'v' to index 'i', O(log(n))
  // Updated cloneTree object with the range updates. Object tree still has the original
  // Fenwick tree
  public void add(int i, long v) {
    while (i < N) {
      cloneTree[i] += v;
      i += lsb(i);
    }
  }

  // To get the point/index value, sum from starting of array till the point
  // and return.
  // Fhe logic behind it is same as to find a prefix sum in a Fenwick tree
  //Taking out prefix sum for both cloneTree and tree. Subtracting them to get the updated value.
  public long getPoint(int index) {

    int sum1 = 0, sum2=0;
    int index1 = index, index2 = index;

    index2 += 1;
    while (index2 > 0) {
      sum1 += cloneTree[index2];
      index2 -= lsb(index2);
    }
    while(index1 > 0) {
      sum2 += tree[index1];
      index1 -= lsb(index1);
    }

    return sum1 - sum2;
  }

  // Returns the value of the least significant bit (LSB)
  // lsb(108) = lsb(0b1101100) = 0b100 = 4
  // lsb(104) = lsb(0b1101000) = 0b1000 = 8
  // lsb(96)  = lsb(0b1100000) = 0b100000 = 32
  // lsb(64)  = lsb(0b1000000) = 0b1000000 = 64
  private static int lsb(int i) {

    // Isolates the lowest one bit value
    return i & -i;

    // An alternative method is to use the Java's built in method
    // return Integer.lowestOneBit(i);

  }
}
