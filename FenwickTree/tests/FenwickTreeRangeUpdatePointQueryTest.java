import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class FenwickTreeRangeUpdatePointQueryTest {
    
    static final int MIN_RAND_NUM = -1000;
    static final int MAX_RAND_NUM = +1000;

    static final int TEST_SZ = 1000;
    static final int LOOPS = 1000;
    
    @Before
    public void setup() { }
    
    @Test
    public void testFenwickTreeRangeUpdatePointQuery1() {

      long[] values = {1,2,3,4,5,6};
      FenwickTreeRangeUpdatePointQuery ft = new FenwickTreeRangeUpdatePointQuery(values);
      ft.updateRange(2, 4, 10);
      assertEquals(2,  ft.getPoint(1));
      assertEquals(13, ft.getPoint(2));
      assertEquals(14, ft.getPoint(3));
      assertEquals(15, ft.getPoint(4));
      assertEquals(6,  ft.getPoint(5));
      
    }
    
    @Test
    public void testFenwickTreeRangeUpdatePointQuery2() {
      
      long[] values={0,0,0,0,0,0};
      FenwickTreeRangeUpdatePointQuery ft = new FenwickTreeRangeUpdatePointQuery(values);
      ft.updateRange(2, 4, 10);
      assertEquals(0, ft.getPoint(1));
      assertEquals(10, ft.getPoint(2));
      assertEquals(10, ft.getPoint(3));
      assertEquals(10, ft.getPoint(4));
      assertEquals(0, ft.getPoint(5));
    
    }
    
    @Test
    public void testFenwickTreeRangeUpdatePointQuery3() {   
    
      long[] values = {1,1,1,1,1,1};
      FenwickTreeRangeUpdatePointQuery ft=new FenwickTreeRangeUpdatePointQuery(values);
      ft.updateRange(2, 4, 10);
      assertEquals(1, ft.getPoint(1));
      assertEquals(11, ft.getPoint(2));
      assertEquals(11, ft.getPoint(3));
      assertEquals(11, ft.getPoint(4));
      assertEquals(1, ft.getPoint(5));
      
    }
      
    @Test
    public void testFenwickTreeRangeUpdatePointQuery4() {

      long[] values = {-1,-1,-1,-1,-1,-1};
      FenwickTreeRangeUpdatePointQuery ft = new FenwickTreeRangeUpdatePointQuery(values);
      ft.updateRange(2, 4, 10);
      assertEquals(-1, ft.getPoint(1));
      assertEquals( 9, ft.getPoint(2));
      assertEquals( 9, ft.getPoint(3));
      assertEquals( 9, ft.getPoint(4));
      assertEquals(-1, ft.getPoint(5));
      
    }
    
    @Test
    public void testFenwickTreeRangeUpdatePointQuery5() {
      
      long[] values={1,2,-3,-4,5,6};
      FenwickTreeRangeUpdatePointQuery ft = new FenwickTreeRangeUpdatePointQuery(values);
      ft.updateRange(2, 4, 10);
      assertEquals(2,  ft.getPoint(1));
      assertEquals(7,  ft.getPoint(2));
      assertEquals(6,  ft.getPoint(3));
      assertEquals(15, ft.getPoint(4));
      assertEquals(6,  ft.getPoint(5));
      
    }

}

