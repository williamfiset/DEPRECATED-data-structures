import org.junit.Test;
import org.junit.Before;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.junit.runner.RunWith;
import static org.junit.Assert.assertTrue;
// import static org.junit.Assert.assertEquals;
import static org.junit.Assert.*;

import java.util.Iterator;
 
public class BinarySearchTreeTest {

  @Before
  public void setup() {
    
  }

  @Test public void adding() {
    
    BinarySearchTree <Integer> tree = new BinarySearchTree<>();
    tree.add(7);
    tree.add(2);
    tree.add(1);
    
    assertTrue( tree.root.data == 7 ); 
    assertTrue( tree.root.left.data == 2 ); 
    assertTrue( tree.root.left.left.data == 1 ); 
    assertTrue( tree.root.left.left.left == null ); 
    assertTrue( tree.root.left.left.right == null ); 
    
  }

  public boolean validateTreeTraversal( TreeTraversalOrder trav_order, Integer[] in, Integer[] expected ) {
    
    int i = 0;
    int N = in.length;
    Integer[] out = new Integer[N];
    BinarySearchTree <Integer> tree = new BinarySearchTree<>();
    
    for (Integer v : in ) tree.add(v);
    Iterator <Integer> iter = tree.traverse(trav_order);
    
    while(iter.hasNext()) {
      out[i] = iter.next();
      System.out.println(out[i]);
      i++;
    }
    for (i = 0; i < N; i++ )
      if (expected[i] != out[i])
        return false;
    return true;
    
  }
  
  // Process self, then left subtree, then right subtree
  @Test public void testPreOrderTraversal() {
    
    Integer[] in = new Integer[] { 10, 5, 11, 3, 7 };
    Integer[] ex = new Integer[] { 10, 5, 3, 7, 11 };
    assertTrue( validateTreeTraversal(TreeTraversalOrder.PRE_ORDER, in, ex) );
    
  }
  
  // Process left subtree, then self, then right
  @Test public void testInOrderTraversal() {
    
    Integer[] in = new Integer[] { 10, 5, 11, 3, 7 };
    Integer[] ex = new Integer[] { 3, 5, 7, 10, 11 };
    assertTrue( validateTreeTraversal(TreeTraversalOrder.IN_ORDER, in, ex) );
    
  }

  // Process left subtree, then right subtree, then left
  @Test public void testPostOrderTraversal() {
    
    Integer[] in = new Integer[] { 40, 25, 78, 10, 32 };
    Integer[] ex = new Integer[] { 10, 32, 25, 78, 40 };
    assertTrue( validateTreeTraversal(TreeTraversalOrder.POST_ORDER, in, ex) );
    
  }  

  // Process left subtree, then right subtree, then left
  @Test public void testLevelOrderTraversal() {
    
    Integer[] in = new Integer[] { 40, 78, 25, 10, 32 };
    Integer[] ex = new Integer[] { 40, 25, 78, 10, 32 };
    assertTrue( validateTreeTraversal(TreeTraversalOrder.LEVEL_ORDER, in, ex) );
    
  } 

}












