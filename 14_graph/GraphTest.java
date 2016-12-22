import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class GraphTest {

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
    new GraphAdjacencyList(1);
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
  public void testNumNodesAdjMatrix1() {
    GraphAdjacencyMatrix m = new GraphAdjacencyMatrix(5);
    assertEquals( 5, m.numNodes() );
    m = new GraphAdjacencyMatrix(3);
    assertEquals( 3, m.numNodes() );
    m = new GraphAdjacencyMatrix(55);
    assertEquals( 55, m.numNodes() );
    
  }

}

