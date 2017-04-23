import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import java.util.*;

public class BloomFilterTests {

  static final int MIN_RAND_NUM = -1000;
  static final int MAX_RAND_NUM = +1000;

  static final int TEST_SZ = 1000;
  static final int LOOPS = 1000;

  @Before
  public void setup() {
    
  }

  @Test
  public void testStringSetAllSubsets() {

    final String s = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    StringSet set = new StringSet(s.length());
    set.addAllSubstrings(s);

    for (int i = 0; i < s.length(); i++) {
      for (int j = i+1; j < s.length(); j++) {
        String sub = s.substring(i, j+1);
        assertTrue(set.contains(sub));
      }
    }

  }

  @Test
  public void testStringSetAllSubsetsFailure() {

    int[] smallPrimes = {113, 107};
    boolean collisionHappened = false;
    final String s = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    StringSet set = new StringSet(smallPrimes, s.length());

    // Add a few substrings to the set
    int len = 26;
    for (int i = 0; i < len; i++) {
      for (int j = i+1; j < len; j++) {
        String sub = s.substring(i, j+1);
        set.add(sub);
      }
    }

    // Check for strings that shouldn't be in the set
    // there should be plenty of collisions
    len = s.length();
    for (int i = 27; i < len; i++) {
      for (int j = i+1; j < len; j++) {
        String sub = s.substring(i, j+1);
        if (set.contains(sub)) collisionHappened = true;
      }
    }

    // The probablity of a collision should be really high
    // because we're using small prime numbers
    assertTrue(collisionHappened);

  }  

}

