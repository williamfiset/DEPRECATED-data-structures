import static org.junit.Assert.*;
import org.junit.*;
import java.util.*;

public class PQueueTest {

  static final int LOOPS = 500;
  
  @Before
  public void setup() { }

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
  public void testClear() {

    PQueue <String> q;
    String[] strs = {"aa", "bb", "cc", "dd", "ee"};
    q = new PQueue<>(strs);
    q.clear();
    assertEquals(q.size(), 0);
    assertTrue(q.isEmpty());

  }

  public void sequentialRemoving(Integer[] in, Integer[] removeOrder) {

    assertEquals(in.length, removeOrder.length);
    
    PQueue <Integer> pq = new PQueue<>(in);
    PriorityQueue <Integer> PQ = new PriorityQueue<>();
    for (int value : in) PQ.offer(value);

    for (int i = 0; i < removeOrder.length; i++) {
      
      int elem = removeOrder[i];

      assertTrue(pq.peek() == PQ.peek());
      pq.remove(elem);
      PQ.remove(elem);
      assertTrue(pq.size() == PQ.size());
      assertTrue(pq.isMinHeap(0));

    }

    assertTrue(pq.isEmpty());

  }

  @Test
  public void testRemoving() {

    Integer [] in = {1,2,3,4,5,6,7};
    Integer [] removeOrder = { 1,3,6,4,5,7,2 };
    sequentialRemoving(in, removeOrder);

    in = new Integer[] {1,2,3,4,5,6,7,8,9,10,11};
    removeOrder = new Integer[] {7,4,6,10,2,5,11,3,1,8,9};
    sequentialRemoving(in, removeOrder);

    in = new Integer[] {8, 1, 3, 3, 5, 3};
    removeOrder = new Integer[] {3,3,5,8,1,3};
    sequentialRemoving(in, removeOrder);

    in = new Integer[] {7, 7, 3, 1, 1, 2};
    removeOrder = new Integer[] {2, 7, 1, 3, 7, 1};
    sequentialRemoving(in, removeOrder);

    in = new Integer[] {32, 66, 93, 42, 41, 91, 54, 64, 9, 35};
    removeOrder = new Integer[] {64, 93, 54, 41, 35, 9, 66, 42, 32, 91};
    sequentialRemoving(in, removeOrder);

  }

  @Test
  public void testRandomizedPolling() {

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
        
        pq1.poll();
        pq2.poll();
        
        assertEquals( pq1.peek(), pq2.peek() );
        assertEquals( pq1.size(), pq2.size() );
        assertTrue(pq2.isMinHeap(0));

      }

    }

  }

  @Test
  public void testRandomizedRemoving() {

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

      Collections.shuffle(randNums);
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

  @Test
  public void testPQReusability() {

    List <Integer> SZs = genUniqueRandList(LOOPS);

    PriorityQueue <Integer> PQ = new PriorityQueue<>();
    PQueue <Integer> pq = new PQueue<>();

    for (int sz : SZs) {
      
      pq.clear();
      PQ.clear();

      List <Integer> nums = genRandList(sz);
      for (int n : nums) {
        pq.add(n);
        PQ.add(n);
      }

      Collections.shuffle(nums);

      for (int i = 0; i < sz/2; i++) {
        
        // Sometimes add a new number into the Pqueue
        if (0.25 < Math.random()) {
          int randNum = (int) (Math.random() * 10000);
          PQ.add(randNum);
          pq.add(randNum);
        }

        int removeNum = nums.get(i);

        assertTrue(pq.isMinHeap(0));
        assertEquals( PQ.size(), pq.size() );
        assertEquals( PQ.peek(), pq.peek() );

        PQ.remove(removeNum);
        pq.remove(removeNum);
        
        assertEquals( PQ.peek(), pq.peek() );
        assertEquals( PQ.size(), pq.size() );
        assertTrue(pq.isMinHeap(0));

      }

    }

  }

  // Generate a list of random numbers
  static List <Integer> genRandList(int sz) {
    List <Integer> lst = new ArrayList<>(sz);
    for (int i = 0; i < sz; i++)
      lst.add( (int) (Math.random()*250) );
    return lst;
  }

  // Generate a list of unique random numbers
  static List <Integer> genUniqueRandList(int sz) {
    List <Integer> lst = new ArrayList<>(sz);
    for (int i = 0; i < sz; i++) lst.add( i );
    Collections.shuffle( lst );
    return lst;
  }  

}










