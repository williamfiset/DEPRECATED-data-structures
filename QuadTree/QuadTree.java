
/**
 * A QuadTree implementation
 * @author William Fiset, william.alexandre.fiset@gmail.com
 **/

// import java.awt.Rectangle;

class Rect {

  long x1, y1, x2, y2;

  // Define a rectangle as a pair of points (x1, y1) in the top left corner
  // and (x2, y2) in the bottom right corner of the rectangle.
  public Rect(long x1, long y1, long x2, long y2) {
    if (x2 > x1 || y2 > y1) throw new IllegalArgumentException("Illegal rectangle coordinates");
    this.x1 = x1; this.y1 = y1;
    this.x2 = x2; this.y2 = y2;
  }

  // It is easier to check if two rectangles do not
  // intersect and negate the logic afterwards
  public boolean intersects(Rect r) {
    return !(r.x1 < x2 || r.x2 > x1 || r.y2 > y1 || r.y1 < y1);
  }

  // Check if a point (x, y) is within this rectangle, this
  // includes the boundary of the rectangle
  public boolean contains(long x, long y) {
    return x1 <= x && x <= x2 && y1 <= y && y <= y2;
  }

  // Check if another rect is strictly contained within this rectangle
  public boolean contains(Rect r) {
    return contains(r.x1, r.y1) && contains(r.x2, r.y2);
  }

  @Override public String toString() {
    return "("+x1+","+y1+"), ("+x2+","+y2+")";
  }

}

class QTNode {

  // This is the maximum number of points each 
  // quad tree node can sustain before it has to
  // subdivide into four more regions.
  private static final int NUM_POINTS = 10;

  // Keeps track of how many points are currently
  // contained within this quad tree node.
  private int ptCount = 0;

  // Tracks the (x,y) coordinates of points within this quad tree node
  private long [] X, Y;

  // Define four Quad Tree nodes to subdivide the region we're
  // considering into four parts: north west (nw), north east (ne),
  // south west(sw) and south east(se)
  private QTNode nw, ne, sw, se;

  private Rect region;

  // Track whether we have already subdivided this region or not
  private boolean subdivided;

  // Construct a quad tree for a particular region
  public QTNode(Rect region) {
    if (region == null) throw new IllegalArgumentException("Illegal region");
    this.region = region;
  }

  // Try adding a point to the current region and if the
  // region is already full subdivide and recurse until
  // you are able to place the point inside a smaller region
  public boolean add(long x, long y) {

    // Point is not within this region
    if (!region.contains(x, y)) return false;

    // The point is within this region and there is room for it
    if (ptCount < NUM_POINTS) {

      X[ptCount] = x;
      Y[ptCount] = y;
      ptCount++;

      return true;

    // This region is full, so subdivide the region into four
    // quadrants and try adding the point to these new regions
    } else {
      
      // Find the center of this region at (cx, cy)
      long cx = (region.x1 + region.x2) / 2;
      long cy = (region.y1 + region.y2) / 2;

      // Lazily subdivide each of the regions into four parts
      // one by one as needed to save memory.

      if (nw == null) nw = new QTNode( new Rect(region.x1, region.y1, cx, cy) );
      if (nw.add(x, y)) return true;

      // Q: Do we need to cx+1 and cy-1??
      if (ne == null) ne = new QTNode( new Rect(cx+1, region.y1, region.x2, cy) );
      if (ne.add(x, y)) return true;

      if (sw == null) sw = new QTNode( new Rect(region.x1, cy-1, cx, region.y2) );
      if (sw.add(x, y)) return true;

      if (se == null) se = new QTNode( new Rect(cx+1, cy-1, region.x2, region.y2) );
      return se.add(x, y);

    }

  }

  public int count(Rect area) {
    return 0;
  }

  private void subdivide() {
    
    subdivided = true;

  }

}

