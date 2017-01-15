import java.util.*;
public class T {

  static int n = 5;
  static long[] arr = new long[n]; // Array storing the values
  static long[] ft = new long[n + 1]; // The Fenwick tree

  //Add v to arr[p]
  static void update(int p, long v) {
    for (; p <= arr.length; p += p & (-p))
      ft[p] += v;
  }

  //Add v to arr[a...b] 
  static void update(int a, int b, long v) {
    update(a, v);
    update(b + 1, -v);
  }

  // Return arr[b]     
  static long query(int b) {
    long sum = 0;
    for (; b > 0; b -= b & (-b))
      sum += ft[b];
    return sum + arr[b];
  }

  public static void main(String[] args) {
    // for (int i = 1; i <= n; i++) update(i, 5);
    update(1, n, 50L);
    System.out.println(Arrays.toString(ft));
    System.out.println( query(n) );
    // for (int i = 1; i <= n; i++) update(i, 5);
    System.out.println(Arrays.toString(ft));
    System.out.println( query(n) );
  }
}