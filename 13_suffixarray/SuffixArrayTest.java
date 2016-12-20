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
      // System.out.println(r);

      SuffixArray sa = new SuffixArray(r);
      SuffixArrayNaive san = new SuffixArrayNaive(r);

      int[] sa_arr = sa.getSuffixPositions();
      int[] san_arr = san.getSuffixPositions();

      for (int k = 0; k < sa.len; k++ )
        assertEquals(san_arr[k], sa_arr[k]);

    }

  }

  // @Test
  // public void testRandomizedContains() {

  //   for (int loop = 1; loop < LOOPS; loop++) {
  //     String r = randomString(50);

  //     for (int i = 0; i < TEST_SZ ;i++ ) {
        
  //       int s = randNum(0, r.length()-1);
  //       int e = randNum(s, r.length()-1);
  //       if (s == e) continue;
  //       String substr = r.substring(s,e);
  //       SuffixArrayNaive sa = new SuffixArrayNaive( r );
  //       assertTrue(sa.contains(substr));

  //     }

  //   }

  // }

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


/*
  private void constructSuffixArray() {

    // Tracks the position of the shuffled suffixes 
    // in their partially sorted state.
    int [] suffix_pos = new int[len];

    // Initially sort the suffixes by their first two characters
    Arrays.sort(suffixes);

    for(int pos = 2; pos < len; pos *= 2) {

      // Initialize first suffix values to zero
      Suffix firstSuffix = suffixes[0];
      int new_rank = suffix_pos[firstSuffix.index] = firstSuffix.rank.rank1 = 0;

      // Update rank1
      for (int i = 1; i < len; i++) {

        Suffix prev_suffix = suffixes[i-1];
        Suffix suffix = suffixes[i];
        suffix_pos[ suffix.index ] = i;

        // if ( suffix.rank.rank1 == prev_suffix.rank.rank2 )
        if ( suffix.rank.rank1 != prev_suffix.rank.rank2 )
          ++new_rank;
        
        // suffix.rank.rank1 = ++new_rank;

      }

      // Update rank2
      for (int i = 0; i < len; i++) {
        Suffix suffix = suffixes[i];
        int nextIndex = suffix.index + pos;
        if (nextIndex < len) {
          Suffix nextSuffix = suffixes[suffix_pos[nextIndex]];
          suffix.rank.rank2 = nextSuffix.rank.rank1;
        } else suffix.rank.rank2 = -1;
      }

      Arrays.sort(suffixes);

    }

  }
*/