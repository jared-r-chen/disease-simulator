import java.util.*;

  /**
  * Sublclass of Disease. Modified update function.
  */
public class PPE extends Disease{

  public PPE(double rate, String name){
    super(rate, name);
 }
      
  
     /**
    * @Override
    * update: looks at a node and if it is infected it will look at all of the nodes connected to the current node. Then, based on infection rate and edge weight, update will infect neighboring nodes. However, in this subclass, infection rate is increased to reflect PPE.
    * @param currNode: the current node being looked at
    * @param nodes: a hashmap of all the other nodes in the graph. This gives the update method the ability to infect other nodes in the graph.
    * @return: void
    */
    public void update(Node currNode, HashMap<String, Node> nodes){
    if (currNode.infected){
      ArrayList<TempEdge> connected = currNode.getEdges();
      Random rand = new Random(); 


      for (TempEdge edge: connected){
        int randNum = rand.nextInt(100); 
        double modifyRate = (rate * 6.0/edge.weight) / 2;
        if (randNum < modifyRate){
          nodes.get(edge.end).infected = true;
        }
      }
    }
  }
}