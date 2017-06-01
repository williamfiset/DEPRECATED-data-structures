import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.*;

public class QuadTreeTest {

  static final int LOOPS = 50;
  static final int TEST_SZ = 1000;
  static final int MAX_RAND_NUM = +2000;

  @Before
  public void setup() {
    
  }

  @Test
  public void testRectIntersection() {

    Rect r1 = new Rect(0,0, 5,5);

    Rect r1Center   = new Rect(1,1, 4,4);
    Rect r1NWCorner = new Rect(-1,5, 0,6);
    Rect r1SWCorner = new Rect(-1,-1, 0,0);
    Rect r1SECorner = new Rect(5,-1, 6,0);
    Rect r1NECorner = new Rect(5,5, 6,6);
    Rect r1Above = new Rect(2,6, 3,8);
    Rect r1Below = new Rect(2,-5, 5,-1);
    Rect r1Left  = new Rect(-5,-4, -1,8);
    Rect r1Right = new Rect(6,-3, 7,8);

    assertTrue(r1.intersects(r1));

    assertTrue(r1.intersects(r1Center));
    assertTrue(r1Center.intersects(r1));

    assertTrue(r1.intersects(r1NWCorner));
    assertTrue(r1NWCorner.intersects(r1));

    assertTrue(r1.intersects(r1NECorner));
    assertTrue(r1NECorner.intersects(r1));

    assertTrue(r1.intersects(r1SECorner));
    assertTrue(r1SECorner.intersects(r1));

    assertTrue(r1.intersects(r1SWCorner));
    assertTrue(r1SWCorner.intersects(r1));

    assertFalse(r1.intersects(r1Above));
    assertFalse(r1Above.intersects(r1));

    assertFalse(r1.intersects(r1Below));
    assertFalse(r1Below.intersects(r1));

    assertFalse(r1.intersects(r1Left));
    assertFalse(r1Left.intersects(r1));

    assertFalse(r1.intersects(r1Right));
    assertFalse(r1Right.intersects(r1));

  }

  @Test
  public void testRectContainment() {

    Rect r1 = new Rect(0,0, 5,5);

    Rect r1Center   = new Rect(1,1, 4,4);
    Rect r1NWCorner = new Rect(-1,5, 0,6);
    Rect r1SWCorner = new Rect(-1,-1, 0,0);
    Rect r1SECorner = new Rect(5,-1, 6,0);
    Rect r1NECorner = new Rect(5,5, 6,6);
    Rect r1Above = new Rect(2,6, 3,8);
    Rect r1Below = new Rect(2,-5, 5,-1);
    Rect r1Left  = new Rect(-5,-4, -1,8);
    Rect r1Right = new Rect(6,-3, 7,8);

    assertTrue(r1.contains(r1));

    assertTrue(r1.contains(r1Center));
    assertFalse(r1Center.contains(r1));

    assertFalse(r1.contains(r1NWCorner));
    assertFalse(r1NWCorner.contains(r1));

    assertFalse(r1.contains(r1NECorner));
    assertFalse(r1NECorner.contains(r1));

    assertFalse(r1.contains(r1SECorner));
    assertFalse(r1SECorner.contains(r1));

    assertFalse(r1.contains(r1SWCorner));
    assertFalse(r1SWCorner.contains(r1));

    assertFalse(r1.contains(r1Above));
    assertFalse(r1Above.contains(r1));

    assertFalse(r1.contains(r1Below));
    assertFalse(r1Below.contains(r1));

    assertFalse(r1.contains(r1Left));
    assertFalse(r1Left.contains(r1));

    assertFalse(r1.contains(r1Right));
    assertFalse(r1Right.contains(r1));

  }

  @Test
  public void testPointContainment() {

    Rect r1 = new Rect(0,0, 5,5);

    // Corner check
    assertTrue(r1.contains(0,0));
    assertTrue(r1.contains(0,5));
    assertTrue(r1.contains(5,0));
    assertTrue(r1.contains(5,5));

    // Side check
    assertTrue(r1.contains(0, 1));
    assertTrue(r1.contains(0, 2));
    assertTrue(r1.contains(0, 3));
    assertTrue(r1.contains(0, 4));

    // Side check
    assertTrue(r1.contains(1, 0));
    assertTrue(r1.contains(2, 0));
    assertTrue(r1.contains(3, 0));
    assertTrue(r1.contains(4, 0));

    // Side check
    assertTrue(r1.contains(1, 5));
    assertTrue(r1.contains(2, 5));
    assertTrue(r1.contains(3, 5));
    assertTrue(r1.contains(4, 5));

    // Side check
    assertTrue(r1.contains(5, 1));
    assertTrue(r1.contains(5, 2));
    assertTrue(r1.contains(5, 3));
    assertTrue(r1.contains(5, 4));    

    // Inside check
    assertTrue(r1.contains(2, 3));
    assertTrue(r1.contains(1, 1));
    assertTrue(r1.contains(4, 3));
    assertTrue(r1.contains(3, 1));

    // Outside check
    assertFalse(r1.contains(-1, 3));
    assertFalse(r1.contains(-2, -2));
    assertFalse(r1.contains(6, 3));
    assertFalse(r1.contains(3, 6));
    assertFalse(r1.contains(3, -6));
    assertFalse(r1.contains(-3, 6));

  }

  @Test
  public void testCountingPoints() {

    final int SZ = 100;
    Rect region = new Rect(0, 0, SZ, SZ);
    QTNode quadTree = new QTNode(region);

    // Add points on a diagonal
    for (int i = 0; i <= SZ; i++)
      quadTree.add(i, i);

    // Query entire region there should be 101 points
    assertEquals( 101, quadTree.count(region) );

  }

  public int bruteForceCount(int[][] grid, int x1, int y1, int x2, int y2) {
    int sum = 0;
    for (int i = y1; i <= y2; i++)
      for (int j = x1; j <= x2; j++)
        sum += grid[i][j];
    return sum;
  }

  @Test
  public void randomizedQueryTests() {

    for (int test = 0; test < LOOPS; test++ ) {
      
      int W = 1 + (int) (Math.random() * MAX_RAND_NUM);
      int H = 1 + (int) (Math.random() * MAX_RAND_NUM);

      QTNode quadTree = new QTNode(new Rect(0,0,W,H));
      int[][] grid = new int[H+1][W+1];

      for (int i = 0; i < TEST_SZ; i++) {
        int x = (int)(Math.random() * (W+1) );
        int y = (int)(Math.random() * (H+1) );
        assertTrue(quadTree.add(x, y));
        grid[y][x]++;
        // System.out.printf("(%d, %d)\n",x,y);
      }

      // for (int i = H; i >= 0; i--) System.out.println(Arrays.toString(grid[i]));

      for (int i = 0; i < TEST_SZ;) {

        int x1 = (int)(Math.random() * (W) );
        int y1 = (int)(Math.random() * (H) );
        int x2 = x1 + (int)(Math.random() * (W-x1) );
        int y2 = y1 + (int)(Math.random() * (H-y1) );

        // Make sure region is valid
        if (x1 <= x2 && y1 <= y2) {
          
          // System.out.printf("(%d, %d) (%d %d)\n", x1,y1,x2,y2);

          Rect region = new Rect(x1,y1,x2,y2);
          int expectedPts = bruteForceCount(grid,x1,y1,x2,y2);
          int quadTreeCount = quadTree.count(region);
          // System.out.printf("EXPECTED: %d, GOT: %d\n", expectedPts, quadTreeCount);
          assertEquals(expectedPts, quadTreeCount);

          // Increment because we have a valid region
          i++;

        }
      }      

    }

  }

}

