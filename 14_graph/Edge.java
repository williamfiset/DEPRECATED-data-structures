

public class Edge {
  
  int from, to, weight, hash;

  public Edge(int from, int to, int weight) {
    this.weight = weight;
    this.from = from;
    this.to = to;
    this.hash = java.util.Objects.hash(from, to, weight);
  }
  
  @Override public int hashCode() {
    return hash;
  }

  @Override public boolean equals( Object obj ) {
    if (obj == null) return false;
    Edge other = (Edge) obj;
    return (from==other.from) && (to==other.to) && (weight==other.weight);
  }

}