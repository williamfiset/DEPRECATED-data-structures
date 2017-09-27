/**
 * A Fenwick Tree implementation which supports 
 * range updates and point queries
 * @author Braj65
 **/
 
public class FenwickTreeRangeUpdatePointQuery {

  // The size of the array holding the Fenwick tree values
  final int N;

  // This array contains the original Fenwick tree range
  // values from when it was first created.
  private long[] tree;

  // The clone tree will contain the updated range values
  private long[] cloneTree;

  // Construct a Fenwick tree with an initial set of values. 
  // The 'values' array is one based meaning values[0] 
  // does not get used, O(n) construction.
  public FenwickTreeRangeUpdatePointQuery(long[] values) {

    if (values == null)
      throw new IllegalArgumentException("Values array cannot be null!");
    
    values[0] = 0L;

    N = values.length;
    long[] ft = values.clone();
    
    for (int i = 1; i < N; i++) {
      int parent = i + lsb(i);
      if (parent < N) ft[parent] += ft[i];
    }

    cloneTree = ft.clone();
    tree = ft;

  }
  
  // Update the interval [left, right] with the value 'val', O(log(n))
  public void updateRange(int left, int right, long val) {
    add(left, +val);
    add(right + 1, -val);
  }

  // Add 'v' to index 'i' and all the ranges responsible for 'i', O(log(n))
  private void add(int i, long v) {
    while (i < N) {
      cloneTree[i] += v;
      i += lsb(i);
    }
  }

  // Get the value at a specific index. The logic behind this method is the 
  // same as finding the prefix sum in a Fenwick tree except that you need to 
  // take the difference between the current tree and the original to get 
  // the point value.
  public long getPoint(int i) {

    long sum1 = 0L, sum2 = 0L;
    int index1 = i - 1, index2 = i;
    
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

  public static void main(String[] args) {
    // long[] v = {534534,2,0,-7,34,5,2,-5};
    // long[] v = {23847239,1,2,3,4};
    long[] v = {23847239,0,0,0,0,0,0,0,0,0,0,0};
    FenwickTreeRangeUpdatePointQuery ft = new FenwickTreeRangeUpdatePointQuery(v);
    // ft.add(4, 10);
    ft.updateRange(3,5,+5);
    for (int i = 1; i < 8; i++) {
      System.out.println(i + " " + ft.getPoint(i));
    }
    // System.out.println(ft.getPoint(1));
  }
  
}
