import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class GraphAdjacencyMatrixTest {

  @Test(expected=IllegalArgumentException.class)
  public void testIllegalAdjMatrixConstruction1() {
    new GraphAdjacencyMatrix(-1);
  }

  @Test(expected=IllegalArgumentException.class)
  public void testIllegalAdjMatrixConstruction2() {
    new GraphAdjacencyMatrix(0);
  }

  @Test
  public void testLegalAdjMatrixConstruction1() {
    new GraphAdjacencyMatrix(1);
  }

  @Test
  public void testLegalAdjMatrixConstruction2() {
    new GraphAdjacencyMatrix(10);
  }

  @Test
  public void testAddingEdgesAdjMatrix() {
    GraphAdjacencyMatrix m = new GraphAdjacencyMatrix(5);
    m.addDirectedEdge(0, 1, 5);
    m.addDirectedEdge(0, 1, 5);
    m.addDirectedEdge(0, 1, 5);
    assertEquals( 1, m.numEdges() );
  }

  @Test
  public void testAddingEdgesAdjMatrix2() {
    GraphAdjacencyMatrix m = new GraphAdjacencyMatrix(5);
    m.addDirectedEdge(0, 1, 5);
    m.addDirectedEdge(1, 4, -5);
    m.addDirectedEdge(2, 3, 16);
    assertEquals( 3, m.numEdges() );
  }

  @Test
  public void testEdgeCountAdjMatrix() {

    GraphAdjacencyMatrix m = new GraphAdjacencyMatrix(50);

    m.addDirectedEdge(0, 1, 5);
    m.addDirectedEdge(1, 4, -5);
    m.addDirectedEdge(2, 3, 16);
    assertEquals( 3, m.numEdges() );

    m.removeDirectedEdge(0, 1);
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
  public void testNumNodesAdjMatrix1() {
    GraphAdjacencyMatrix m = new GraphAdjacencyMatrix(5);
    assertEquals( 5, m.numNodes() );
    m = new GraphAdjacencyMatrix(3);
    assertEquals( 3, m.numNodes() );
    m = new GraphAdjacencyMatrix(55);
    assertEquals( 55, m.numNodes() );
  }

}

