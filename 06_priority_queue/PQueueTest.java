import static org.junit.Assert.*;
import org.junit.*;
import java.util.*;

public class PQueueTest {

  /*
  @Before
  public void setup() {
    // list = new LinkedList<>();
  }

  @Test
  public void addingTest() {
    PQueue<Integer> q = new PQueue<>();
    q.add(1);
    q.add(2);
    q.add(3);
    q.add(4);
    q.add(5);
    
  }

  @Test
  public void testEmpty() {
    PQueue<Integer> q = new PQueue<>();
    assertEquals(q.size(), 0);
    assertTrue(q.isEmpty());
    assertEquals(q.poll(), null);
    assertEquals(q.peek(), null);
  }

  @Test
  public void testHeapProperty () {
    
    PQueue<Integer> q = new PQueue<>();
    int[] nums = { 3,2,5,6,7,9,4,8,1 };

    // Try manually creating heap
    for (int n : nums) q.add(n);
    for (int i = 1; i <= 9; i++)
      assertTrue( q.poll() == i );
    
    // Try heapify constructor 
    Integer [] numbers = new Integer[9];
    for (int i = 0; i < 9; i++) numbers[i] = nums[i];
    q = new PQueue<>(numbers);

    for (int i = 1; i <= 9; i++)
      assertTrue( q.poll() == i );

  }
  */

  @Test
  public void testPeekAndPoll() {

    PQueue<String> q = new PQueue<>();
    
    // Add elements
    q.add("a"); 
    assertEquals("a", q.peek());
    assertEquals(1, q.size());
    q.add("b"); 
    assertEquals("a", q.peek());
    assertEquals(2, q.size());
    q.add("c"); 
    assertEquals("a", q.peek());
    assertEquals(3, q.size());
    q.add("d"); 
    assertEquals("a", q.peek());
    assertEquals(4, q.size());

    // Remove elements
    assertEquals("a", q.poll());
    assertEquals(3, q.size());
    
    assertEquals("b", q.poll());
    assertEquals(2, q.size());
    
    assertEquals("c", q.poll());
    assertEquals(1, q.size());
    
    assertEquals("d", q.poll());
    assertEquals(0, q.size());
    

    // Redo ^^

    // Add elements
    q.add("a"); 
    assertEquals("a", q.peek());
    assertEquals(1, q.size());
    q.add("b"); 
    assertEquals("a", q.peek());
    assertEquals(2, q.size());
    q.add("c"); 
    assertEquals("a", q.peek());
    assertEquals(3, q.size());
    q.add("d"); 
    assertEquals("a", q.peek());
    assertEquals(4, q.size());

    // Remove elements
    assertEquals("a", q.poll());
    assertEquals(3, q.size());
    
    assertEquals("b", q.poll());
    assertEquals(2, q.size());
    
    assertEquals("c", q.poll());
    assertEquals(1, q.size());
    
    assertEquals("d", q.poll());
    assertEquals(0, q.size());
    

    // ^^^ Redo

    // Add elements
    q.add("a"); 
    assertEquals("a", q.peek());
    assertEquals(1, q.size());
    q.add("b"); 
    assertEquals("a", q.peek());
    assertEquals(2, q.size());
    q.add("c"); 
    assertEquals("a", q.peek());
    assertEquals(3, q.size());
    q.add("d"); 
    assertEquals("a", q.peek());
    assertEquals(4, q.size());

    // Remove elements
    assertEquals("a", q.poll());
    assertEquals(3, q.size());
    assertEquals("b", q.poll());
    assertEquals(2, q.size());
    assertEquals("c", q.poll());
    assertEquals(1, q.size());
    assertEquals("d", q.poll());
    assertEquals(0, q.size());

  }



  @Test
  public void testClear() {

    PQueue <String> q;
    String[] strs = {"aa", "bb", "cc", "dd", "ee"};
    
    q = new PQueue<>(strs);
    q.clear();
    assertEquals(q.size(), 0);
    assertTrue(q.isEmpty());

    // Repeat ^^ 

    q = new PQueue<>(strs);
    q.clear();
    assertEquals(q.size(), 0);
    assertTrue(q.isEmpty());

  }

  @Test
  public void testPolling1() {

    Integer[] nums = {  };
    PQueue <Integer> pq = new PQueue<>(nums);
    pq.add(4);
    assertTrue( 4 == pq.poll() );

    pq.add(5);
    pq.add(8);

    assertTrue( 5 == pq.poll() );
    assertTrue( 8 == pq.poll() );

  }

  @Test
  public void testRandomOperations() {

    for (int i = 0; i < 500; i++) {
      
      int sz = i;
      List <Integer> randNums = genUniqueRandList(sz);
      PriorityQueue <Integer> pq1 = new PriorityQueue<>();
      PQueue <Integer> pq2 = new PQueue<>();

      // Add all the elements to both priority queues
      for (Integer value : randNums) {
        pq1.offer(value);
        pq2.add(value);
      }

      while( !pq1.isEmpty() ) {

        assertEquals( pq1.size(), pq2.size() );
        assertEquals( pq1.peek(), pq2.peek() );
        pq1.poll(); pq2.poll();
        assertEquals( pq1.peek(), pq2.peek() );
        assertEquals( pq1.size(), pq2.size() );

      }

    }

  }

  static List <Integer> genRandList(int sz) {
    List <Integer> lst = new ArrayList<>(sz);
    for (int i = 0; i < sz; i++)
      lst.add( (int) (Math.random()*100000) );
    return lst;
  }

  static List <Integer> genUniqueRandList(int sz) {
    List <Integer> lst = new ArrayList<>(sz);
    for (int i = 0; i < sz; i++) lst.add( i );
    Collections.shuffle( lst );
    return lst;
  }  

}










