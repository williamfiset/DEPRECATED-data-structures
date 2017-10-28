import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.*;

public class AVLTreeTest {

  static final int MAX_RAND_NUM = +100000;
  static final int MIN_RAND_NUM = -100000;

  static final int TEST_SZ = 5000;

  private AVLTree<Integer> tree;

  @Before
  public void setup() {
    tree = new AVLTree<>();
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
  public void testLeftLeftCase() {
    
    tree.insert(3);
    tree.insert(2);
    tree.insert(1);

    assertEquals(2, tree.root.value.intValue());
    assertEquals(1, tree.root.left.value.intValue());
    assertEquals(3, tree.root.right.value.intValue());

    assertNull(tree.root.left.left);
    assertNull(tree.root.left.right);
    assertNull(tree.root.right.left);
    assertNull(tree.root.right.right);

  }

  @Test
  public void testLeftRightCase() {

    tree.insert(3);
    tree.insert(1);
    tree.insert(2);

    assertEquals(2, tree.root.value.intValue());
    assertEquals(1, tree.root.left.value.intValue());
    assertEquals(3, tree.root.right.value.intValue());

    assertNull(tree.root.left.left);
    assertNull(tree.root.left.right);
    assertNull(tree.root.right.left);
    assertNull(tree.root.right.right);
    
  }

  @Test
  public void testRightRightCase() {

    tree.insert(1);
    tree.insert(2);
    tree.insert(3);

    assertEquals(2, tree.root.value.intValue());
    assertEquals(1, tree.root.left.value.intValue());
    assertEquals(3, tree.root.right.value.intValue());

    assertNull(tree.root.left.left);
    assertNull(tree.root.left.right);
    assertNull(tree.root.right.left);
    assertNull(tree.root.right.right);
    
  }

  @Test
  public void testRightLeftCase() {

    tree.insert(1);
    tree.insert(3);
    tree.insert(2);

    assertEquals(2, tree.root.value.intValue());
    assertEquals(1, tree.root.left.value.intValue());
    assertEquals(3, tree.root.right.value.intValue());

    assertNull(tree.root.left.left);
    assertNull(tree.root.left.right);
    assertNull(tree.root.right.left);
    assertNull(tree.root.right.right);
    
  }

  @Test
  public void testRandomizedValueInsertionsAgainstTreeSet() {

    TreeSet<Integer> set = new TreeSet<>();
    for (int i = 0; i < TEST_SZ; i++) {
      int v = randValue();
      set.add(v);
      tree.insert(v);
      assertEquals(set.size(), tree.size());
      assertTrue(validateBSTInvarient(tree.root));
    }

  }

  static <T extends Comparable<T>> boolean validateBSTInvarient(AVLTree.Node<T> node) {
    if (node == null) return true;
    T val = node.value;
    boolean isValid = true;
    if (node.left != null)  isValid = isValid && node.left.value.compareTo(val)  < 0;
    if (node.right != null) isValid = isValid && node.right.value.compareTo(val) > 0;
    return v && validateBSTInvarient(node.left) && validateBSTInvarient(node.right);
  }

  @Test
  public void testTreeHeight() {
    for (int n = 1; n <= TEST_SZ; n++) {
      
      tree.insert(randValue());
      int height = tree.height();

      // Get an upper bound on what the maximum height of
      // an AVL tree should be. Values were taken from:
      // https://en.wikipedia.org/wiki/AVL_tree#Comparison_to_other_structures
      double c = 1.441;
      double b = -0.329;
      double upperBound = c*(Math.log(n+2.0)/Math.log(2)) + b;

      assertTrue(height < upperBound);

    }
  }

  public static int randValue() {
    return (int)(Math.random() * MAX_RAND_NUM*2) + MIN_RAND_NUM;    
  }

}













