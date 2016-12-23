import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class GraphAdjacencyListTest {

  @Test(expected=IllegalArgumentException.class)
  public void testIllegalAdjListConstruction1() {
    new GraphAdjacencyList(-1);
  }

  @Test(expected=IllegalArgumentException.class)
  public void testIllegalAdjListConstruction2() {
    new GraphAdjacencyList(0);
  }

  @Test
  public void testLegalAdjListConstruction1() {
    new GraphAdjacencyList(1);
  }

  @Test
  public void testLegalAdjListConstruction2() {
    new GraphAdjacencyList(10);
  }

  @Test
  public void testAddingEdgesAdjList() {
    GraphAdjacencyList m = new GraphAdjacencyList(5);
    m.addDirectedEdge(0, 1, 5);
    m.addDirectedEdge(0, 1, 5);
    m.addDirectedEdge(0, 1, 5);
    assertEquals( 1, m.numEdges() );
  }

  @Test
  public void testAddingEdgesAdjList2() {
    GraphAdjacencyList m = new GraphAdjacencyList(5);
    m.addDirectedEdge(0, 1, 5);
    m.addDirectedEdge(1, 4, -5);
    m.addDirectedEdge(2, 3, 16);
    assertEquals( 3, m.numEdges() );
  }

  @Test
  public void testSetAddingEdges() {
    
    Edge edge00 = new Edge(0,0,0);
    Edge edge12 = new Edge(1,2,-8);
    Edge edge35 = new Edge(3,5,4);

    HSet <Edge> set = new HSet<>();
    set.add(edge00);
    set.add(edge12);
    set.add(edge35);

    assertEquals( 3, set.size() );
    
    set.remove(edge00);
    assertFalse(set.contains(edge00));
    assertEquals(2, set.size());

    set.remove(edge12);
    assertFalse(set.contains(edge12));
    assertEquals(1, set.size());

    set.remove(edge35);
    assertFalse(set.contains(edge35));
    assertEquals(0, set.size());

  }

  @Test
  public void testEdgeCountAdjList() {
    
    GraphAdjacencyList m = new GraphAdjacencyList(1);

    m.addDirectedEdge(0, 1, 5);
    m.addDirectedEdge(1, 4, -5);
    m.addDirectedEdge(2, 3, 16);
    assertEquals( 3, m.numEdges() );

    m.removeDirectedEdge(0, 1); // only this edge exists
    m.removeDirectedEdge(3, 4);
    m.removeDirectedEdge(6, 8);
    assertEquals( 2, m.numEdges() );

    m.removeDirectedEdge(1, 4);
    m.removeDirectedEdge(2, 3);
    assertEquals( 0, m.numEdges() );

    m.addUndirectedEdge(0, 0, 16);
    assertEquals( 1, m.numEdges() );

    m.removeUndirectedEdge(0, 0);
    assertEquals( 0, m.numEdges() );

    m.addUndirectedEdge(5, 6, 7);
    m.addUndirectedEdge(5, 7, 7);
    m.addUndirectedEdge(5, 8, 7);
    assertEquals( 6, m.numEdges() );

  }

  @Test
  public void testNumNodesAdjList1() {
    GraphAdjacencyList m = new GraphAdjacencyList(5);
    assertEquals( 5, m.numNodes() );
    m = new GraphAdjacencyList(3);
    assertEquals( 3, m.numNodes() );
    m = new GraphAdjacencyList(55);
    assertEquals( 55, m.numNodes() );
  }


}

