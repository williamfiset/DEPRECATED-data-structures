import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

public class QueueTest {

  Queue<Integer> queue;

  @Before
  public void setup() {
    queue = new Queue<Integer>();
  }

  @Test
  public void testEmptyQueue() {
    assertTrue(queue.isEmpty());
    assertEquals(queue.getSize(), 0);
  }
  
  @Test(expected=Exception.class)
  public void testPollOnEmpty() {
    queue.poll(); 
  }
  
  @Test(expected=Exception.class)
  public void testPeekOnEmpty() {
    queue.peek(); 
  }
 
  @Test
  public void testOffer() {
    queue.offer(2);
    assertEquals(queue.getSize(), 1);
  }
  
  @Test
  public void testPeek() {
    queue.offer(2);
    assertTrue(queue.peek() == 2);
    assertEquals(queue.getSize(), 1);
  }
  
  @Test
  public void testPoll() {
    queue.offer(2);
    assertTrue(queue.poll() == 2);
    assertEquals(queue.getSize(), 0);
  }
  
  @Test
  public void testExhaustively() {
    assertTrue(queue.isEmpty());
    queue.offer(1);
    assertTrue(!queue.isEmpty());
    queue.offer(2);
    assertEquals(queue.getSize(), 2);
    assertTrue(queue.peek() == 1);
    assertEquals(queue.getSize(), 2);
    assertTrue(queue.poll() == 1);
    assertEquals(queue.getSize(), 1);
    assertTrue(queue.peek() == 2);
    assertEquals(queue.getSize(), 1);
    assertTrue(queue.poll() == 2);
    assertEquals(queue.getSize(), 0);
    assertTrue(queue.isEmpty());
  }

}
