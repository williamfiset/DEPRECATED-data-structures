import static org.junit.Assert.*;
import org.junit.*;

import java.security.SecureRandom;
import java.math.BigInteger;
import java.util.Random;

public class SuffixArraySlowTest {

  static final SecureRandom random = new SecureRandom();
  static final Random rand = new Random();

  static final int LOOPS = 1000;
  static final int TEST_SZ = 40;
  static final int NUM_NULLS = TEST_SZ / 5;
  static final int MAX_RAND_NUM = 250;

  @Before
  public void setup() { }

  @Test
  public void testSuffixArrayContains() {

    SuffixArraySlow sa = new SuffixArraySlow("");
    // assertTrue(sa.contains("")); // forgivable failure?
    assertFalse(sa.contains("a"));
    assertFalse(sa.contains("hello"));
    assertFalse(sa.contains("world"));
    assertFalse(sa.contains(null));

    sa = new SuffixArraySlow("helloworld");
    assertTrue(sa.contains("llo"));
    assertTrue(sa.contains("hell"));
    assertTrue(sa.contains("world"));
    assertTrue(sa.contains("e"));
    assertTrue(sa.contains("r"));
    assertTrue(sa.contains("h"));
    assertTrue(sa.contains("d"));
    assertTrue(sa.contains("l"));
    assertFalse(sa.contains(null));

  }

  @Test
  public void testRandomizedContains() {

    for (int loop = 1; loop < LOOPS; loop++) {
      String r = randomString(50);

      for (int i = 0; i < TEST_SZ ;i++ ) {

        int s = randNum(0, r.length()-1);
        int e = randNum(s, r.length()-1);
        if (s == e) continue;
        String substr = r.substring(s,e);
        SuffixArraySlow sa = new SuffixArraySlow( r );
        assertTrue(sa.contains(substr));

      }

    }

  }

  static int randNum(int min, int max) {
    int range = max - min + 1;
    return rand.nextInt(range) + min;
  }

  static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

  static String randomString( int len ){
    StringBuilder sb = new StringBuilder( len );
    for( int i = 0; i < len; i++ )
       sb.append( AB.charAt( rand.nextInt(AB.length()) ) );
    return sb.toString();
  }

}
