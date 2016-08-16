
import static org.junit.Assert.*;
import org.junit.*;
import static java.lang.Math.*;
import java.util.*;
import java.io.*;

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

  @Before
  public void setup() {

  }

  static boolean isPrime(final long n) {

    if (n < 2) return false;
    if (n == 2 || n == 3) return true;
    if (n % 2 == 0 || n % 3 == 0) return false;
    int limit = (int) Math.sqrt(n);
    for (int i = 5; i <= limit; i += 6)
      if (n % i == 0 || n % (i + 2) == 0)
        return false;
    return true;

  }

  static void testCapacity() {

    Mapping<Integer, Integer> map = new Mapping<>();
    for (int i = 0; i < 40; i++) {
      int cap = map.capacity;
      if (!isPrime(cap)) System.out.println("capacity is not prime -_-");
      // else System.out.println(cap + " is prime");
      map.rehash();
    }

  }

  static void putting() {
    
    for(int map_size = 0; map_size < 100; map_size++) {
      Mapping<Integer, Integer> map = new Mapping<>(map_size);
      for(int i = 0; i < 100; i++) {
        map.put(i, i);
      }
    }

  }

  static void testIterator() {

    Mapping<String, Long> map = new Mapping<>();

    map.put("34", 35L);
    map.put("456", 456L);
    map.put("666", 666L);
    map.put("-Hellos dfsdf", 0L);
    map.put("34", 35L);
    map.put("456", 456L);

    System.out.println(map);

    for(String k : map) {
      System.out.println(k);
    }

  }

  @Test
  public void removeTestSimple1() {

    Mapping<String, String> map = new Mapping<>();

    map.put("A", "B");
    map.put("B", "C");
    map.put("C", "D");

    map.remove("B");
    map.remove("C");
    map.remove("A");

    assertTrue( map.size() == 0 );

  }

  @Test
  public void randomRemove() {

    Mapping<Integer, Integer> map = new Mapping<>();
    Set<Integer> keys = new HashSet<>();
    for(int i = 0; i < 10000; i++) {
      int randomVal = r.nextInt();
      if (!keys.contains(randomVal)) {
        keys.add(randomVal);
        map.put(randomVal, 5);
      }
    }

    for(Integer k : keys) {
      map.remove(k);
    }

    assertTrue(map.size() == 0);

  }

  @Test
  public void removeTestComplex1() {

    Mapping<ConstHashObj, Integer> map = new Mapping<>();
    ConstHashObj o1 = new ConstHashObj(88, 1);
    ConstHashObj o2 = new ConstHashObj(88, 2);
    ConstHashObj o3 = new ConstHashObj(88, 3);
    ConstHashObj o4 = new ConstHashObj(88, 4);


    // System.out.println(map.size());
    map.put(o1, 111);
    // System.out.println( Arrays.toString(map.table) );
    // System.out.println(map.size());
    map.put(o2, 111);
    // System.out.println(map.size());
    // System.out.println( Arrays.toString(map.table) );
    map.put(o3, 111);
    // System.out.println(map.size());
    // System.out.println( Arrays.toString(map.table) );
    map.put(o4, 111);
    // System.out.println( Arrays.toString(map.table) );
    // System.out.println("After ADD:" + map.size());

    map.remove(o2);
    // System.out.println( Arrays.toString(map.table) );
    // System.out.println(map.size());
    map.remove(o3);
    // System.out.println(map.size());
    // System.out.println( Arrays.toString(map.table) );
    map.remove(o1);
    // System.out.println(map.size());
    // System.out.println( Arrays.toString(map.table) );
    map.remove(o4);
    // System.out.println(map.size());
    // System.out.println( Arrays.toString(map.table) );

    assertTrue(map.size() == 0);    
  }

}
