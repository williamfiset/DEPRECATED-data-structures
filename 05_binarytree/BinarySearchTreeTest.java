import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Random;
 
public class BinarySearchTreeTest {

  @Before
  public void setup() {
    
  }

  @Test public void testIsEmpty() {
    
    BinarySearchTree <String> tree = new BinarySearchTree<>();
    assertTrue( tree.isEmpty() );
    
    tree.add("Hello World!");
    assertFalse( tree.isEmpty() );
    
  }

  @Test public void testSize() {
    BinarySearchTree <String> tree = new BinarySearchTree<>();
    assertEquals( tree.getSize(), 0 );
    
    tree.add("Hello World!");
    assertEquals( tree.getSize(), 1 );
  }

  @Test public void testHeight() {
    BinarySearchTree <String> tree = new BinarySearchTree<>();
    
    // Tree should look like:
    //        M
    //      J  S
    //    B   N Z
    //  A
    
    // No tree
    assertEquals( tree.height(), 0 );
    
    // Layer One
    tree.add("M");
    assertEquals( tree.height(), 1 );
    
    // Layer Two
    tree.add("J");
    assertEquals( tree.height(), 2 );
    tree.add("S");
    assertEquals( tree.height(), 2 );    
    
    // Layer Three
    tree.add("B");
    assertEquals( tree.height(), 3 );
    tree.add("N");
    assertEquals( tree.height(), 3 );        
    tree.add("Z");
    assertEquals( tree.height(), 3 );
    
    // Layer 4
    tree.add("A");
    assertEquals( tree.height(), 4 );    
    
  }

  @Test public void testRemove() {
    
  }

  @Test public void testFind() {
    
  }
  
  // Tests a mixture of methods working together
  @Test public void testGeneralCase() {
    
  }

  public boolean validateTreeTraversal( TreeTraversalOrder trav_order, Integer[] in, Integer[] expected ) {
    
    if (in.length != expected.length)
      System.out.println("ARRAYS NOT THE SAME LENGTH!");
    
    int i = 0;
    int N = in.length;
    Integer[] out = new Integer[N];
    BinarySearchTree <Integer> tree = new BinarySearchTree<>();
    
    for (Integer v : in ) tree.add(v);
    Iterator <Integer> iter = tree.traverse(trav_order);
    
    while(iter.hasNext()) {
      out[i] = iter.next();
      i++;
    }
    for (i = 0; i < N; i++ )
      if ( !expected[i].equals(out[i]) )
        return false;
    return true;
    
  }
  
  // Process self, then left subtree, then right subtree
  @Test public void testPreOrderTraversal() {
    
    Integer[] in = new Integer[] {  };
    Integer[] ex = new Integer[] {  };
    assertTrue( validateTreeTraversal(TreeTraversalOrder.PRE_ORDER, in, ex) );
    
    in = new Integer[] { 4 };
    ex = new Integer[] { 4 };
    assertTrue( validateTreeTraversal(TreeTraversalOrder.PRE_ORDER, in, ex) );        
    
    in = new Integer[] { 10, 5, 11, 3, 7 };
    ex = new Integer[] { 10, 5, 3, 7, 11 };
    assertTrue( validateTreeTraversal(TreeTraversalOrder.PRE_ORDER, in, ex) );
    
  }
  
  // Process left subtree, then self, then right
  @Test public void testInOrderTraversal() {
    
    Integer[] in = new Integer[] {  };
    Integer[] ex = new Integer[] {  };
    assertTrue( validateTreeTraversal(TreeTraversalOrder.IN_ORDER, in, ex) );
    
    in = new Integer[] { 4 };
    ex = new Integer[] { 4 };
    assertTrue( validateTreeTraversal(TreeTraversalOrder.IN_ORDER, in, ex) );        
    
    in = new Integer[] { 10, 5, 11, 3, 7 };
    ex = new Integer[] { 3, 5, 7, 10, 11 };
    assertTrue( validateTreeTraversal(TreeTraversalOrder.IN_ORDER, in, ex) );

    in = new Integer[] { 31, 29, 30, 27, 15, 16, 20, 21, 19, 18 };
    ex = new Integer[] { 15, 16, 18, 19, 20, 21, 27, 29, 30, 31 };
    assertTrue( validateTreeTraversal(TreeTraversalOrder.IN_ORDER, in, ex) );
    
    in = new Integer[] { -40, 60, 30, 3, 66, 33, 24, 100, 110, 111, 133, 122, -3, -5 };
    ex = new Integer[] { -40, -5, -3, 3, 24, 30, 33, 60, 66, 100, 110, 111, 122, 133 };
    assertTrue( validateTreeTraversal(TreeTraversalOrder.IN_ORDER, in, ex) );
    
    // Generate random numbers and see if they come out in order
    Random rand = new Random();
    int min_val = -100000, max_val = 100000, num_elems = 100, num_tests = 3000;
    
    for (int i = 0; i < num_tests; i++) {
      in = new Integer[num_elems];
      ex = new Integer[num_elems];
      for (int j = 0; j < num_elems; j++ ) {
        int random_num = min_val + rand.nextInt((max_val - min_val) + 1);
        in[j] = ex[j] = random_num;
      }
      Arrays.sort(ex);
      assertTrue( validateTreeTraversal(TreeTraversalOrder.IN_ORDER, in, ex) );
    }

  }

  // Process left subtree, then right subtree, then left
  @Test public void testPostOrderTraversal() {

    Integer[] in = new Integer[] {  };
    Integer[] ex = new Integer[] {  };
    assertTrue( validateTreeTraversal(TreeTraversalOrder.POST_ORDER, in, ex) );
    
    in = new Integer[] { 4 };
    ex = new Integer[] { 4 };
    assertTrue( validateTreeTraversal(TreeTraversalOrder.POST_ORDER, in, ex) );    
    
    in = new Integer[] { 40, 25, 78, 10, 32 };
    ex = new Integer[] { 10, 32, 25, 78, 40 };
    assertTrue( validateTreeTraversal(TreeTraversalOrder.POST_ORDER, in, ex) );

    in = new Integer[] { 30, 25, 10, 27, 26, 40, 35, 33 };
    ex = new Integer[] { 10, 26, 27, 25, 33, 35, 40, 30 };
    assertTrue( validateTreeTraversal(TreeTraversalOrder.POST_ORDER, in, ex) );

  }  

  // Process left subtree, then right subtree, then left
  @Test public void testLevelOrderTraversal() {
    
    Integer[] in = new Integer[] {  };
    Integer[] ex = new Integer[] {  };
    assertTrue( validateTreeTraversal(TreeTraversalOrder.LEVEL_ORDER, in, ex) );
    
    in = new Integer[] { 4 };
    ex = new Integer[] { 4 };
    assertTrue( validateTreeTraversal(TreeTraversalOrder.LEVEL_ORDER, in, ex) ); 
    
    in = new Integer[] { 40, 78, 25, 10, 32 };
    ex = new Integer[] { 40, 25, 78, 10, 32 };
    assertTrue( validateTreeTraversal(TreeTraversalOrder.LEVEL_ORDER, in, ex) );
    
    in = new Integer[] { 30, 25, 10, 27, 26, 40, 35, 33 };
    ex = new Integer[] { 30, 25, 40, 10, 27, 35, 26, 33 };
    assertTrue( validateTreeTraversal(TreeTraversalOrder.LEVEL_ORDER, in, ex) );    
    
  } 

}












