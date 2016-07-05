import static org.junit.Assert.*;
import org.junit.*;

public class PQueueTest {

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

  @Test
  public void testPeekAndPoll() {

    PQueue<String> q = new PQueue<>();
    
    q.add("a");
    assertEquals(q.peek(), "a");
    assertEquals(q.size(), 1);

    q.add("b");
    assertEquals(q.peek(), "b");
    assertEquals(q.size(), 2);

    q.add("c");
    assertEquals(q.peek(), "c");
    assertEquals(q.size(), 3);

    q.add("d");
    assertEquals(q.peek(), "d");
    assertEquals(q.size(), 4);

    assertEquals(q.poll(), "d");
    assertEquals(q.size(), 3);

    assertEquals(q.poll(), "c");
    assertEquals(q.size(), 2);

    assertEquals(q.poll(), "b");
    assertEquals(q.size(), 1);

    assertEquals(q.poll(), "a");
    assertEquals(q.size(), 0);

    // Redo ^^

    q.add("a");
    assertEquals(q.peek(), "a");
    assertEquals(q.size(), 1);

    q.add("b");
    assertEquals(q.peek(), "b");
    assertEquals(q.size(), 2);

    q.add("c");
    assertEquals(q.peek(), "c");
    assertEquals(q.size(), 3);

    q.add("d");
    assertEquals(q.peek(), "d");
    assertEquals(q.size(), 4);

    assertEquals(q.poll(), "d");
    assertEquals(q.size(), 3);

    assertEquals(q.poll(), "c");
    assertEquals(q.size(), 2);

    assertEquals(q.poll(), "b");
    assertEquals(q.size(), 1);

    assertEquals(q.poll(), "a");
    assertEquals(q.size(), 0);

  }

  @Test
  public void testClear() {

    PQueue<String> q;
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

}










