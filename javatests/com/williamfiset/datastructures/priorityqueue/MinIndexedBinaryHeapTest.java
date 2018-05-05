package javatests.com.williamfiset.datastructures.priorityqueue;

import static com.google.common.truth.Truth.assertThat;

import com.williamfiset.datastructures.priorityqueue.MinIndexedBinaryHeap;

import org.junit.*;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;

public class MinIndexedBinaryHeapTest {

  private MinIndexedBinaryHeap pq;

  @Before
  public void setup() {}

  @Test(expected=IllegalArgumentException.class)
  public void testIllegalSizeOfNegativeOne() {
    new MinIndexedBinaryHeap<String>(-1);
  }

  @Test(expected=IllegalArgumentException.class)
  public void testIllegalSizeOfZero() {
    new MinIndexedBinaryHeap<String>(0);
  }

  @Test
  public void testLegalSize() {
    new MinIndexedBinaryHeap<String>(1);
  }

  @Test
  public void testContainsValidKey() {
    pq = new MinIndexedBinaryHeap<String>(10);
    pq.insert(5, "abcdef");
    assertThat(pq.contains(5)).isTrue();
  }

  @Test
  public void testContainsInvalidKey() {
    pq = new MinIndexedBinaryHeap<String>(10);
    pq.insert(5, "abcdef");
    assertThat(pq.contains(3)).isFalse();
  }

  @Test(expected=IllegalArgumentException.class)
  public void testDuplicateKeys() {
    pq = new MinIndexedBinaryHeap<String>(10);
    pq.insert(5, "abcdef");
    pq.insert(5, "xyz");
  }

  @Test
  public void testUpdateKeyValue() {
    pq = new MinIndexedBinaryHeap<String>(10);
    pq.insert(5, "abcdef");
    pq.update(5, "xyz");
    assertThat(pq.valueOf(5)).isEqualTo("xyz");
  }

  @Test
  public void testUpdateValueToRoot() {
    // test updating a vlaue to the smallest value puts it
  }

  @Test
  public void testTestDecreaseKey() {
    pq = new MinIndexedBinaryHeap<Integer>(10);
    pq.insert(3, 5);
    pq.decrease(3, 4);
    assertThat(pq.valueOf(3)).isEqualTo(4);
  }

  @Test
  public void testTestDecreaseKeyNoUpdate() {
    pq = new MinIndexedBinaryHeap<Integer>(10);
    pq.insert(3, 5);
    pq.decrease(3, 6);
    assertThat(pq.valueOf(3)).isEqualTo(5);
  }

  @Test
  public void testTestIncreaseKey() {
    pq = new MinIndexedBinaryHeap<Integer>(10);
    pq.insert(3, 5);
    pq.increase(3, 6);
    assertThat(pq.valueOf(3)).isEqualTo(6);
  }

  @Test
  public void testTestIncreaseKeyNoUpdate() {
    pq = new MinIndexedBinaryHeap<Integer>(10);
    pq.insert(3, 5);
    pq.increase(3, 4);
    assertThat(pq.valueOf(3)).isEqualTo(5);
  }

  @Test
  public void testPeekAndPollMinIndex() {
    // pairs[i][0] is the index
    // pairs[i][1] is the value
    Integer[][] pairs = {
      {4, 1},
      {7, 5},
      {1, 6},
      {5, 8},
      {3, 7},
      {6, 9},
      {8, 0},
      {2, 4},
      {9, 3},
      {0, 2}
    };
    sortPairsByValue(pairs);
    
    int n = pairs.length;
    pq = new MinIndexedBinaryHeap<Integer>(n);
    for(int i = 0; i < n; i++)
      pq.insert(pairs[i][0], pairs[i][1]);

    for (int i = 0, minIndex; i < n; i++) {
      minIndex = pq.peekMinIndex();
      assertThat(minIndex).isEqualTo(pairs[i][0]);
      minIndex = pq.pollMinIndex();
      assertThat(minIndex).isEqualTo(pairs[i][0]);
    }
  }

  @Test
  public void testInsertionAndValueOf() {
    String[] names = {"jackie", "wilson", "catherine", "jason", "bobby", "sia"};
    pq = new MinIndexedBinaryHeap<String>(names.length);
    for (int i = 0; i < names.length; i++) 
      pq.insert(i, names[i]);
    for (int i = 0; i < names.length; i++) 
      assertThat(pq.valueOf(i)).isEqualTo(names[i]);
  }


  // TODO(williamfiset): Randomized testing against java.util.PriorityQueue



  static int[] genRandArray(int n, int lo, int hi) {
    return new Random().ints(n, lo, hi).toArray();
  }

  static void sortPairsByValue(Integer[][] pairs) {
    Arrays.sort(pairs, new Comparator<Integer[]>(){
      @Override public int compare(Integer[] pair1, Integer[] pair2) {
        return pair1[1] - pair2[1];
      }
    });
  }

  // Generate a list of random numbers
  // static List <Integer> genRandList(int sz) {
  //   List <Integer> lst = new ArrayList<>(sz);
  //   for (int i = 0; i < sz; i++)
  //     lst.add( (int) (Math.random()*MAX_SZ) );
  //   return lst;
  // }

  // Generate a list of unique random numbers
  // static List <Integer> genUniqueRandList(int sz) {
  //   List <Integer> lst = new ArrayList<>(sz);
  //   for (int i = 0; i < sz; i++) lst.add( i );
  //   Collections.shuffle(lst);
  //   return lst;
  // } 

}