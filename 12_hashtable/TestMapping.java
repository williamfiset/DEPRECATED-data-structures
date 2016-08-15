import static java.lang.Math.*;
import java.util.*;
import java.io.*;

class ConstHashObj {

  Long data;
  public ConstHashObj (Long data) {
    this.data = data;
  }

  @Override public int hashCode() {
    return 4; // Hash is constant, great for collison tests
  }

  @Override public boolean equals(Object o) {
    ConstHashObj c = (ConstHashObj)o;
    return data.equals(c.data);
  }

  @Override public String toString() {
    return ""+data;
  }

}

public class TestMapping {

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

  static void removeTests() {

    Mapping<String, String> map = new Mapping<>();

    map.put("A", "B");
    map.put("B", "C");
    map.put("C", "D");

    map.remove("B");
    map.remove("C");
    map.remove("A");

    System.out.println( map.size() == 0 );

    Mapping<ConstHashObj, Integer> map2 = new Mapping<>();
    ConstHashObj o1 = new ConstHashObj(1L);
    ConstHashObj o2 = new ConstHashObj(2L);
    ConstHashObj o3 = new ConstHashObj(3L);
    ConstHashObj o4 = new ConstHashObj(4L);

    map2.put(o1, 111);
    System.out.println( Arrays.toString(map2.table) );
    map2.put(o2, 111);
    System.out.println( Arrays.toString(map2.table) );
    map2.put(o3, 111);
    System.out.println( Arrays.toString(map2.table) );
    map2.put(o4, 111);
    System.out.println( Arrays.toString(map2.table) );

    System.out.println("Finished Adding");
    map2.remove(o2);
    System.out.println( Arrays.toString(map2.table) );
    map2.remove(o3);
    System.out.println( Arrays.toString(map2.table) );
    map2.remove(o1);
    System.out.println( Arrays.toString(map2.table) );
    map2.remove(o4);
    System.out.println( Arrays.toString(map2.table) );

    System.out.println(map2.size());

  }

  public static void main(String[] args) {

    // int h = 3;
    // System.out.println( Number.class.isInstance(null) );
    // putting();
    // testIterator();
    removeTests();


  }
}
