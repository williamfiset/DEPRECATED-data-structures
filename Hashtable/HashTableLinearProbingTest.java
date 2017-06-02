
import static java.lang.Math.*;
import static org.junit.Assert.*;
import org.junit.*;

import java.util.Random;
import java.util.Set;
import java.util.List;
import java.util.HashSet;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

public class HashTableLinearProbingTest {

  // You can set the hash value of this object to be whatever you want
  // This makes it great for testing special cases.
  static class ConstHashObj {
    int hash, data;
    public ConstHashObj (int hash, int data) {
      this.hash = hash;
      this.data = data;
    }
    @Override public int hashCode() { return hash; }
    @Override public boolean equals(Object o) {
      return data == ((ConstHashObj)o).data;
    }
  }

  static Random r = new Random();

  static final int LOOPS = 30000;
  static final int MAX_SIZE = 100;
  static final int MAX_RAND_NUM = 50;

  HashTableLinearProbing <Integer, Integer> map;

  @Before
  public void setup() {
    map = new HashTableLinearProbing<>();
  }

  @Test(expected=IllegalArgumentException.class)
  public void testNullKey() {
    map.put(null, 5);
  }

  @Test(expected=IllegalArgumentException.class)
  public void testIllegalCreation1() {
    new HashTableLinearProbing<>(-3, 0.5);
  }

  @Test(expected=IllegalArgumentException.class)
  public void testIllegalCreation2() {
    new HashTableLinearProbing<>(5, Double.POSITIVE_INFINITY);
  }

  @Test(expected=IllegalArgumentException.class)
  public void testIllegalCreation3() {
    new HashTableLinearProbing<>(6, -0.5);
  }

  @Test
  public void testLegalCreation() {
    new HashTableLinearProbing<>(6, 0.9);
  }

  @Test
  public void testUpdatingValues() {
    map.add(1,1);
    assertTrue(map.get(1) == 1);
    map.add(1, 5);
    assertTrue(map.get(1) == 5);
    map.add(1, -7);
    assertTrue(map.get(1) == -7);
  }

  @Test
  public void testIterator() {

    HashMap <Integer, Integer> map2 = new HashMap<>();

    for (int loop = 0; loop < LOOPS; loop++) {
      
      map.clear();
      map2.clear();
      assertTrue(map.isEmpty());

      List <Integer> rand_nums = genRandList(MAX_SIZE);
      for (Integer key : rand_nums)
        assertEquals(map.add(key, key), map2.put(key, key));

      int count = 0;
      for (Integer key : map) {
        assertEquals(key, map.get(key));
        assertEquals(map.get(key), map2.get(key));
        assertTrue(map.hasKey(key));
        assertTrue(rand_nums.contains(key));
        count++;
      }

      for (Integer key : map2.keySet()) {
        assertEquals(key, map.get(key));
      }

      Set <Integer> set = new HashSet<>();
      for(int n : rand_nums) set.add(n);

      assertEquals( set.size() , count);
      assertEquals( map2.size(), count );

    }

  }

  @Test(expected=java.util.ConcurrentModificationException.class)
  public void testConcurrentModificationException() {
    map.add(1,1);
    map.add(2,1);
    map.add(3,1);
    for (Integer key : map) map.add(4,4);
  }

  @Test(expected=java.util.ConcurrentModificationException.class)
  public void testConcurrentModificationException2() {
    map.add(1,1);
    map.add(2,1);
    map.add(3,1);
    for (Integer key : map) map.remove(2);
  }

  @Test
  public void randomRemove() {

    HashTableLinearProbing <Integer, Integer> map = new HashTableLinearProbing<>();

    for (int loop = 0; loop < LOOPS; loop++) {
      
      map.clear();

      // Add some random values
      Set <Integer> keys_set = new HashSet<>();
      for(int i = 0; i < MAX_SIZE; i++) {
        int randomVal = r.nextInt() % 400000;
        keys_set.add(randomVal);
        map.put(randomVal, 5);
      }

      assertEquals( map.size(), keys_set.size() );

      List <Integer> keys = map.keys();
      for (Integer key : keys) map.remove(key);
      
      assertTrue( map.isEmpty() );

    }

  }

  @Test
  public void removeTest() {

    HashTableLinearProbing <Integer, Integer> map = new HashTableLinearProbing<>( 7 );
    
    // Add three elements
    map.put(11, 0); map.put(12, 0); map.put(13, 0);
    assertEquals(3, map.size());

    // Add ten more
    for(int i = 1; i <= 10; i++) map.put(i, 0);
    assertEquals(13, map.size());

    // Remove ten
    for(int i = 1; i <= 10; i++) map.remove(i);
    assertEquals(3, map.size());

    // remove three
    map.remove(11); map.remove(12); map.remove(13);
    assertEquals(0, map.size());

  }

  @Test
  public void removeTestComplex1() {

    HashTableLinearProbing <ConstHashObj, Integer> map = new HashTableLinearProbing<>();

    ConstHashObj o1 = new ConstHashObj(88, 1);
    ConstHashObj o2 = new ConstHashObj(88, 2);
    ConstHashObj o3 = new ConstHashObj(88, 3);
    ConstHashObj o4 = new ConstHashObj(88, 4);

    map.add(o1, 111);
    map.add(o2, 111);
    map.add(o3, 111);
    map.add(o4, 111);

    map.remove(o2);
    map.remove(o3);
    map.remove(o1);
    map.remove(o4);

    assertEquals(0, map.size());

  }

  @Test
  public void testRandomMapOperations() {

    HashMap <Integer, Integer> map2 = new HashMap<>();

    for (int loop = 0; loop < LOOPS; loop++) {
      
      List <Integer> nums = genRandList(MAX_SIZE);
      map.clear();
      map2.clear();

      assertTrue(map2.size() == map.size());

      // StringBuilder sb = new StringBuilder();

      for (int i = 0; i < MAX_SIZE; i++ ) {
        
        double r = Math.random();

        int key = nums.get(i);
        int val = i;

        if ( r < 0.5 ) {
          // sb.append("INSERT: " + key + " : " + val + "\n");
          assertEquals( map2.put( key, val ), map.put( key, val ));
        }

        // System.out.println(sb);

        // assertEquals( map2.get(key), map.get(key));
        // assertEquals( map2.containsKey(key), map.containsKey(key) );
        assertEquals( map2.size(), map.size() );

        if ( r > 0.5 ) {
          // sb.append("REMOVE: " + key + "\n");
          assertEquals( map.remove( key ), map2.remove( key ) );
        }

        // System.out.println(sb);

        // assertEquals( map2.get(key), map.get(key));
        // assertEquals( map2.containsKey(key), map.containsKey(key) );
        assertEquals( map2.size(), map.size() );

      }

      // System.out.println();
      
    }

  }

  @Test
  public void randomIteratorTests() {

    HashTableLinearProbing <Integer, LinkedList<Integer>> m = new HashTableLinearProbing<>();
    HashMap <Integer, LinkedList<Integer>> hm = new HashMap<>();

    for (int loop = 0; loop < LOOPS; loop++ ) {
      
      m.clear();
      hm.clear();

      int sz = (int)(Math.random() * MAX_SIZE);
      m  = new HashTableLinearProbing<>(sz);
      hm = new HashMap<>(sz);

      for (int i = 0; i < MAX_SIZE; i++) {
        
        int index = (int)(Math.random() * MAX_SIZE);
        LinkedList <Integer> l1 = m.get(index);
        LinkedList <Integer> l2 = hm.get(index);

        if ( l2 == null ) {
          l1 = new LinkedList<Integer>();
          l2 = new LinkedList<Integer>();
          m.put(index,  l1);
          hm.put(index, l2);
        }

        int rand_val = (int)(Math.random() * MAX_SIZE);
        
        if ( Math.random() < 0.5 ) {

          l1. removeFirstOccurrence(rand_val);
          l2. removeFirstOccurrence(rand_val);

        } else {

          l1.add(rand_val);
          l2.add(rand_val);

        }

        // Compare Lists
        for (Integer I : l1) assertTrue(l2.contains(I));
        for (Integer I : l2) assertTrue(l1.contains(I));

        assertEquals( m.size(), hm.size() );
        assertEquals( l1.size(), l2.size() );

      }

    }


  }

  // Generate a list of random numbers
  static List <Integer> genRandList(int sz) {
    
    List <Integer> lst = new ArrayList<>(sz);
    for (int i = 0; i < sz; i++) lst.add( (int) (Math.random()*MAX_RAND_NUM ));
    Collections.shuffle( lst );
    return lst;
    // Integer[] retAr = new Integer[sz];
    // lst.toArray(retAr);
    // return retAr;

  }

  // Generate a list of unique random numbers
  static List <Integer> genUniqueRandList(int sz) {
    List <Integer> lst = new ArrayList<>(sz);
    for (int i = 0; i < sz; i++) lst.add( i );
    Collections.shuffle( lst );
    return lst;
    // Integer[] retAr = new Integer[sz];
    // lst.toArray(retAr);
    // return retAr;
  }  

}
