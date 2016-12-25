import static org.junit.Assert.*;
import org.junit.*;

import java.security.SecureRandom;
import java.math.BigInteger;
import java.util.Random;
import java.util.Arrays;

public class SuffixArrayTest {

  static final SecureRandom random = new SecureRandom();
  static final Random rand = new Random();

  static final int LOOPS = 1000;
  static final int TEST_SZ = 1051;
  static final int NUM_NULLS = TEST_SZ / 5;
  static final int MAX_RAND_NUM = 250;

  @Before
  public void setup() { }

  @Test
  public void testContruction() {

    for (int i = 0; i < LOOPS; i++) {
      
      String r = randomString(randNum(1, TEST_SZ));

      SuffixArray sa = new SuffixArray(r);
      SuffixArrayNaive san = new SuffixArrayNaive(r);

      int[] sa_arr = sa.sa; //getSuffixPositions();
      int[] san_arr = san.getSuffixPositions();

      for (int k = 0; k < sa.N; k++ )
        assertEquals(san_arr[k], sa_arr[k]);

    }

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
        SuffixArrayNaive sa = new SuffixArrayNaive( r );
        assertTrue(sa.contains(substr));

      }

    }

  }

  @Test
  public void testLCS() {

    assertEquals( SuffixArray.lcs("abcde", "gear", '#'), "a" );
    assertEquals( SuffixArray.lcs("abcde", "xzy", '#'), null );
    assertEquals( SuffixArray.lcs("cabbage", "garbage", '#'), "bage" );
    assertEquals( SuffixArray.lcs("123-345-4566", "4-345-4566-7653", '#'), "-345-4566" );

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
