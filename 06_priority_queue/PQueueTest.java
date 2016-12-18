import static org.junit.Assert.*;
import org.junit.*;
import java.util.*;

public class PQueueTest {

  static final int LOOPS = 500;

  /*
  @Before
  public void setup() {
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
    
    PQueue <Integer> q = new PQueue<>();
    Integer[] nums = { 3,2,5,6,7,9,4,8,1 };

    // Try manually creating heap
    for (int n : nums) q.add(n);
    for (int i = 1; i <= 9; i++)
      assertTrue( i == q.poll() );
    
    q.clear();

    // Try heapify constructor 
    q = new PQueue<>(nums);
    for (int i = 1; i <= 9; i++)
      assertTrue( i == q.poll() );

  }

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
  public void remove() {

    Integer[] nums = { 0,1,2,3,4,5,6,7,8,9,10 };
    PQueue <Integer> pq = new PQueue<>(nums);

    pq.remove(1);
    System.out.println(pq.heap);

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
  */

  public void testSequentialRemoving(Integer[] in, Integer[] removeOrder) {

    assertEquals(in.length, removeOrder.length);
    
    PQueue <Integer> pq = new PQueue<>(in);
    PriorityQueue <Integer> PQ = new PriorityQueue<>();
    for (int value : in) PQ.offer(value);

    for (int i = 0; i < removeOrder.length; i++) {
      
      int elem = removeOrder[i];

      assertTrue(pq.peek() == PQ.peek());
      pq.remove(elem);
      PQ.remove(elem); 
      assertTrue(pq.isMinHeap(0));

    }

    assertTrue(pq.isEmpty());

  }

  @Test
  public void testRemoving() {

    Integer [] in = {1,2,3,4,5,6,7};
    Integer [] removeOrder = { 1,3,6,4,5,7,2 };

    testSequentialRemoving(in, removeOrder);

    in = new Integer[] {1,2,3,4,5,6,7,8,9,10,11};
    removeOrder = new Integer[] {7,4,6,10,2,5,11,3,1,8,9};

    testSequentialRemoving(in, removeOrder);

    in = new Integer[] {8, 1, 3, 3, 5, 3};
    removeOrder = new Integer[] {3,3,5,8,1,3};

    testSequentialRemoving(in, removeOrder);

    in = new Integer[] {7, 7, 3, 1, 1, 2};
    removeOrder = new Integer[] {2, 7, 1, 3, 7, 1};

  }

  /*
  @Test
  public void testGeneralOperations1() {

    for (int i = 0; i < LOOPS; i++) {
      
      int sz = i;
      List <Integer> randNums = genRandList(sz);
      PriorityQueue <Integer> pq1 = new PriorityQueue<>();
      PQueue <Integer> pq2 = new PQueue<>();

      // Add all the elements to both priority queues
      for (Integer value : randNums) {
        pq1.offer(value);
        pq2.add(value);
      }

      while( !pq1.isEmpty() ) {

        assertTrue(pq2.isMinHeap(0));
        assertEquals( pq1.size(), pq2.size() );
        assertEquals( pq1.peek(), pq2.peek() );
        pq1.poll(); pq2.poll();
        assertEquals( pq1.peek(), pq2.peek() );
        assertEquals( pq1.size(), pq2.size() );
        assertTrue(pq2.isMinHeap(0));

      }

    }

  }
  */


  @Test
  public void testMinHeapMethod() {
    Integer[] nums = {1,1,7,7,3};
    PQueue <Integer> pq = new PQueue<>(nums);
    assertTrue(pq.isMinHeap(0));
  }

  /*
  @Test
  public void testGeneralOperations2() {

    for (int i = 0; i < LOOPS; i++) {
      
      int sz = i;
      List <Integer> randNums = genRandList(sz);
      PriorityQueue <Integer> pq1 = new PriorityQueue<>();
      PQueue <Integer> pq2 = new PQueue<>();

      // Add all the elements to both priority queues
      for (Integer value : randNums) {
        pq1.offer(value);
        pq2.add(value);
      }

      System.out.println("Entered Order: " + randNums);
      Collections.shuffle(randNums);
      System.out.println("Shuffled Order: " + randNums);
      System.out.println();
      int index = 0;

      while( !pq1.isEmpty() ) {

        int removeNum = randNums.get(index++);

        assertTrue(pq2.isMinHeap(0));
        assertEquals( pq1.size(), pq2.size() );
        assertEquals( pq1.peek(), pq2.peek() );
        pq1.remove(removeNum); pq2.remove(removeNum);
        assertEquals( pq1.peek(), pq2.peek() );
        assertEquals( pq1.size(), pq2.size() );
        assertTrue(pq2.isMinHeap(0));

      }

    }

  }
  */

  static List <Integer> genRandList(int sz) {
    List <Integer> lst = new ArrayList<>(sz);
    for (int i = 0; i < sz; i++)
      lst.add( (int) (Math.random()*100) );
    return lst;
  }

  static List <Integer> genUniqueRandList(int sz) {
    List <Integer> lst = new ArrayList<>(sz);
    for (int i = 0; i < sz; i++) lst.add( i );
    Collections.shuffle( lst );
    return lst;
  }  

}










