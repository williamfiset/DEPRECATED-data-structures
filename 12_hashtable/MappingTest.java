
import static java.lang.Math.*;
import static org.junit.Assert.*;
import org.junit.*;
import java.util.Random;
import java.util.Set;
import java.util.HashSet;
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

  // static void testCapacity() {

  //   Mapping<Integer, Integer> map = new Mapping<>();
    
  // }

  // static void putting() {
    
  //   for(int map_size = 0; map_size < 100; map_size++) {
  //     Mapping<Integer, Integer> map = new Mapping<>(map_size);
  //     for(int i = 0; i < 100; i++) {
  //       map.put(i, i);
  //     }
  //   }

  // }

  // static void testIterator() {

  //   Mapping<String, Long> map = new Mapping<>();

  //   map.put("34", 35L);
  //   map.put("456", 456L);
  //   map.put("666", 666L);
  //   map.put("-Hellos dfsdf", 0L);
  //   map.put("34", 35L);
  //   map.put("456", 456L);

  //   System.out.println(map);

  //   for(String k : map)
  //     System.out.println(k);

  // }

  // @Test
  // public void removeTestSimple1() {

  //   Mapping<String, String> map = new Mapping<>();

  //   map.put("A", "B");
  //   map.put("B", "C");
  //   map.put("C", "D");

  //   map.remove("B");
  //   map.remove("C");
  //   map.remove("A");

  //   assertEquals( 0, map.size() );

  // }

  // @Test
  // public void randomRemove() {

  //   Mapping<Integer, Integer> map = new Mapping<>();
  //   Set<Integer> keys = new HashSet<>();
  //   for(int i = 0; i < 100000; i++) {
  //     int randomVal = r.nextInt() % 400000;
  //     if (!keys.contains(randomVal)) {
  //       keys.add(randomVal);
  //       map.put(randomVal, 5);
  //     }
  //   }

  //   System.out.println( map.size() + " " + keys.size());
  //   assertEquals( map.size(), keys.size() );

  // }

  @Test
  public void removeTest() {

    Mapping <Integer, Integer> map = new Mapping<>( 7 );
    
    // Add three elements
    map.put(11, 0); map.put(12, 0); map.put(13, 0);
    System.out.println("FIRST: " + map);
    assertEquals(3, map.size());
    System.out.println("SIZE: " + map.size());

    // Add ten more
    for(int i = 1; i <= 10; i++) map.put(i, 0);
    System.out.println("SECOND: " + map);
    assertEquals(13, map.size());
    System.out.println("SIZE: " + map.size());

    // remove ten
    for(int i = 1; i <= 10; i++) map.remove(i);
    System.out.println("THIRD: " + map);
    assertEquals(3, map.size());
    System.out.println("SIZE: " + map.size());

    // remove three
    map.remove(13); map.remove(15); map.remove(26);
    System.out.println("FOURTH: " + map);
    assertEquals(0, map.size());
    System.out.println("SIZE: " + map.size());

  }

  /*
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
  */

}
