
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

// You can set the hash value of this object to be whatever you want
// This makes it great for testing special cases.
class ConstHashObj {
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

public class MappingTest {

  static Random r = new Random();

  static final int LOOPS = 10000;
  static final int MAX_SIZE = 1000;
  static final int MAX_RAND_NUM = 25;

  Mapping <Integer, Integer> map;

  @Before
  public void setup() {
    map = new Mapping<>();
  }

  @Test(expected=IllegalArgumentException.class)
  public void testNullKey() {
    map.put(null, 5);
  }

  @Test(expected=IllegalArgumentException.class)
  public void testIllegalCreation1() {
    new Mapping<>(-3, 0.5);
  }

  @Test(expected=IllegalArgumentException.class)
  public void testIllegalCreation2() {
    new Mapping<>(5, Double.POSITIVE_INFINITY);
  }

  @Test(expected=IllegalArgumentException.class)
  public void testIllegalCreation3() {
    new Mapping<>(6, -0.5);
  }

  @Test
  public void testLegalCreation() {
    new Mapping<>(6, 0.9);
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

      Set <Integer> set = new HashSet<>(rand_nums);
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

    Mapping <Integer, Integer> map = new Mapping<>();

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

      Array <Integer> keys = map.keys();
      for (Integer key : keys) map.remove(key);
      
      assertTrue( map.isEmpty() );

    }

  }

  @Test
  public void removeTest() {

    Mapping <Integer, Integer> map = new Mapping<>( 7 );
    
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

    Mapping <ConstHashObj, Integer> map = new Mapping<>();

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

      assertTrue(map.size() == map2.size());

      for (int i = 0; i < MAX_SIZE; i++ ) {
        
        double r = Math.random();

        if ( r < 0.5 ) assertEquals( map.put( nums.get(i), i ), map2.put( nums.get(i), i ));

        assertEquals( map.containsKey(nums.get(i)), map2.containsKey(nums.get(i)) );
        assertEquals( map.size(), map2.size() );

        if ( r > 0.5 ) assertEquals( map.remove( nums.get(i) ), map2.remove( nums.get(i) ) );

        assertEquals( map.containsKey(nums.get(i)), map2.containsKey(nums.get(i)) );
        assertEquals( map.size(), map2.size() );

      }
      
    }


  }

  // Generate a list of random numbers
  static List <Integer> genRandList(int sz) {
    List <Integer> lst = new ArrayList<>(sz);
    for (int i = 0; i < sz; i++) lst.add( (int) (Math.random()*MAX_RAND_NUM ));
    Collections.shuffle( lst );
    return lst;
  }

  // Generate a list of unique random numbers
  static List <Integer> genUniqueRandList(int sz) {
    List <Integer> lst = new ArrayList<>(sz);
    for (int i = 0; i < sz; i++) lst.add( i );
    Collections.shuffle( lst );
    return lst;
  }  

}
