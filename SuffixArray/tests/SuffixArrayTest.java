import static org.junit.Assert.*;
import org.junit.*;

import java.security.SecureRandom;
import java.math.BigInteger;
import java.util.Random;

public class SuffixArrayTest {

  static final SecureRandom random = new SecureRandom();
  static final Random rand = new Random();

  static final int LOOPS = 1000;
  static final int TEST_SZ = 40;
  static final int NUM_NULLS = TEST_SZ / 5;
  static final int MAX_RAND_NUM = 250;

  @Before
  public void setup() { }

  @Test
  public void suffixArrayLength() {
    
    SuffixArray sa1 = new SuffixArraySlow("ABCD");
    SuffixArray sa2 = new SuffixArrayMed("ABCD");
    SuffixArray sa3 = new SuffixArrayFast("ABCD");
    
    assertEquals(sa1.N, 4);
    assertEquals(sa2.N, 4);
    assertEquals(sa3.N, 4);
    
  }

}
