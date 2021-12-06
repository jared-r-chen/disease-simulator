import java.util.*;

   /**
    * The node class cotains the name of a node, whether or not it is infected, and an arraylist of edges that have this node in the edges.
    */
public class Node{
  public String name;
  public boolean infected;
  public ArrayList<Edge> edges;

   /**
    * creates a node.
    * @param name: a String representing the name of the disease.
    * @param infected: a boolean of whether the current node is infected.
    * @contructor
    */
  public Node(String name, boolean infected){
    this.infected = infected;
    this.name = name;
    edges = new ArrayList<Edge>();
  }

   /**
    *adds an edge of the edges arrayList.
    * @param edge: the edge that is going to be added to the arraylist
    * @void
    */
  public void addEdge(Edge edge){
    edges.add(edge);
  }

   /**
    * getEdges returns an arraylist of tempEdges. tempEdges are simplified versions of the edges, they only have the destination of an edge and its weight. 
    * @return: an arraylist of of tempEdges. 
    */
  public ArrayList<TempEdge> getEdges(){
    ArrayList<TempEdge> connected = new ArrayList<TempEdge>();

    for (Edge edge: edges){
      if (edge.node1.equals(name)){
        TempEdge temp = new TempEdge(edge.node2, edge.weight);
        connected.add(temp);
      }
      else{
        TempEdge temp = new TempEdge(edge.node1, edge.weight);
        connected.add(temp);
      }
    }
    return connected;
  }

   /**
    * toString returns a string representing key information about a node. used for testing.
    * @return: returns a string representing key information about a node.
    */
  public String toString(){
    String returnString = name + " " + infected;
    for (int i = 0; i < edges.size(); i ++){
      returnString = returnString + " " + edges.get(i).node1 + "--" + edges.get(i).weight + "--" + edges.get(i).node2;
    } 
    return returnString;
  }

}