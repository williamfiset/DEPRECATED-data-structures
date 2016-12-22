

class GraphAdjacencyMatrix {

  final int N;
  final Integer[][] matrix;

  private int edgeCount = 0;

  // Creates an Graph with an adjacency matrix, O(V)
  public GraphAdjacencyMatrix(int N) {
    if (N <= 0) throw new IllegalArgumentException();
    matrix = new Integer[N][N];
    this.N = N;
  }

  public int numNodes() { return N; }
  public int numEdges() { return edgeCount; }

  // O(1)
  public void addDirectedEdge(int from, int to, int weight) {
    if ( (0 > from && from >= N) || (0 > to && to >= N))
      throw new IllegalArgumentException();
    if (matrix[from][to] == null) edgeCount++;
    matrix[from][to] = weight;
  }

  // O(1)
  public void addUndirectedEdge(int from, int to, int weight) {
    addDirectedEdge(from, to, weight);
    addDirectedEdge(to, from, weight);
  }

  // O(1)
  public void removeEdge(int from, int to, int weight) {
    if ( (0 > from && from >= N) || (0 > to && to >= N))
      throw new IllegalArgumentException();
    if (matrix[from][to] != null) edgeCount--;
    matrix[from][to] = null;
    matrix[to][from] = null;
  }

}

