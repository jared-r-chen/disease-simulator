import java.util.*;

   /**
    * The disease class has two variable: rate and name. the rate is the infection rate of a disease. the name is the name of the disease itself. the disease class also has getRate which returns the infection rate of a disease and getName, which returns the name of a disease. The last method is update. update updates the map every "day", infecting new people as the disease spreads.
    */
public class Disease{
  public double rate;
  public String name;

   /**
    * Creates a disease which hold a diseases infection rate, and its name.
    * @param rate: a double representing the infection rate of a disease.
    * @param name: a String representing the name of the disease.
    * @contructor
    */
  public Disease(double rate, String name){
    this.rate = rate;
    this.name = name;
  }
   /**
    * getRate returns a double represention of the infection rate.
    * @return rate: a double representing the infection rate.
    */
  public double getRate(){
    return rate;
  }

   /**
    * getName returns a string represention of the disease name.
    * @return name: returns the name of the disease.
    */
  public String getName(){
    return name;
  }


   /**
    * update: looks at a node and if it is infected it will look at all of the nodes connected to the current node. Then, based on infection rate and edge weight, update will infect neighboring nodes.
    * @param currNode: the current node being looked at
    * @param nodes: a hashmap of all the other nodes in the graph. This gives the update method the ability to infect other nodes in the graph.
    * @void
    */
  public void update(Node currNode, HashMap<String, Node> nodes){
    if (currNode.infected){
      //creates an arraylist of tempEdges, which only hold destination and weight since the start node is already know (currnode).
      ArrayList<TempEdge> connected = currNode.getEdges();
      Random rand = new Random(); 

      for (TempEdge edge: connected){
        int randNum = rand.nextInt(100); 
        //modifyRate is the base rate changed to account for edge weight.
        double modifyRate = rate * 6.0/edge.weight;
        //some "randomness" is involved in infecting neighbors.
        if (randNum < modifyRate){
          nodes.get(edge.end).infected = true;
        }
      }
    }
  }
} 