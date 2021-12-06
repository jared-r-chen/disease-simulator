import java.util.*;

   /**
    * a representation of an edge. holds start node, end node, and weight.
    */
public class Edge{
  public String node1;
  public String node2;
  public int weight;

   /**
    * Creates an edge.
    * @param node1: a string representing a node in the edge
    * @param node2: a string representing a node in the edge
    * @param weight: an int representing the "distance" between two nodes, this affects of easily it is to be infected (the closer two nodes are, the higher the chance of infection)
    * @contructor
    */
  public Edge(String node1, String node2, int weight){
    this.weight = weight;
    this.node1 = node1;
    this.node2 = node2;
  }
}