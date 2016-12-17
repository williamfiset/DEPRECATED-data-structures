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
    q.add("a"); System.out.println(q.heap);
    assertEquals("a", q.peek());
    assertEquals(1, q.size());
    q.add("b"); System.out.println(q.heap);
    assertEquals("a", q.peek());
    assertEquals(2, q.size());
    q.add("c"); System.out.println(q.heap);
    assertEquals("a", q.peek());
    assertEquals(3, q.size());
    q.add("d"); System.out.println(q.heap);
    assertEquals("a", q.peek());
    assertEquals(4, q.size());

    // Remove elements
    assertEquals("a", q.poll());
    assertEquals(3, q.size());
    System.out.println(q.heap);
    assertEquals("b", q.poll());
    assertEquals(2, q.size());
    System.out.println(q.heap);
    assertEquals("c", q.poll());
    assertEquals(1, q.size());
    System.out.println(q.heap);
    assertEquals("d", q.poll());
    assertEquals(0, q.size());
    System.out.println(q.heap);

    // Redo ^^

    // Add elements
    q.add("a"); System.out.println(q.heap);
    assertEquals("a", q.peek());
    assertEquals(1, q.size());
    q.add("b"); System.out.println(q.heap);
    assertEquals("a", q.peek());
    assertEquals(2, q.size());
    q.add("c"); System.out.println(q.heap);
    assertEquals("a", q.peek());
    assertEquals(3, q.size());
    q.add("d"); System.out.println(q.heap);
    assertEquals("a", q.peek());
    assertEquals(4, q.size());

    // Remove elements
    assertEquals("a", q.poll());
    assertEquals(3, q.size());
    System.out.println(q.heap);
    assertEquals("b", q.poll());
    assertEquals(2, q.size());
    System.out.println(q.heap);
    assertEquals("c", q.poll());
    assertEquals(1, q.size());
    System.out.println(q.heap);
    assertEquals("d", q.poll());
    assertEquals(0, q.size());
    System.out.println(q.heap);

    // ^^^ Redo

    // Add elements
    q.add("a"); System.out.println(q.heap);
    assertEquals("a", q.peek());
    assertEquals(1, q.size());
    q.add("b"); System.out.println(q.heap);
    assertEquals("a", q.peek());
    assertEquals(2, q.size());
    q.add("c"); System.out.println(q.heap);
    assertEquals("a", q.peek());
    assertEquals(3, q.size());
    q.add("d"); System.out.println(q.heap);
    assertEquals("a", q.peek());
    assertEquals(4, q.size());

    // Remove elements
    assertEquals("a", q.poll());
    assertEquals(3, q.size());
    System.out.println(q.heap);
    assertEquals("b", q.poll());
    assertEquals(2, q.size());
    System.out.println(q.heap);
    assertEquals("c", q.poll());
    assertEquals(1, q.size());
    System.out.println(q.heap);
    assertEquals("d", q.poll());
    assertEquals(0, q.size());
    System.out.println(q.heap);    

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

  /*
  @Test
  public void testRandomOperations() {

    for (int i = 0; i < 500; i++) {
      
      int sz = i;
      List <Integer> randNums = genRandList(sz);
      PriorityQueue <Integer> pq1 = new PriorityQueue<>();
      PQueue <Integer> pq2 = new PQueue<>();

      for (Integer value : randNums) {
        pq1.offer(value);
        pq2.add(value);
      }

      assertEquals( pq1.size(), pq2.size() );
      
    }



  }

  static List <Integer> genRandList(int sz) {
    List <Integer> lst = new ArrayList<>(sz);
    for (int i = 0; i < sz; i++)
      lst.add( (int) (Math.random()*100000) );
    return lst;
  }
  */

}










