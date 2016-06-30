
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class ArrayTest {
  
  @Test
  public void testEmptyList() {
    Array<Integer> list = new Array<>();
    assertTrue(list.isEmpty());
  }

  @Test(expected=Exception.class)
  public void testRemovingEmpty() {
    Array<Integer> list = new Array<>();
    list.removeAt(0);
  }

  @Test(expected=Exception.class)
  public void testIndexOutOfBounds() {
    Array<Integer> list = new Array<>();
    list.add(-56);
    list.add(-53);
    list.add(-55);
    list.removeAt(3);
  }

  @Test(expected=Exception.class)
  public void testIndexOutOfBounds2() {
    Array<Integer> list = new Array<>();
    for (int i = 0; i < 1000; i++)
      list.add(789);
    list.removeAt(1000);
  }

  @Test(expected=Exception.class)
  public void testIndexOutOfBounds3() {
    Array<Integer> list = new Array<>();
    for (int i = 0; i < 1000; i++)
      list.add(789);
    list.removeAt(-1);
  }

  @Test(expected=Exception.class)
  public void testIndexOutOfBounds4() {
    Array<Integer> list = new Array<>();
    for (int i = 0; i < 15; i++)
      list.add(123);
    list.removeAt(-66);
  }


  @Test
  public void testRemoving() {

    Array<String> list = new Array<>();
    String[] strs = {"a", "b", "c", "d", "e", "f", "g", "h"};
    for (String s : strs)
      list.add(s);
    
    boolean ret = list.remove("c");
    assertTrue(ret);
    ret = list.remove("c");
    assertFalse(ret);
    ret = list.remove("h");
    assertTrue(ret);
    ret = list.remove("a");
    assertTrue(ret);
    ret = list.remove("a");
    assertFalse(ret);
    ret = list.remove("h");
    assertFalse(ret);

  }

  @Test
  public void testAddingElements() {
    
    Array<Integer> list = new Array<>();
    
    int[] elems = {1,2,3,4,5,6,7};
    
    for (int i = 0; i < elems.length; i++)
      list.add(elems[i]);
    
    for (int i = 0; i < elems.length; i++)
      assertEquals( list.get(i).intValue(), elems[i] );

  }


  @Test
  public void testGetSize() {
    
    Array<Integer> list = new Array<>();
    
    int[] elems = {-76, 45, 66, 3, 234, 54, 33};
    for (int i = 0, sz = 1; i < elems.length; i++, sz++) {
      list.add(elems[i]);
      assertEquals(list.getSize(), sz);
    }

  }

}









