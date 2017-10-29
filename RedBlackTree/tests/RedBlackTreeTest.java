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

  // @Test(expected=IllegalArgumentException.class)
  // public void testNullInsertion() {
  //   tree.insert(null);
  // }

  @Test
  public void testTreeContainsNull() {
    assertFalse(tree.contains(null));
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













