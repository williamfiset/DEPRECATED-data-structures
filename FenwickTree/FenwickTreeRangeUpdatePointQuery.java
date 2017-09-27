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

    tree = ft.clone();
    cloneTree = ft.clone();

  }
  
  // TODO(williamfiset): Improve comment.
  // Update the left+1 and right+2 index in fenwick tree with the update value
  public void updateRange(int left, int right, long val) {
    // add(left  + 1, +val);
    // add(right + 2, -val);
    add(left, +val);
    add(right + 1, -val);
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
    index--;
    long sum1 = 0L, sum2 = 0L;
    int index1 = index, index2 = index + 1;
    
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
    long[] v = {23847239,1,2,3,4};
    // long[] v = {23847239,0,0,0,0,0,0,0,0,0,0,0};
    FenwickTreeRangeUpdatePointQuery ft = new FenwickTreeRangeUpdatePointQuery(v);
    ft.updateRange(3,3,+5);
    // ft.updateRange(3,3,+5);
    System.out.println(ft.getPoint(3));
    // System.out.println(ft.getPoint(1));
  }
  
}
