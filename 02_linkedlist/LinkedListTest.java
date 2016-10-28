import static org.junit.Assert.*;
import org.junit.*;

public class LinkedListTest {
  
  LinkedList<Integer> list;

  @Before
  public void setup() {
    list = new LinkedList<>();
  }

  @Test
  public void testEmptyList() {
    assertTrue(list.isEmpty());
    assertEquals(list.getSize(), 0);
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
    assertEquals(list.getSize(), 1);
    list.addFirst(5);
    assertEquals(list.getSize(), 2);
  }
  
  @Test
  public void testAddLast() {
    list.addLast(3);
    assertEquals(list.getSize(), 1);
    list.addLast(5);
    assertEquals(list.getSize(), 2);
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
    assertEquals(list.getSize(), 1);
  }
  
  @Test
  public void testPeekLast() {
    list.addLast(4);
    assertTrue(list.peekLast() == 4);
    assertEquals(list.getSize(), 1);
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
  public void testExhaustively() {
    assertTrue(list.isEmpty());
    list.addFirst(2);
    assertFalse(list.isEmpty());
    list.addFirst(1);
    list.addLast(3);
    assertTrue(list.peekFirst() == 1);
    assertTrue(list.peekLast() == 3);
    assertTrue(list.removeFirst() == 1);
    assertEquals(list.getSize(), 2);
    assertTrue(list.removeFirst() == 2);
    assertTrue(list.removeLast() == 3);
    assertTrue(list.isEmpty());
  }

}

