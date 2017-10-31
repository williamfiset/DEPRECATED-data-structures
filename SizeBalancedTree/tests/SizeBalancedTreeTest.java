import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;
import java.util.*;

public class SizeBalancedTreeTest {

  static final int LOOPS = 1000;

  SizeBalancedTree<Integer> tree;

  @Before
  public void setup() {
    tree = new SizeBalancedTree<>();
  }

  @Test 
  public void testIsEmpty() {
    assertTrue(tree.isEmpty());
  }

  @Test 
  public void testSize() {
    assertEquals(tree.size(), 0);
    tree.add(78);
    assertEquals(tree.size(), 1);
    tree.add(90);
    assertEquals(tree.size(), 2);
  }

}

