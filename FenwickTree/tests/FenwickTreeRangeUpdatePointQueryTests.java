import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class FenwickTreeRangeUpdatePointQueryTests {
	  static final int MIN_RAND_NUM = -1000;
	  static final int MAX_RAND_NUM = +1000;

	  static final int TEST_SZ = 1000;
	  static final int LOOPS = 1000;
	  
	  @Before
	  public void setup() { }
	  
	  @Test
	  public void testFenwickTreeRangeUpdatePointQuery(){
		  long[] val1={1,2,3,4,5,6};
		  long[] val2={0,0,0,0,0,0};
		  long[] val3={1,1,1,1,1,1};
		  long[] val4={-1,-1,-1,-1,-1,-1};
		  long[] val5={1,2,-3,-4,5,6};
		  
		  FenwickTreeRangeUpdatePointQuery obj1=new FenwickTreeRangeUpdatePointQuery(val1);
		  obj1.updateRange(2, 4, 10);
		  assertEquals(obj1.getPoint(1), 2);
		  assertEquals(obj1.getPoint(2), 13);
		  assertEquals(obj1.getPoint(3), 14);
		  assertEquals(obj1.getPoint(4), 15);
		  assertEquals(obj1.getPoint(5), 6);
		  
		  FenwickTreeRangeUpdatePointQuery obj2=new FenwickTreeRangeUpdatePointQuery(val2);
		  obj1.updateRange(2, 4, 10);
		  assertEquals(obj2.getPoint(1), 0);
		  assertEquals(obj2.getPoint(2), 10);
		  assertEquals(obj2.getPoint(3), 10);
		  assertEquals(obj2.getPoint(4), 10);
		  assertEquals(obj2.getPoint(5), 0);
		  
		  FenwickTreeRangeUpdatePointQuery obj3=new FenwickTreeRangeUpdatePointQuery(val3);
		  obj1.updateRange(2, 4, 10);
		  assertEquals(obj3.getPoint(1), 1);
		  assertEquals(obj3.getPoint(2), 11);
		  assertEquals(obj3.getPoint(3), 11);
		  assertEquals(obj3.getPoint(4), 11);
		  assertEquals(obj3.getPoint(5), 1);
		  
		  FenwickTreeRangeUpdatePointQuery obj4=new FenwickTreeRangeUpdatePointQuery(val4);
		  obj1.updateRange(2, 4, 10);
		  assertEquals(obj4.getPoint(1), -1);
		  assertEquals(obj4.getPoint(2), 9);
		  assertEquals(obj4.getPoint(3), 9);
		  assertEquals(obj4.getPoint(4), 9);
		  assertEquals(obj4.getPoint(5), -1);
		  
		  FenwickTreeRangeUpdatePointQuery obj5=new FenwickTreeRangeUpdatePointQuery(val5);
		  obj1.updateRange(2, 4, 10);
		  assertEquals(obj5.getPoint(1), 2);
		  assertEquals(obj5.getPoint(2), 7);
		  assertEquals(obj5.getPoint(3), 6);
		  assertEquals(obj5.getPoint(4), 15);
		  assertEquals(obj5.getPoint(5), 6);
	  }

}
