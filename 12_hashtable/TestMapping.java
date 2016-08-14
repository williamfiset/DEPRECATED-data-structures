import static java.lang.Math.*;
import java.util.*;
import java.io.*;

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
    
    Mapping<Integer, Integer> map = new Mapping<>(3);
    
    map.put(4, 5);
    map.put(4, 6);
    map.put(5, 77);
    map.put(66, -567);
    // map.put(67, -567);

    System.out.println( map.entries() );
    System.out.println( map.get(4) );
    System.out.println( map.get(5) );
    System.out.println( map.get(66) );

  }

  public static void main(String[] args) {

    // int h = 3;
    // System.out.println( Number.class.isInstance(null) );
    putting();


  }
}
