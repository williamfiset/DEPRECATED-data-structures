import static org.junit.Assert.*;
import org.junit.*;

public class UnionFindTest {

  // @Before public void setup() { }

  @Test
  public void testNumberOfComponents() {

    UnionFind uf = new UnionFind(5);
    assertEquals(uf.numberOfComponents(), 5);

    uf.unify(0,1);
    assertEquals(uf.numberOfComponents(), 4);
    
    uf.unify(1,0);
    assertEquals(uf.numberOfComponents(), 4);

    uf.unify(1,2);
    assertEquals(uf.numberOfComponents(), 3);
    
    uf.unify(0,2);
    assertEquals(uf.numberOfComponents(), 3);

    uf.unify(2,1);
    assertEquals(uf.numberOfComponents(), 3);

    uf.unify(3,4);
    assertEquals(uf.numberOfComponents(), 2);

    uf.unify(4,3);
    assertEquals(uf.numberOfComponents(), 2);

    uf.unify(1,3);
    assertEquals(uf.numberOfComponents(), 1);

    uf.unify(4,0);
    assertEquals(uf.numberOfComponents(), 1);

  }

  @Test
  public void testComponentSize() {

    UnionFind uf = new UnionFind(5);
    assertEquals(uf.getComponentSize(0), 1);
    assertEquals(uf.getComponentSize(1), 1);
    assertEquals(uf.getComponentSize(2), 1);
    assertEquals(uf.getComponentSize(3), 1);
    assertEquals(uf.getComponentSize(4), 1);

    uf.unify(0,1);
    assertEquals(uf.getComponentSize(0), 2);
    assertEquals(uf.getComponentSize(1), 2);
    assertEquals(uf.getComponentSize(2), 1);
    assertEquals(uf.getComponentSize(3), 1);
    assertEquals(uf.getComponentSize(4), 1);    
    
    uf.unify(1,0);
    assertEquals(uf.getComponentSize(0), 2);
    assertEquals(uf.getComponentSize(1), 2);
    assertEquals(uf.getComponentSize(2), 1);
    assertEquals(uf.getComponentSize(3), 1);
    assertEquals(uf.getComponentSize(4), 1);      

    uf.unify(1,2);
    assertEquals(uf.getComponentSize(0), 3);
    assertEquals(uf.getComponentSize(1), 3);
    assertEquals(uf.getComponentSize(2), 3);
    assertEquals(uf.getComponentSize(3), 1);
    assertEquals(uf.getComponentSize(4), 1);    
    
    uf.unify(0,2);
    assertEquals(uf.getComponentSize(0), 3);
    assertEquals(uf.getComponentSize(1), 3);
    assertEquals(uf.getComponentSize(2), 3);
    assertEquals(uf.getComponentSize(3), 1);
    assertEquals(uf.getComponentSize(4), 1);    

    uf.unify(2,1);
    assertEquals(uf.getComponentSize(0), 3);
    assertEquals(uf.getComponentSize(1), 3);
    assertEquals(uf.getComponentSize(2), 3);
    assertEquals(uf.getComponentSize(3), 1);
    assertEquals(uf.getComponentSize(4), 1);    

    uf.unify(3,4);
    assertEquals(uf.getComponentSize(0), 3);
    assertEquals(uf.getComponentSize(1), 3);
    assertEquals(uf.getComponentSize(2), 3);
    assertEquals(uf.getComponentSize(3), 2);
    assertEquals(uf.getComponentSize(4), 2);
    
    uf.unify(4,3);
    assertEquals(uf.getComponentSize(0), 3);
    assertEquals(uf.getComponentSize(1), 3);
    assertEquals(uf.getComponentSize(2), 3);
    assertEquals(uf.getComponentSize(3), 2);
    assertEquals(uf.getComponentSize(4), 2);    

    uf.unify(1,3);
    assertEquals(uf.getComponentSize(0), 5);
    assertEquals(uf.getComponentSize(1), 5);
    assertEquals(uf.getComponentSize(2), 5);
    assertEquals(uf.getComponentSize(3), 5);
    assertEquals(uf.getComponentSize(4), 5);     

    uf.unify(4,0);
    assertEquals(uf.getComponentSize(0), 5);
    assertEquals(uf.getComponentSize(1), 5);
    assertEquals(uf.getComponentSize(2), 5);
    assertEquals(uf.getComponentSize(3), 5);
    assertEquals(uf.getComponentSize(4), 5);

  }

  @Test
  public void testGetSize() {

    UnionFind uf = new UnionFind(5);
    assertEquals(uf.getSize(), 5);
    uf.unify(0,1);
    uf.find(3);
    assertEquals(uf.getSize(), 5);
    uf.unify(1,2);
    assertEquals(uf.getSize(), 5);
    uf.unify(0,2);
    uf.find(1);
    assertEquals(uf.getSize(), 5);
    uf.unify(2,1);
    assertEquals(uf.getSize(), 5);
    uf.unify(3,4);
    uf.find(0);
    assertEquals(uf.getSize(), 5);
    uf.unify(4,3);
    uf.find(3);
    assertEquals(uf.getSize(), 5);
    uf.unify(1,3);
    assertEquals(uf.getSize(), 5);
    uf.find(2);
    uf.unify(4,0);
    assertEquals(uf.getSize(), 5);

  }

  @Test(expected=IllegalArgumentException.class)
  public void testBadUnionFindCreation1() {
    new UnionFind(-1);
  }

  @Test(expected=IllegalArgumentException.class)
  public void testBadUnionFindCreation2() {
    new UnionFind(-3463);
  }

  @Test(expected=IllegalArgumentException.class)
  public void testBadUnionFindCreation3() {
    new UnionFind(0);
  }

}

