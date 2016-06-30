import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

import java.util.*;

public class LinkedListTest {

  @Test
  public void testEmptyList() {
    LinkedList<Integer> list = new LinkedList<>();
    assertTrue(list.isEmpty());
    assertEquals(list.getSize(), 0);
  }
  
  @Test(expected=Exception.class)
  public void testRemoveFirstOfEmpty() {
    LinkedList<Integer> list = new LinkedList<>();
    list.removeFirst(); 
  }
  
  @Test(expected=Exception.class)
  public void testRemoveLastOfEmpty() {
    LinkedList<Integer> list = new LinkedList<>();
    list.removeLast(); 
  }
  
  @Test(expected=Exception.class)
  public void testPeekFirstOfEmpty() {
    LinkedList<Integer> list = new LinkedList<>();
    list.peekFirst(); 
  }
  
  @Test(expected=Exception.class)
  public void testPeekLastOfEmpty() {
    LinkedList<Integer> list = new LinkedList<>();
    list.peekLast(); 
  }
  
  @Test
  public void testAddFirst() {
    LinkedList<Integer> list = new LinkedList<>();
    list.addFirst(3);
    assertEquals(list.getSize(), 1);
    list.addFirst(5);
    assertEquals(list.getSize(), 2);
  }
  
  @Test
  public void testAddLast() {
    LinkedList<Integer> list = new LinkedList<>();
    list.addLast(3);
    assertEquals(list.getSize(), 1);
    list.addLast(5);
    assertEquals(list.getSize(), 2);
  }
  
  @Test
  public void testRemoveFirst() {
    LinkedList<Integer> list = new LinkedList<>();
    list.addFirst(3);
    assertTrue(list.removeFirst() == 3);
    assertTrue(list.isEmpty());
  }
  
  @Test
  public void testRemoveLast() {
    LinkedList<Integer> list = new LinkedList<>();
    list.addLast(4);
    assertTrue(list.removeLast() == 4);
    assertTrue(list.isEmpty());
  }
  
  @Test
  public void testPeekFirst() {
    LinkedList<Integer> list = new LinkedList<>();
    list.addFirst(4);
    assertTrue(list.peekFirst() == 4);
    assertEquals(list.getSize(), 1);
  }
  
  @Test
  public void testPeekLast() {
    LinkedList<Integer> list = new LinkedList<>();
    list.addLast(4);
    assertTrue(list.peekLast() == 4);
    assertEquals(list.getSize(), 1);
  }

  @Test
  public void testExhaustively() {
    LinkedList<Integer> list = new LinkedList<>();
    list.addFirst(2);
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

