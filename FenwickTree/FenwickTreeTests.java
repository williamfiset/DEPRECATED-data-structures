import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import java.util.*;

public class FenwickTreeTests {

  static final int MIN_RAND_NUM = -1000;
  static final int MAX_RAND_NUM = +1000;

  static final int TEST_SZ = 1000;
  static final int LOOPS = 1000;

  @Before
  public void setup() {
    
  }

  @Test
  public void test() {
    long[] ar = { 0,3,4,-2,7,3,11,5,-8,-9,2,4,-8 };
    FenwickTree ft = new FenwickTree(ar);
    // System.out.println(ft);
    // ft.add(9, 5);
    // ft.prefixSum(7);

  }

  @Test
  public void testIntervalSumPositiveValues() {
    
    // System.out.println("testIntervalSumPositiveValues");
    long[] ar = {0,1,2,3,4,5,6};
    FenwickTree ft = new FenwickTree(ar);
    
    assertEquals( 21, ft.interval_sum(1, 6) );
    assertEquals( 15, ft.interval_sum(1, 5) );
    assertEquals( 10, ft.interval_sum(1, 4) );
    assertEquals(  6, ft.interval_sum(1, 3) );
    assertEquals(  3, ft.interval_sum(1, 2) );
    assertEquals(  1, ft.interval_sum(1, 1) );
    assertEquals(  0, ft.interval_sum(1, 0) );

    assertEquals(  7, ft.interval_sum(3, 4) );
    assertEquals( 20, ft.interval_sum(2, 6) );
    assertEquals(  9, ft.interval_sum(4, 5) );

  }
  
  @Test
  public void testIntervalSumNegativeValues() {

    // System.out.println("testIntervalSumNegativeValues");
    long[] ar = {0,-1,-2,-3,-4,-5,-6};
    FenwickTree ft = new FenwickTree(ar);
    
    assertEquals( -21, ft.interval_sum(1, 6) );
    assertEquals( -15, ft.interval_sum(1, 5) );
    assertEquals( -10, ft.interval_sum(1, 4) );
    assertEquals(  -6, ft.interval_sum(1, 3) );
    assertEquals(  -3, ft.interval_sum(1, 2) );
    assertEquals(  -1, ft.interval_sum(1, 1) );
    assertEquals(   0, ft.interval_sum(1, 0) );

  }

  
  @Test
  public void testIntervalSumNegativeValues2() {

    // System.out.println("testIntervalSumNegativeValues2");
    long[] ar = {0, -76871, -164790};
    FenwickTree ft = new FenwickTree(ar);
    
    for(int i = 0; i < LOOPS;i++) {
      assertEquals( -76871,  ft.interval_sum(1, 1) );
      assertEquals( -76871,  ft.interval_sum(1, 1) );
      assertEquals( -241661, ft.interval_sum(1, 2) );
      assertEquals( -241661, ft.interval_sum(1, 2) );
      assertEquals( -241661, ft.interval_sum(1, 2) );
      assertEquals( -164790, ft.interval_sum(2, 2) );
      assertEquals( -164790, ft.interval_sum(2, 2) );
      assertEquals( -164790, ft.interval_sum(2, 2) );
    }

  }

  @Test
  public void testRandomizedStaticSumQueries() {

    // System.out.println("testRandomizedStaticSumQueries");
    for (int i = 1; i <= LOOPS; i++) {
      
      long [] randList = genRandList( i );
      FenwickTree ft = new FenwickTree(randList);

      for (int j = 0; j < LOOPS / 10; j++ ) {
        doRandomRangeQuery(randList, ft);
      }

    }

  }

  public void doRandomRangeQuery( long[] arr, FenwickTree ft ) {

    int N = arr.length;
    long sum = 0L;

    int lo = lowBound(N);
    int hi = highBound(lo, N);

    for(int k = lo; k <= hi; k++) sum += arr[k];

    assertEquals(sum, ft.interval_sum(lo, hi));

  }

  @Test
  public void testRandomizedSetSumQueries() {

    // System.out.println("testRandomizedSetSumQueries");
    for (int i = 2; i <= LOOPS; i++) {
      
      long [] randList = genRandList( i );
      FenwickTree ft = new FenwickTree(randList);

      for (int j = 0; j < LOOPS / 10; j++ ) {
        
        int index = 1 + ((int) Math.random() * i);
        long rand_val = randValue();
        
        randList[index] += rand_val;
        ft.add(index, rand_val);

        doRandomRangeQuery(randList, ft);

      }

    }

  }

  @Test
  public void testReusability() {

    int SIZE = 1000;
    FenwickTree ft = new FenwickTree(SIZE);
    long[] arr = new long[SIZE+1];

    for ( int loop = 0; loop < LOOPS ; loop++ ) {
      
      for(int i = 1; i <= SIZE; i++) {
        long val = randValue();
        ft.set(i, val);
        arr[i] = val;
      }
      doRandomRangeQuery( arr, ft );

    }

  }

  public static int lowBound(int N) {
    return 1 + ((int) Math.random() * N);
  }

  public static int highBound(int low, int N) {
    return Math.min(N-1, low + (int)(Math.random() * N) );    
  }

  public static long randValue() {
    return (long)(Math.random() * MAX_RAND_NUM*2) + MIN_RAND_NUM;    
  }

  @Test(expected=IllegalArgumentException.class)
  public void testIllegalCreation() {
    new FenwickTree(null);
  }

  // Generate a list of random numbers
  static long[] genRandList(int sz) {
    long[] lst = new long[sz];
    for (int i = 0; i < sz; i++) {
      lst[i] = randValue();
    }
    return lst;
  }

}

