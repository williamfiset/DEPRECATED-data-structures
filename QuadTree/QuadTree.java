
/**
 * A QuadTree implementation with integer coordinates.
 * @author William Fiset, william.alexandre.fiset@gmail.com
 **/

import java.awt.*;

class Rect {

  long x1, y1, x2, y2;

  // Define a rectangle as a pair of points (x1, y1) in the bottom left corner
  // and (x2, y2) in the top right corner of the rectangle.
  public Rect(long x1, long y1, long x2, long y2) {
    if (x1 > x2 || y1 > y2) throw new IllegalArgumentException("Illegal rectangle coordinates");
    this.x1 = x1; this.y1 = y1;
    this.x2 = x2; this.y2 = y2;
  }

  // It is easier to check if two rectangles do not
  // intersect and negate the logic afterwards
  public boolean intersects(Rect r) {
    return r != null && !(r.x2 < x1 || r.x1 > x2 || r.y1 > y2 || r.y2 < y1);
  }

  // Check if a point (x, y) is within this rectangle, this
  // includes the boundary of the rectangle
  public boolean contains(long x, long y) {
    return (x1 <= x && x <= x2) && (y1 <= y && y <= y2);
  }

  // Check if another rect is strictly contained within this rectangle
  public boolean contains(Rect r) {
    return r != null && contains(r.x1, r.y1) && contains(r.x2, r.y2);
  }

}

class QTNode {

  // This is the maximum number of points each quad tree node can
  // sustain before it has to subdivide into four more regions.
  // This variables can have a significant impact on performance.
  private static final int NUM_POINTS = 16;

  // Keeps track of how many points are currently
  // contained within this quad tree node.
  private int ptCount = 0;

  // Tracks the (x,y) coordinates of points within this quad tree node
  private long [] X, Y;

  // Define four Quad Tree nodes to subdivide the region we're
  // considering into four parts: north west (nw), north east (ne),
  // south west(sw) and south east(se)
  private QTNode nw, ne, sw, se;

  // The region this node encompasses
  private Rect region;

  // Construct a quad tree for a particular region
  public QTNode(Rect region) {
    if (region == null) throw new IllegalArgumentException("Illegal region");
    this.region = region;
    X = new long[NUM_POINTS];
    Y = new long[NUM_POINTS];
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

      if (sw == null) sw = new QTNode( new Rect(region.x1, region.y1, cx, cy) );
      if (sw.add(x, y)) return true;

      if (nw == null) nw = new QTNode( new Rect(region.x1, cy, cx, region.y2) );
      if (nw.add(x, y)) return true;

      if (ne == null) ne = new QTNode( new Rect(cx, cy, region.x2, region.y2) );
      if (ne.add(x, y)) return true;

      if (se == null) se = new QTNode( new Rect(cx, region.y1, region.x2, cy) );
      return se.add(x, y);

    }

  }

  // Count how many points are found within a certain rectangular region
  public int count(Rect area) {

    if (area == null || !region.intersects(area)) return 0;

    int count = 0;

    // The area we're considering fully contains 
    // the region of this node, so simply add the
    // number of points within this region to the count
    if ( area.contains(region) ) {
      count = ptCount;

    // Our regions overlap, so some points in this
    // region may intersect with the area we're considering
    } else {
      for (int i = 0; i < ptCount; i++)
        if ( area.contains(X[i], Y[i]) )
          count++;
    }

    // Dig into each of the quadrants and count all points
    // which overlap with the area and sum their count
    if (nw != null) count += nw.count(area);
    if (ne != null) count += ne.count(area);
    if (sw != null) count += sw.count(area);
    if (se != null) count += se.count(area);

    return count;

  }

}
