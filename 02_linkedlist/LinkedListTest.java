import static org.junit.Assert.*;
import org.junit.*;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class LinkedListTest {
  
  static final int TEST_SZ = 10000;
  LinkedList<Integer> list;

  @Before
  public void setup() {
    list = new LinkedList<>();
  }

  @Test
  public void testEmptyList() {
    assertTrue(list.isEmpty());
    assertEquals(list.size(), 0);
  }
  
  @Test(expected=Exception.class)
  public void testRemoveFirstOfEmpty() {
    list.removeFirst(); 
  }
  
  @Test(expected=Exception.class)
  public void testRemoveLastOfEmpty() {
    list.removeLast(); 
  }
  
  @Test(expected=Exception.class)
  public void testPeekFirstOfEmpty() {
    list.peekFirst(); 
  }
  
  @Test(expected=Exception.class)
  public void testPeekLastOfEmpty() {
    list.peekLast(); 
  }
  
  @Test
  public void testAddFirst() {
    list.addFirst(3);
    assertEquals(list.size(), 1);
    list.addFirst(5);
    assertEquals(list.size(), 2);
  }
  
  @Test
  public void testAddLast() {
    list.addLast(3);
    assertEquals(list.size(), 1);
    list.addLast(5);
    assertEquals(list.size(), 2);
  }
  
  @Test
  public void testRemoveFirst() {
    list.addFirst(3);
    assertTrue(list.removeFirst() == 3);
    assertTrue(list.isEmpty());
  }
  
  @Test
  public void testRemoveLast() {
    list.addLast(4);
    assertTrue(list.removeLast() == 4);
    assertTrue(list.isEmpty());
  }
  
  @Test
  public void testPeekFirst() {
    list.addFirst(4);
    assertTrue(list.peekFirst() == 4);
    assertEquals(list.size(), 1);
  }
  
  @Test
  public void testPeekLast() {
    list.addLast(4);
    assertTrue(list.peekLast() == 4);
    assertEquals(list.size(), 1);
  }

  @Test
  public void testPeeking() {
    
    // 5
    list.addFirst(5);
    assertTrue(list.peekFirst() == 5);
    assertTrue(list.peekLast() == 5);

    // 6 - 5
    list.addFirst(6);
    assertTrue(list.peekFirst() == 6);
    assertTrue(list.peekLast() == 5);

    // 7 - 6 - 5
    list.addFirst(7);
    assertTrue(list.peekFirst() == 7);
    assertTrue(list.peekLast() == 5);

    // 7 - 6 - 5 - 8
    list.addLast(8);
    assertTrue(list.peekFirst() == 7);
    assertTrue(list.peekLast() == 8);

    // 7 - 6 - 5
    list.removeLast();
    assertTrue(list.peekFirst() == 7);
    assertTrue(list.peekLast() == 5);

    // 7 - 6
    list.removeLast();
    assertTrue(list.peekFirst() == 7);
    assertTrue(list.peekLast() == 6);

    // 6
    list.removeFirst();
    assertTrue(list.peekFirst() == 6);
    assertTrue(list.peekLast() == 6);

  }

  @Test
  public void testRemoving() {
    
    LinkedList <String> strs = new LinkedList<>();
    strs.add("a");
    strs.add("b");
    strs.add("c");
    strs.add("d");
    strs.add("e");
    strs.add("f");
    strs.remove("b");
    strs.remove("a");
    strs.remove("d");
    strs.remove("e");
    strs.remove("c");
    strs.remove("f");
    assertEquals(0, strs.size());

  }

  @Test
  public void testRemoveAt() {
    
    list.add("a");
    list.add("b");
    list.add("c");
    list.add("d");
    list.removeAt(0);
    list.removeAt(2);
    assertEquals( list.peekFirst(), "b" );
    assertEquals( list.peekLast(), "c" );
    list.removeAt(1);
    list.removeAt(0);
    assertEquals( list.size(), 0 );
  }

  @Test
  public void testClear() {

  }

  @Test
  public void testRandomizedRemoving() {

    java.util.LinkedList <Integer> LIST = new java.util.LinkedList<>();

    List <Integer> randNums = genRandList(TEST_SZ);
    for (Integer value : randNums) {
      LIST.add(value);
      list.add(value);
    }

    Collections.shuffle(randNums);
    for (int i = 0; i < randNums.size()-1; i++ ) {

      Integer rm_val = randNums.get(i);
      LIST.remove(rm_val);      
      list.remove(rm_val);
      
      java.util.Iterator <Integer> iter1 = LIST.iterator();
      java.util.Iterator <Integer> iter2 = list.iterator();
      while(iter1.hasNext()) assertEquals(iter1.next(), iter2.next());

    }

    assertEquals(lst.size(), 1);

  }

  @Test
  public void testRandomizedRemoveAt() {

    java.util.LinkedList <Integer> LST = new java.util.LinkedList<>();
    LinkedList <Integer> lst = new LinkedList<>();

    List <Integer> randNums = genRandList(TEST_SZ);
    for (Integer value : randNums) {
      LST.add(value);
      lst.add(value);
    }

    for (int i = 0; i < randNums.size()-1; i++ ) {
      
      int rm_index = (int)(lst.size() * (Math.random()));

      Integer num1 = LST.remove(rm_index);
      Integer num2 = lst.removeAt(rm_index);
      assertEquals(num1, num2);

      java.util.Iterator <Integer> iter1 = LST.iterator();
      java.util.Iterator <Integer> iter2 = lst.iterator();
      while(iter1.hasNext()) assertEquals(iter1.next(), iter2.next());

    }

  }

  // Generate a list of random numbers
  static List <Integer> genRandList(int sz) {
    List <Integer> lst = new ArrayList<>(sz);
    for (int i = 0; i < sz; i++)
      lst.add( (int) (Math.random()*250) );
    return lst;
  }

}

