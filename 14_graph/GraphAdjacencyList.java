
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

  public GraphAdjacencyList(int numNodes) {
    adjacencyList = new Mapping<>(numNodes);
  }

  public int numNodes() {
    return adjacencyList.size();
  }

  public int numEdges() {
    return edgeCount;
  }

  // Get all the edges exiting node at nodeID
  public LinkedList <Edge> getEdges(int nodeID) {
    return adjacencyList.get(nodeID);
  }

  // Add an edge to this graph
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

  // Add an edge to this graph
  public void addDirectedEdge(int from, int to, int weight) {
    addDirectedEdge(from, to, weight);
    addDirectedEdge(to, from, weight);
  }

  // Remove an edge from this Graph
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

