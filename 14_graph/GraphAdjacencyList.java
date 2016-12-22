
class Edge {
  int from, to, weight;
  public Edge(int from, int to, int weight) {
    this.weight = weight;
    this.from = from;
    this.to = to;
  }
}

public class GraphAdjacencyList {

  private int edgeCount = 0;
  private IMap <Integer, LinkedList<Edge>> adjacencyList;

  public GraphAdjacencyList () {
    adjacencyList = new Mapping<>();
  }

  // Intializes adjacency matrix for nodes indexed
  // from [0, numNodes). Additional nodes can also be added later
  public GraphAdjacencyList(int numNodes) {
    if (numNodes <= 0) throw new IllegalArgumentException();
    adjacencyList = new Mapping<>(numNodes);
    for (int i = 0; i < numNodes; i++)
      adjacencyList.add(i, new LinkedList<Edge>());
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
  public LinkedList <Edge> getEdges(int nodeID) {
    return adjacencyList.get(nodeID);
  }

  // Add an edge to this graph, O(1)
  public void addUndirectedEdge(int from, int to, int weight) {

    Edge newEdge = new Edge(from, to, weight);
    LinkedList <Edge> edges = adjacencyList.get(from);

    if (edges == null) {
      edges = new LinkedList<>();
      adjacencyList.add( from, edges );
    }

    edges.add(newEdge);
    edgeCount++;

  }

  // Add an edge to this graph, O(1)
  public void addDirectedEdge(int from, int to, int weight) {
    addDirectedEdge(from, to, weight);
    addDirectedEdge(to, from, weight);
  }

  // Remove an edge from this Graph, O(E)
  public void removeEdge(int from, int to, int weight) {
    LinkedList <Edge> edges = getEdges(from);
    if (edges != null) {
      Edge edgeToRemove = null;
      for (Edge edge : edges) {
        if ( (edge.to == to) && edge.weight == weight ) {
          edgeToRemove = edge;
          break;
        }
      }
      if (edgeToRemove != null) {
        edges.remove(edgeToRemove);
        edgeCount--;
      }
    }
  }

}

