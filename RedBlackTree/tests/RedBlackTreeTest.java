import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.*;

public class RedBlackTreeTest {

  static final int MAX_RAND_NUM = +100000;
  static final int MIN_RAND_NUM = -100000;

  static final int TEST_SZ = 2500;

  private RedBlackTree<Integer> tree;

  @Before
  public void setup() {
    tree = new RedBlackTree<>();
  }

  @Test(expected=IllegalArgumentException.class)
  public void testNullInsertion() {
    tree.insert(null);
  }

  @Test
  public void testTreeContainsNull() {
    assertFalse(tree.contains(null));
  }

  @Test
  public void testLeftLeftRotation() {

    tree.insert(3);
    tree.insert(2);
    tree.insert(1);

    assertEquals(2, tree.root.value.intValue());
    assertEquals(1, tree.root.left.value.intValue());
    assertEquals(3, tree.root.right.value.intValue());

    assertEquals(RedBlackTree.BLACK, tree.root.color);
    assertEquals(RedBlackTree.RED, tree.root.left.color);
    assertEquals(RedBlackTree.RED, tree.root.right.color);

    assertEquals(tree.root, tree.root.left.parent);
    assertEquals(tree.root, tree.root.right.parent);

    assertNull(tree.root.parent);
    assertNull(tree.root.left.left);
    assertNull(tree.root.left.right);
    assertNull(tree.root.right.left);
    assertNull(tree.root.right.right);

  }

  @Test
  public void testLeftRightRotation() {

    tree.insert(3);
    tree.insert(1);
    tree.insert(2);

    assertEquals(2, tree.root.value.intValue());
    assertEquals(1, tree.root.left.value.intValue());
    assertEquals(3, tree.root.right.value.intValue());

    assertEquals(RedBlackTree.BLACK, tree.root.color);
    assertEquals(RedBlackTree.RED, tree.root.left.color);
    assertEquals(RedBlackTree.RED, tree.root.right.color);

    assertEquals(tree.root, tree.root.left.parent);
    assertEquals(tree.root, tree.root.right.parent);

    assertNull(tree.root.parent);
    assertNull(tree.root.left.left);
    assertNull(tree.root.left.right);
    assertNull(tree.root.right.left);
    assertNull(tree.root.right.right);

  }

  @Test
  public void testRightLeftRotation() {

    tree.insert(1);
    tree.insert(3);
    tree.insert(2);

    assertEquals(2, tree.root.value.intValue());
    assertEquals(1, tree.root.left.value.intValue());
    assertEquals(3, tree.root.right.value.intValue());

    assertEquals(RedBlackTree.BLACK, tree.root.color);
    assertEquals(RedBlackTree.RED, tree.root.left.color);
    assertEquals(RedBlackTree.RED, tree.root.right.color);

    assertEquals(tree.root, tree.root.left.parent);
    assertEquals(tree.root, tree.root.right.parent);

    assertNull(tree.root.parent);
    assertNull(tree.root.left.left);
    assertNull(tree.root.left.right);
    assertNull(tree.root.right.left);
    assertNull(tree.root.right.right);

  }

  static List<Integer> genRandList(int sz) {
    List <Integer> lst = new ArrayList<>(sz);
    for (int i = 0; i < sz; i++) lst.add(i); // unique values.
    Collections.shuffle(lst);
    return lst;
  }

  public static int randValue() {
    return (int)(Math.random() * MAX_RAND_NUM*2) + MIN_RAND_NUM;    
  }

}













