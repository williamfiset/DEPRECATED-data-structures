import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import java.util.Iterator;

public class BinarySearchTreeTest {

  @Before
  public void setup() {
    
  }
  
  @Test
  public void preOrderTraversalTest() {
    
    BinarySearchTree <Integer> tree = new BinarySearchTree<>();
    Integer[] vals = { 43, 15, 60, 8, 30, 50, 82, 70 };
    
    for (Integer v : vals )
      tree.add(v);
    
    Iterator <Integer> iter = tree.traverse(TreeTraversalOrder.IN_ORDER);
    while(iter.hasNext()) {
      System.out.println("ELEM: " + iter.next());
    }    
    
  }
  
  @Test
  public void inOrderTraversalTest() {
    
    BinarySearchTree <Integer> tree = new BinarySearchTree<>();
    Integer[] vals = { 43, 15, 60, 8, 30, 50, 82, 70 };
    
    for (Integer v : vals )
      tree.add(v);
    
    Iterator <Integer> iter = tree.traverse(TreeTraversalOrder.IN_ORDER);
    while(iter.hasNext()) {
      System.out.println("ELEM: " + iter.next());
    }
    
  }

}
