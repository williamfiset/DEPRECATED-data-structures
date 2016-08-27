import static org.junit.Assert.*;
import org.junit.*;

public class TrieTest {

  // @Before public void setup() { }

  @Test(expected=IllegalArgumentException.class)
  public void testBadTrieInsert() {
    (new Trie()).insert(null);
  }  

  @Test(expected=IllegalArgumentException.class)
  public void testBadTrieCount() {
    (new Trie()).count(null);
  }

  @Test(expected=IllegalArgumentException.class)
  public void testBadTrieContains() {
    (new Trie()).contains(null);
  }

  @Test
  public void testContains() {

    Trie t1 = new Trie();
    
    // This implementation doesn't count the empty string as 
    // a valid string to be inserted into the trie (although it 
    // would be easy to account for)
    t1.insert("");
    assertFalse( t1.contains("") );
    t1.insert("");
    assertFalse( t1.contains("") );
    t1.insert("");
    assertFalse( t1.contains("") );

    Trie t2 = new Trie();
    t2.insert("aaaaa");
    t2.insert("aaaaa");
    t2.insert("aaaaa");
    t2.insert("aaaaa");
    t2.insert("aaaaa");
    assertTrue( t2.contains("aaaaa") );
    assertTrue( t2.contains("aaaa") );
    assertTrue( t2.contains("aaa") );
    assertTrue( t2.contains("aa") );
    assertTrue( t2.contains("a") );

    Trie t3 = new Trie();
    
    t3.insert("AE");
    t3.insert("AE");
    t3.insert("AH");
    t3.insert("AH");
    t3.insert("AH7");
    t3.insert("A7");
    t3.insert("7");
    t3.insert("7");
    t3.insert("B");
    t3.insert("B");
    t3.insert("B");
    t3.insert("B");

    assertTrue(t3.contains("A"));
    assertTrue(t3.contains("AH"));
    assertTrue(t3.contains("A7"));
    assertTrue(t3.contains("AE"));
    assertTrue(t3.contains("AH7"));
    assertTrue(t3.contains("7"));
    assertTrue(t3.contains("B"));

    assertFalse(t3.contains("Ar"));
    assertFalse(t3.contains("A8"));
    assertFalse(t3.contains("AH6"));
    assertFalse(t3.contains("C"));

  }

  @Test
  public void testCount() {

    Trie t1 = new Trie();
    
    // This implementation doesn't count the empty string as 
    // a valid string to be inserted into the trie (although it 
    // would be easy to account for)
    t1.insert("");
    assertEquals( t1.count(""), 0 );
    t1.insert("");
    assertEquals( t1.count(""), 0 );
    t1.insert("");
    assertEquals( t1.count(""), 0 );

    Trie t2 = new Trie();
    t2.insert("aaaaa");
    t2.insert("aaaaa");
    t2.insert("aaaaa");
    t2.insert("aaaaa");
    t2.insert("aaaaa");
    assertEquals( t2.count("aaaaa"), 5 );
    assertEquals( t2.count("aaaa"), 5 );
    assertEquals( t2.count("aaa"), 5 );
    assertEquals( t2.count("aa"), 5 );
    assertEquals( t2.count("a"), 5 );

    Trie t3 = new Trie();
    
    t3.insert("AE");
    t3.insert("AE");
    t3.insert("AH");
    t3.insert("AH");
    t3.insert("AH7");
    t3.insert("A7");
    
    t3.insert("7");
    t3.insert("7");
    
    t3.insert("B");
    t3.insert("B");
    t3.insert("B");
    t3.insert("B");

    assertEquals(t3.count("A"), 6);
    assertEquals(t3.count("AH"), 3);
    assertEquals(t3.count("A7"), 1);
    assertEquals(t3.count("AE"), 2);
    assertEquals(t3.count("AH7"), 1);
    assertEquals(t3.count("7"), 2);
    assertEquals(t3.count("B"), 4);
    assertEquals(t3.count("Ar"), 0);
    assertEquals(t3.count("A8"), 0);
    assertEquals(t3.count("AH6"), 0);
    assertEquals(t3.count("C"), 0);


  }

  @Test
  public void testClear() {



  }

  @Test
  public void testEdgeCases() {

    Trie t = new Trie();
    assertEquals(t.count(""), 0);
    assertEquals(t.count("\0"), 0);
    assertEquals(t.count("\0\0"), 0);
    assertEquals(t.count("\0\0\0"), 0);

    for(char c = 0; c < 128; c++)
      assertEquals(t.count( ""+c ), 0);

    assertFalse(t.contains(""));
    assertFalse(t.contains("\0"));
    assertFalse(t.contains("\0\0"));
    assertFalse(t.contains("\0\0\0"));

    for(char c = 0; c < 128; c++)
      assertFalse(t.contains( ""+c ));

  }

}

