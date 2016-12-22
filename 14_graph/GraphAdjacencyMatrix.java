

class GraphAdjacencyMatrix {

  final int N;
  final Integer[][] matrix;

  private int edgeCount = 0;

  public GraphAdjacencyMatrix(int N) {
    this.N = N;
    matrix = new Integer[N][N];
  }

  public int numNodes() { return N; }
  public int numEdges() { return edgeCount; }

  public void addDirectedEdge(int from, int to, int weight) {
    if ( (0 > from && from >= N) || (0 > to && to >= N))
      throw new IllegalArgumentException();
    if (matrix[from][to] == null) edgeCount++;
    matrix[from][to] = weight;
  }

  public void addUndirectedEdge(int from, int to, int weight) {
    addDirectedEdge(from, to, weight);
    addDirectedEdge(to, from, weight);
  }

  public void removeEdge(int from, int to, int weight) {
    if ( 0 > from && from >= N ) throw new IllegalArgumentException();
    if ( 0 > to && to >= N ) throw new IllegalArgumentException();
    if (matrix[from][to] != null) edgeCount--;
    matrix[from][to] = null;
    matrix[to][from] = null;
  }

}

