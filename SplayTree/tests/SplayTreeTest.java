import static org.junit.Assert.*;
import org.junit.*;
import java.util.*;

public class SplayTreeTest {

  private SplayTree<Integer> tree;

  @Before
  public void setup() {
    tree = new SplayTree<>();
  }

  @Test(expected=IllegalArgumentException.class)
  public void testAddingNullElement() {
    tree.insert(null);
  }

  @Test
  public void testContainsEmptyTree() {
    assertFalse(tree.contains(6));
    assertFalse(tree.contains(-5));
    assertFalse(tree.contains(0));
    assertFalse(tree.contains(123));
    assertFalse(tree.contains(99));
  }

  @Test
  public void testInsertionMethod() {
    tree.insert(43);
    assertTrue(tree.contains(43));
  }

  @Test
  public void testInsertionMethod2() {
    tree.insert(1);
    tree.insert(2);
    tree.insert(3);
    tree.insert(4);
    tree.insert(5);
    assertTrue(tree.contains(1));
    assertTrue(tree.contains(2));
    assertTrue(tree.contains(3));
    assertTrue(tree.contains(4));
    assertTrue(tree.contains(5));
  }

}

