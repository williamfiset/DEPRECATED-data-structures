
public class GraphAdjacencyList {

  private int edgeCount = 0;
  java.util.Map <Integer, java.util.HashSet<Edge>> adjacencyList;

  public GraphAdjacencyList () {
    adjacencyList = new java.util.HashMap<>();
  }

  // Intializes adjacency matrix for nodes indexed
  // from [0, numNodes). Additional nodes can also be added later
  public GraphAdjacencyList(int numNodes) {
    if (numNodes <= 0) throw new IllegalArgumentException();
    adjacencyList = new java.util.HashMap<>( numNodes );
    for (int i = 0; i < numNodes; i++)
      adjacencyList.put(i, new java.util.HashSet<Edge>());
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
  // public Set <Edge> getEdges(int nodeID) {
  public java.util.HashSet <Edge> getEdges(int nodeID) {
    return adjacencyList.get(nodeID);
  }

  // Add an edge to this graph, O(1)
  public void addDirectedEdge(int from, int to, int weight) {

    Edge newEdge = new Edge(from, to, weight);
    java.util.HashSet <Edge> edges = adjacencyList.get(from);

    if (edges == null) {
      edges = new java.util.HashSet<>();
      adjacencyList.put( from, edges );
    }

    // If edge did not exist before
    if (edges.add(newEdge))
      edgeCount++;

  }

  // Add an edge to this graph, O(1)
  public void addUndirectedEdge(int from, int to, int weight) {
    addDirectedEdge(from, to, weight);
    addDirectedEdge(to, from, weight);
  }

  // Remove an edge from this Graph, O(E)
  public void removeDirectedEdge(int from, int to) {
    
    // Set <Edge> edges = getEdges(from);
    java.util.HashSet <Edge> edges = getEdges(from);

    if (edges != null) {

      Edge edgeToRemove = null;

      for (Edge edge : edges) {
        if ( edge.to == to ) {
          edgeToRemove = edge; break;
        }
      }

      if (edges.remove(edgeToRemove))
        edgeCount--;

    }
  }

  public void removeUndirectedEdge(int from, int to) {
    removeDirectedEdge(from, to);
    removeDirectedEdge(to, from);
  }

}

