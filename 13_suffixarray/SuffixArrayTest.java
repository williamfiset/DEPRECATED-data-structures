import static org.junit.Assert.*;
import org.junit.*;

import java.security.SecureRandom;
import java.math.BigInteger;
import java.util.Random;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;

public class SuffixArrayTest {

  static final SecureRandom random = new SecureRandom();
  static final Random rand = new Random();

  static final int LOOPS = 1000;
  static final int TEST_SZ = 1051;
  static final int NUM_NULLS = TEST_SZ / 5;
  static final int MAX_RAND_NUM = 250;

  @Before
  public void setup() { }

  /*
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
  */

  @Test 
  public void containsSubstring() {

    String s = "abcdef";
    SuffixArray sa = new SuffixArray(s);
    
    assertTrue( sa.contains("") );
    for (int i = 0; i < s.length(); i++ ) {
      for (int j = i+1; j <= s.length(); j++ ) {
        String substr = s.substring(i, j);
        assertTrue(sa.contains(substr));
      }
    }

    assertFalse( sa.contains("abce") );
    assertFalse( sa.contains("efg") );
    assertFalse( sa.contains("aaa") );
    assertFalse( sa.contains("y") );

  }

  @Test
  public void testLRS() {

    List <String> list = new ArrayList<>();

    String s = "aabaab";
    SuffixArray sa = new SuffixArray(s);
    Set <String> lrss = sa.lrs();
    list.addAll(lrss);
    assertEquals(1, lrss.size());
    assertEquals("aab", list.get(0));
    System.out.println( lrss );
    list.clear();

    s = "abcdefg";
    sa = new SuffixArray(s);
    lrss = sa.lrs();    
    list.addAll(lrss);
    assertEquals(0, list.size());
    System.out.println( lrss );
    list.clear();

    s = "abca";
    sa = new SuffixArray(s);
    lrss = sa.lrs();
    list.addAll(lrss);
    assertEquals(1, lrss.size());
    assertEquals("a", list.get(0));
    System.out.println( lrss );
    list.clear();

    s = "abcba";
    sa = new SuffixArray(s);
    lrss = sa.lrs();
    list.addAll(lrss);
    assertEquals(2, lrss.size() );
    assertEquals("a", list.get(0));
    assertEquals("b", list.get(1));
    System.out.println( lrss );
    list.clear();


    s = "aZZbZZcYYdYYe";
    sa = new SuffixArray(s);
    lrss = sa.lrs();
    list.addAll(lrss);
    assertEquals(2, lrss.size() );
    assertEquals("YY", list.get(0));
    assertEquals("ZZ", list.get(1));
    System.out.println( lrss );
    list.clear();

    s = "AAAAAA";
    sa = new SuffixArray(s);
    lrss = sa.lrs();
    list.addAll(lrss);
    assertEquals(1, lrss.size() );
    assertEquals("AAAAA", list.get(0));
    System.out.println( lrss );
    list.clear();

    s = "aWXYZsdfABCDbvABCDsWXYZyWXYZjisdssd";
    sa = new SuffixArray(s);
    lrss = sa.lrs();
    list.addAll(lrss);
    assertEquals(2, lrss.size() );
    assertEquals("ABCD", list.get(0));
    assertEquals("WXYZ", list.get(1));
    System.out.println( lrss );
    list.clear();

  }

  /*
  @Test
  public void testRandomizedContains() {

    for (int loop = 1; loop < LOOPS; loop++) {
      String r = randomString(50);

      for (int i = 0; i < TEST_SZ ;i++ ) {
        
        int s = randNum(0, r.length()-1);
        int e = randNum(s, r.length()-1);
        if (s == e) continue;
        String substr = r.substring(s,e);
        SuffixArray sa = new SuffixArray( r );
        assertTrue(sa.contains(substr));

      }

    }

  }*/

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
