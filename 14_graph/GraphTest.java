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

  public void testLegalAdjMatrixConstruction() {
    new GraphAdjacencyMatrix(1);
  }

  @Test(expected=IllegalArgumentException.class)
  public void testIllegalAdjListConstruction1() {
    new GraphAdjacencyList(-1);
  }

  @Test(expected=IllegalArgumentException.class)
  public void testIllegalAdjListConstruction2() {
    new GraphAdjacencyList(0);
  }

  public void testLegalAdjListConstruction() {
    new GraphAdjacencyList(1);
  }

}

