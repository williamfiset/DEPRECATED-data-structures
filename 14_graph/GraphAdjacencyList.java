
class Edge {
  int from, to, weight;
  public Edge(int from, int to, int weight) {
    this.weight = weight;
    this.from = from;
    this.to = to;
  }
  @Override public int hashCode() {
    return java.util.Objects.hash(from, to, weight);
  }
  @Override public boolean equals( Object obj ) {
    if (obj == null) return false;
    Edge other = (Edge) obj;
    return (from==other.from) && (to==other.to) && (weight==other.weight);
  }
}

public class GraphAdjacencyList {

  private int edgeCount = 0;
  IMap <Integer, HSet<Edge>> adjacencyList;

  public GraphAdjacencyList () {
    adjacencyList = new Mapping<>();
  }

  // Intializes adjacency matrix for nodes indexed
  // from [0, numNodes). Additional nodes can also be added later
  public GraphAdjacencyList(int numNodes) {
    if (numNodes <= 0) throw new IllegalArgumentException();
    adjacencyList = new Mapping<>( numNodes );
    for (int i = 0; i < numNodes; i++)
      adjacencyList.add(i, new HSet<Edge>());
  }

  // Returns the number of nodes in this graph
  public int numNodes() {
    return adjacencyList.size();
  }

  // Returns the number of edges in this graph
  public int numEdges() {
    return edgeCount;
  }

  // Get all the edges exiting node at nodeID
  public HSet <Edge> getEdges(int nodeID) {
    return adjacencyList.get(nodeID);
  }

  // Add an edge to this graph, O(1)
  public void addDirectedEdge(int from, int to, int weight) {
    
    System.out.println("FROM: " + from + " TO: " + to + " weight: " + weight);
    Edge newEdge = new Edge(from, to, weight);
    HSet <Edge> edges = adjacencyList.get(from);

    if (edges == null) {
      edges = new HSet<>();
      adjacencyList.add( from, edges );
    }

    if (!edges.contains(newEdge)) {
      edges.add(newEdge);
      edgeCount++;
    }

  }

  // Add an edge to this graph, O(1)
  public void addUndirectedEdge(int from, int to, int weight) {
    addDirectedEdge(from, to, weight);
    addDirectedEdge(to, from, weight);
  }

  // Remove an edge from this Graph, O(E)
  public void removeDirectedEdge(int from, int to) {
    HSet <Edge> edges = getEdges(from);
    if (edges != null) {
      
      Edge edgeToRemove = null;

      for (Edge edge : edges) {
        if ( edge.to == to ) {
          edgeToRemove = edge; break;
        }
      }

      if (edgeToRemove != null)
        if (edges.contains(edgeToRemove)) {
          edges.remove(edgeToRemove);
          edgeCount--;
        }

    }
  }

  public void removeUndirectedEdge(int from, int to) {
    removeDirectedEdge(from, to);
    removeDirectedEdge(to, from);
  }

}

